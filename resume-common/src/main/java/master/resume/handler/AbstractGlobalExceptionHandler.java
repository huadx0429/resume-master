package master.resume.handler;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import master.resume.entity.ResponseBody;
import master.resume.enums.BizCodeEnum;
import master.resume.exception.BizException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@SuppressWarnings("AlibabaUndefineMagicConstant")
@Slf4j
public abstract class AbstractGlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> bizException(BizException ex) {
        log.warn("BizException:", ex);
        return ResponseBody.result(ex.getCode(), null,ex.getMessage()).setPath(getPath());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.warn("HttpMessageNotReadableException:", ex);
        String message = ex.getMessage();
        if (StrUtil.containsAny(message, "Could not read document:")) {
            String msg = String.format("无法正确的解析json类型的参数：%s", StrUtil.subBetween(message, "Could not read document:", " at "));
            return ResponseBody.result(BizCodeEnum.PARAM_EX.getCode(), null, msg).setPath(getPath());
        }
        return ResponseBody.result(BizCodeEnum.PARAM_EX.getCode(), null, ex.getMessage()).setPath(getPath());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> bindException(BindException ex) {
        log.warn("BindException:", ex);
        try {
            String msg = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
            if (StrUtil.isNotEmpty(msg)) {
                return ResponseBody.result(BizCodeEnum.PARAM_EX.getCode(), null, msg).setPath(getPath());
            }
        } catch (Exception ee) {
            log.debug("获取异常描述失败", ee);
        }
        StringBuilder msg = new StringBuilder();
        List<FieldError> fieldErrors = ex.getFieldErrors();
        fieldErrors.forEach((oe) ->
                msg.append("参数:[").append(oe.getObjectName())
                        .append(".").append(oe.getField())
                        .append("]的传入值:[").append(oe.getRejectedValue()).append("]与预期的字段类型不匹配.")
        );
        return ResponseBody.result(BizCodeEnum.PARAM_EX.getCode(), null, msg.toString()).setPath(getPath());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.warn("MethodArgumentTypeMismatchException:", ex);
        String msg = "参数：[" + ex.getName() + "]的传入值：[" + ex.getValue() +
                "]与预期的字段类型：[" + Objects.requireNonNull(ex.getRequiredType()).getName() + "]不匹配";
        return ResponseBody.result(BizCodeEnum.PARAM_EX.getCode(), null, msg).setPath(getPath());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> illegalStateException(IllegalStateException ex) {
        log.warn("IllegalStateException:", ex);
        return ResponseBody.result(BizCodeEnum.ILLEGAL_ARGUMENT_EX.getCode(), null, BizCodeEnum.ILLEGAL_ARGUMENT_EX.getMsg()).setPath(getPath());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.warn("MissingServletRequestParameterException:", ex);
        return ResponseBody.result(BizCodeEnum.ILLEGAL_ARGUMENT_EX.getCode(), null, "缺少必须的[" + ex.getParameterType() + "]类型的参数[" + ex.getParameterName() + "]").setPath(getPath());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> nullPointerException(NullPointerException ex) {
        log.warn("NullPointerException:", ex);
        return ResponseBody.result(BizCodeEnum.NULL_POINT_EX.getCode(), null, BizCodeEnum.NULL_POINT_EX.getMsg()).setPath(getPath());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> illegalArgumentException(IllegalArgumentException ex) {
        log.warn("IllegalArgumentException:", ex);
        return ResponseBody.result(BizCodeEnum.ILLEGAL_ARGUMENT_EX.getCode(), null, BizCodeEnum.ILLEGAL_ARGUMENT_EX.getMsg()).setPath(getPath());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        log.warn("HttpMediaTypeNotSupportedException:", ex);
        MediaType contentType = ex.getContentType();
        if (contentType != null) {
            return ResponseBody.result(BizCodeEnum.MEDIA_TYPE_EX.getCode(), null, "请求类型(Content-Type)[" + contentType + "] 与实际接口的请求类型不匹配").setPath(getPath());
        }
        return ResponseBody.result(BizCodeEnum.MEDIA_TYPE_EX.getCode(), null, "无效的Content-Type类型").setPath(getPath());
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> missingServletRequestPartException(MissingServletRequestPartException ex) {
        log.warn("MissingServletRequestPartException:", ex);
        return ResponseBody.result(BizCodeEnum.REQUIRED_FILE_PARAM_EX.getCode(), null, BizCodeEnum.REQUIRED_FILE_PARAM_EX.getMsg()).setPath(getPath());
    }

    @ExceptionHandler(ServletException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> servletException(ServletException ex) {
        log.warn("ServletException:", ex);
        String msg = "UT010016: Not a multi part request";
        if (msg.equalsIgnoreCase(ex.getMessage())) {
            return ResponseBody.result(BizCodeEnum.REQUIRED_FILE_PARAM_EX.getCode(), null, BizCodeEnum.REQUIRED_FILE_PARAM_EX.getMsg());
        }
        return ResponseBody.result(BizCodeEnum.SYSTEM_BUSY.getCode(), null, ex.getMessage()).setPath(getPath());
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> multipartException(MultipartException ex) {
        log.warn("MultipartException:", ex);
        return ResponseBody.result(BizCodeEnum.REQUIRED_FILE_PARAM_EX.getCode(), null, BizCodeEnum.REQUIRED_FILE_PARAM_EX.getMsg()).setPath(getPath());
    }

    /**
     * jsr 规范中的验证异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> constraintViolationException(ConstraintViolationException ex) {
        log.warn("ConstraintViolationException:", ex);
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));

        return ResponseBody.result(BizCodeEnum.BASE_VALID_PARAM.getCode(), null, message).setPath(getPath());
    }

    /**
     * spring 封装的参数验证异常， 在controller中没有写result参数时，会进入
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.warn("MethodArgumentNotValidException:", ex);
        return ResponseBody.result(BizCodeEnum.BASE_VALID_PARAM.getCode(), null, Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage()).setPath(getPath());
    }


    /**
     * 其他异常
     *
     * @param ex 异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> otherExceptionHandler(Exception ex) {
        log.warn("Exception:", ex);
        if (ex.getCause() instanceof BizException) {
            return this.bizException((BizException) ex.getCause());
        }
        return ResponseBody.result(BizCodeEnum.SYSTEM_BUSY.getCode(), null, ex.getMessage()).setPath(getPath());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> sqlException(SQLException ex) {
        log.warn("SQLException:", ex);
        return ResponseBody.result(BizCodeEnum.SQL_EX.getCode(), null, ex.getMessage()).setPath(getPath());
    }
    /**
     * 返回状态码:405
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.warn("HttpRequestMethodNotSupportedException:", ex);
        return ResponseBody.result(BizCodeEnum.METHOD_NOT_ALLOWED.getCode(), null, ex.getMessage()).setPath(getPath());
    }


    @ExceptionHandler(PersistenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> persistenceException(PersistenceException ex) {
        log.warn("PersistenceException:", ex);
        if (ex.getCause() instanceof BizException) {
            BizException cause = (BizException) ex.getCause();
            return ResponseBody.result(cause.getCode(), null, cause.getMessage());
        }
        return ResponseBody.result(BizCodeEnum.SQL_EX.getCode(), null, ex.getMessage()).setPath(getPath());
    }

    @ExceptionHandler(MyBatisSystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody<?> myBatisSystemException(MyBatisSystemException ex) {
        log.warn("PersistenceException:", ex);
        if (ex.getCause() instanceof PersistenceException) {
            return this.persistenceException((PersistenceException) ex.getCause());
        }
        return ResponseBody.result(BizCodeEnum.SQL_EX.getCode(), null, ex.getMessage()).setPath(getPath());
    }


    private String getPath() {
        String path = "";
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            path = request.getRequestURI();
        }
        return path;
    }

}

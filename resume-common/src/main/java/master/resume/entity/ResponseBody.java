package master.resume.entity;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import master.resume.exception.BaseExceptionCode;
import master.resume.exception.BizException;


/**
 * @author hdx
 * @date 2023-03-28 13:46
 */
@Data
@SuppressWarnings("ALL")
@Accessors(chain = true)
public class ResponseBody<T> {
    public static final String DEF_ERROR_MESSAGE = "系统繁忙，请稍候再试";
    public static final String HYSTRIX_ERROR_MESSAGE = "请求超时，请稍候再试";
    public static final int SUCCESS_CODE = 0;
    public static final int FAIL_CODE = -1;
    public static final int TIMEOUT_CODE = -2;
    /**
     * 统一参数验证异常
     */
    public static final int VALID_EX_CODE = -9;
    public static final int OPERATION_EX_CODE = -10;
    /**
     * 调用是否成功标识，0：成功，-1:系统繁忙，此时请开发者稍候再试 详情见[ExceptionCode]
     */
    @Schema(name = "响应编码:0/200-请求处理成功")
    private int code;

    /**
     * 是否执行默认操作
     */
    @JsonIgnore
    private Boolean defExec = true;

    @Schema(name = "请求路径")
    @JsonIgnore
    private String path;

    /**
     * 调用结果
     */
    @Schema(name = "响应数据")
    private T data;

    /**
     * 结果消息，如果调用成功，消息通常为空T
     */
    @Schema(name = "提示消息")
    private String msg = "ok";


    /**
     * 响应时间
     */
    @Schema(name = "响应时间戳")
    private long timestamp = System.currentTimeMillis();


    private ResponseBody() {
        this.defExec = false;
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseBody(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.defExec = false;
        this.timestamp = System.currentTimeMillis();
    }


    public ResponseBody(int code, T data, String msg, boolean defExec) {
        this(code, data, msg);
        this.defExec = defExec;
    }

    public static <E> ResponseBody<E> fail(BaseExceptionCode exceptionCode) {
        return validFail(exceptionCode);
    }

    public static <E> ResponseBody<E> result(int code, E data, String msg) {
        return new ResponseBody<>(code, data, msg);
    }


    /**
     * 请求成功消息
     *
     * @param data 结果
     * @return RPC调用结果
     */
    public static <E> ResponseBody<E> success(E data) {
        return new ResponseBody<>(SUCCESS_CODE, data, "ok");
    }

    public static <E> ResponseBody<E> success() {
        return new ResponseBody<>(SUCCESS_CODE, null, "ok");
    }


    public static <E> ResponseBody<E> successDef(E data) {
        return new ResponseBody<>(SUCCESS_CODE, data, "ok", true);
    }

    public static <E> ResponseBody<E> successDef() {
        return new ResponseBody<>(SUCCESS_CODE, null, "ok", true);
    }

    public static <E> ResponseBody<E> successDef(E data, String msg) {
        return new ResponseBody<>(SUCCESS_CODE, data, msg, true);
    }

    /**
     * 请求成功方法 ，data返回值，msg提示信息
     *
     * @param data 结果
     * @param msg  消息
     * @return RPC调用结果
     */
    public static <E> ResponseBody<E> success(E data, String msg) {
        return new ResponseBody<>(SUCCESS_CODE, data, msg);
    }

    /**
     * 请求失败消息
     *
     * @param msg
     * @return
     */
    public static <E> ResponseBody<E> fail(int code, String msg) {
        return new ResponseBody<>(code, null, (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg);
    }

    public static <E> ResponseBody<E> fail(String msg) {
        return fail(OPERATION_EX_CODE, msg);
    }

    public static <E> ResponseBody<E> fail(String msg, Object... args) {
        String message = (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg;
        return new ResponseBody<>(OPERATION_EX_CODE, null, String.format(message, args));
    }


    public static <E> ResponseBody<E> fail(BizException exception) {
        if (exception == null) {
            return fail(DEF_ERROR_MESSAGE);
        }
        return new ResponseBody<>(exception.getCode(), null, exception.getMessage());
    }

    /**
     * 请求失败消息，根据异常类型，获取不同的提供消息
     *
     * @param throwable 异常
     * @return RPC调用结果
     */
    public static <E> ResponseBody<E> fail(Throwable throwable) {
        String msg = throwable != null ? throwable.getMessage() : DEF_ERROR_MESSAGE;
        return fail(FAIL_CODE, msg);
    }

    public static <E> ResponseBody<E> validFail(String msg) {
        return new ResponseBody<>(VALID_EX_CODE, null, (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg);
    }

    public static <E> ResponseBody<E> validFail(String msg, Object... args) {
        String message = (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg;
        return new ResponseBody<>(VALID_EX_CODE, null, String.format(message, args));
    }

    public static <E> ResponseBody<E> validFail(BaseExceptionCode exceptionCode) {
        return new ResponseBody<>(exceptionCode.getCode(), null,
                (exceptionCode.getMsg() == null || exceptionCode.getMsg().isEmpty()) ? DEF_ERROR_MESSAGE : exceptionCode.getMsg());
    }

    public static <E> ResponseBody<E> timeout() {
        return fail(TIMEOUT_CODE, HYSTRIX_ERROR_MESSAGE);
    }



    /**
     * 逻辑处理是否成功
     *
     * @return 是否成功
     */
    public Boolean getIsSuccess() {
        return this.code == SUCCESS_CODE || this.code == 200;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

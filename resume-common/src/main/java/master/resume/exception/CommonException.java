package master.resume.exception;

import lombok.Data;

/**
 * 非业务异常
 * 用于在处理非业务逻辑时，进行抛出的异常。
 *
 * @author zuihou
 * @version 1.0
 * @see Exception
 */
@Data
public class CommonException extends Exception {

    /**
     * 异常信息
     */
    private String message;

    /**
     * 具体异常码
     */
    private int code;

    public CommonException(final int code, final String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CommonException(final int code, final String format, Object... args) {
        super(String.format(format, args));
        this.code = code;
        this.message = String.format(format, args);
    }

}

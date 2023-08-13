package master.resume.exception;

/**
 * 异常编码
 *
 * @author hdx
 * @date 2023-03-28 13:46
 */
public interface BaseExceptionCode {
    /**
     * 异常编码
     *
     * @return 异常编码
     */
    int getCode();

    /**
     * 异常消息
     *
     * @return 异常消息
     */
    String getMsg();
}

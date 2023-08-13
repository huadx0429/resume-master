package master.resume.exception;

import lombok.Data;

@Data
public class BizException extends RuntimeException{

    private int code;

    private String message;


    public BizException(BaseExceptionCode bizCodeEnum) {
        super();
        this.code = bizCodeEnum.getCode();
        this.message = bizCodeEnum.getMsg();
    }

    public BizException(int code,String msg){
        super();
        this.code = code;
        this.message = msg;
    }

    @Override
    public String toString() {
        return "BizException{" +
                "code=" + code +
                ", msg='" + message + '\'' +
                '}';
    }
}

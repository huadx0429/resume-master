package master.resume.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeInfoAddRequest {


    private String name;

    /**
     * 头像
     */
    private String picture;

    private String birth;

    private Integer age;

    /**
     * 1男 2女
     */
    private Integer sex;

    /**
     * 求职意向
     */
    private String intention;

    /**
     * 自我评价
     */
    private String selfEvaluation;

}

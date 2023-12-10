package master.resume.vo.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeAllInfoResponse {

    private Long id;

    private String name;

    /**
     * 头像
     */
    private String picture;

    private String birth;

    private Integer age;

    private Integer sex;

    /**
     * 求职意向
     */
    private String intention;

    /**
     * 自我评价
     */
    private String selfEvaluation;

    /**
     * 教育经历
     */
    private List<ResumeEducationResponse> educationExperience;

    /**
     * 工作经历
     */
    private List<ResumeWorkExperienceResponse> workExperience;

}

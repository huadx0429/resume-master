package master.resume.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeWorkExperienceAddRequest {

    /**
     * 简历id
     */
    private Long resumeId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 职位
     */
    private String position;

    /**
     * 工作内容
     */
    private String workContent;

}

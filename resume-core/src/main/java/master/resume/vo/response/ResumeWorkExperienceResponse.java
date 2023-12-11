package master.resume.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeWorkExperienceResponse {

    /**
     * id
     */
    private Long id;

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

    private Date startDate;

    private Date endDate;

}

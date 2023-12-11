package master.resume.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @JsonFormat(pattern = "yyyy-MM", shape = JsonFormat.Shape.STRING)
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM", shape = JsonFormat.Shape.STRING)
    private Date endDate;

}

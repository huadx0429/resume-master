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
public class ResumeEducationAddRequest {


    /**
     * 简历id
     */
    private Long resumeId;

    /**
     * 学校经历
     */
    private String schoolName;

    /**
     * 学历
     */
    private Integer degree;

    /**
     * 专业
     */
    private String speciality;

    @JsonFormat(pattern = "yyyy-MM", shape = JsonFormat.Shape.STRING)
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM", shape = JsonFormat.Shape.STRING)
    private Date endDate;
}

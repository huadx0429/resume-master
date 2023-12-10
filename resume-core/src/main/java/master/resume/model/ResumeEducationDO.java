package master.resume.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import master.resume.entity.BaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author huadexuan
 * @since 2023-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("resume_education")
public class ResumeEducationDO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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

    private LocalDateTime startDate;

    private LocalDateTime endDate;



}

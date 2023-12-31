package master.resume.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import master.resume.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

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
     * 用户id
     */
    private Long userId;
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

package master.resume.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("resume_work_experience")
public class ResumeWorkExperienceDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 删除标识
     */
    private Integer delFlag;


}

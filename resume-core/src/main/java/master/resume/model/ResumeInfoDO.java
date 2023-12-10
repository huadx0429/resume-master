package master.resume.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import master.resume.entity.BaseEntity;

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
@TableName("resume_info")
public class ResumeInfoDO extends BaseEntity implements Serializable {


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


}

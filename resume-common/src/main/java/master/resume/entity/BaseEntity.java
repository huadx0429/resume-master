package master.resume.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entity基类
 * @author cui
 */
@Data
public abstract class BaseEntity {

    /**
     * 默认雪花算法生成id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(name = "主键id")
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(name = "创建时间")
    private LocalDateTime createTime;

    /**
     * 删除标记
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @Schema(name = "删除标识")
    private Integer delFlag;
}

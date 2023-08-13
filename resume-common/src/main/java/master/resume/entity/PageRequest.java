package master.resume.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



/**
 * 查询公共参数
 * @author cui
 */
@Data
public class PageRequest {
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    @Schema(description = "当前页码", required = true)
    Integer page=1;

    @NotNull(message = "每页条数不能为空")
    @Schema(description = "每页条数", required = true)
    Integer limit=10;

    @Schema(description = "排序字段")
    String order;

    @Schema(description = "是否升序")
    boolean asc;
}

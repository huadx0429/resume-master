package master.resume.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class OssConfig {

    /**
     * 本地存储文件访问路径
     */
    private static String accessPathPattern = "/u/**";

}

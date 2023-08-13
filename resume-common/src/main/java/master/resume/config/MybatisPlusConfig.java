package master.resume.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import master.resume.handler.FieldMetaObjectHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    /**
     * 实现分页配置
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());   //    注册乐观锁插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());    // 分页插件
        return interceptor;
    }

    @Bean
    public FieldMetaObjectHandler fieldMetaObjectHandler() {
        return new FieldMetaObjectHandler();
    }
}
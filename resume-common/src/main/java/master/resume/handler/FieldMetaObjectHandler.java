package master.resume.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * mybatis-plus 自动填充字段
 *
 * @author cui
 */
public class FieldMetaObjectHandler implements MetaObjectHandler {
    private final static String CREATE_TIME = "createTime";
    private final static String DEL_FLAG = "delFlag";


    @Override
    public void insertFill(MetaObject metaObject) {

        // 创建时间
        this.setFieldValByName(CREATE_TIME, LocalDateTime.now(), metaObject);
        // 删除标识
        this.fillStrategy(metaObject,DEL_FLAG,0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
    }

}
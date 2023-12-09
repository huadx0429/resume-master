package master.resume.service.Impl;

import master.resume.model.UserDO;
import master.resume.mapper.UserMapper;
import master.resume.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huadexuan
 * @since 2023-12-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

}

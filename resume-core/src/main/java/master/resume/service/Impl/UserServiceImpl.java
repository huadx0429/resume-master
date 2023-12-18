package master.resume.service.Impl;

import master.resume.entity.ResponseBody;
import master.resume.model.UserDO;
import master.resume.mapper.UserMapper;
import master.resume.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import master.resume.vo.request.UserAddRequest;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseBody<Boolean> addUser(UserAddRequest request) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(request,userDO);
        userMapper.insert(userDO);
        return ResponseBody.success(true);
    }
}

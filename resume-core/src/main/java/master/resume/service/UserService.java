package master.resume.service;

import master.resume.entity.ResponseBody;
import master.resume.model.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import master.resume.vo.request.UserAddRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huadexuan
 * @since 2023-12-09
 */
public interface UserService extends IService<UserDO> {

    ResponseBody<Boolean> addUser(UserAddRequest request);
}

package master.resume.controller;


import master.resume.entity.ResponseBody;
import master.resume.service.UserService;
import master.resume.vo.request.UserAddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huadexuan
 * @since 2023-12-09
 */
@RestController
@RequestMapping("/api/user/v1")
public class UserController {

    @Autowired
    private UserService userService;


    @PutMapping("/user")
    public ResponseBody<Boolean> addUser(@RequestBody UserAddRequest request){
        return userService.addUser(request);
    }

}


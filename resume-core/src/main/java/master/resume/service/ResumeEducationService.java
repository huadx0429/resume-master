package master.resume.service;

import master.resume.entity.ResponseBody;
import master.resume.model.ResumeEducationDO;
import com.baomidou.mybatisplus.extension.service.IService;
import master.resume.vo.request.ResumeEducationAddRequest;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huadexuan
 * @since 2023-12-09
 */
public interface ResumeEducationService extends IService<ResumeEducationDO> {

    ResponseBody<String> add(List<ResumeEducationAddRequest> requestList);
}

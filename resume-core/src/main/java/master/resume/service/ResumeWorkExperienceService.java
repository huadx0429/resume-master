package master.resume.service;

import master.resume.entity.ResponseBody;
import master.resume.model.ResumeWorkExperienceDO;
import com.baomidou.mybatisplus.extension.service.IService;
import master.resume.vo.request.ResumeWorkExperienceAddRequest;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huadexuan
 * @since 2023-12-09
 */
public interface ResumeWorkExperienceService extends IService<ResumeWorkExperienceDO> {

    ResponseBody<String> add(List<ResumeWorkExperienceAddRequest> requestList);
}

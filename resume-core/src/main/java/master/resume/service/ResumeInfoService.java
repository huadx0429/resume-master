package master.resume.service;

import master.resume.entity.ResponseBody;
import master.resume.model.ResumeInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;
import master.resume.vo.request.ResumeInfoAddRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huadexuan
 * @since 2023-12-09
 */
public interface ResumeInfoService extends IService<ResumeInfoDO> {

    ResponseBody<String> packagePdf(Long resumeId);

    ResponseBody<String> add(ResumeInfoAddRequest request);
}

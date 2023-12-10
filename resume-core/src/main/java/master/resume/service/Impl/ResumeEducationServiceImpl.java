package master.resume.service.Impl;

import master.resume.entity.ResponseBody;
import master.resume.model.ResumeEducationDO;
import master.resume.mapper.ResumeEducationMapper;
import master.resume.service.ResumeEducationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import master.resume.vo.request.ResumeEducationAddRequest;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huadexuan
 * @since 2023-12-09
 */
@Service
public class ResumeEducationServiceImpl extends ServiceImpl<ResumeEducationMapper, ResumeEducationDO> implements ResumeEducationService {

    @Autowired
    private ResumeEducationMapper resumeEducationMapper;

    /**
     * 新增教育经历
     * @param requestList
     * @return
     */
    @Override
    public ResponseBody<String> add(List<ResumeEducationAddRequest> requestList) {
        if (requestList == null || requestList.size() == 0) {
            return ResponseBody.fail("请至少填写一条教育经历");
        }
        for (ResumeEducationAddRequest request : requestList) {
            ResumeEducationDO resumeEducationDO = new ResumeEducationDO();
            BeanUtils.copyProperties(request, resumeEducationDO);
            resumeEducationMapper.insert(resumeEducationDO);
        }
        return ResponseBody.success("教育经历添加成功");
    }
}

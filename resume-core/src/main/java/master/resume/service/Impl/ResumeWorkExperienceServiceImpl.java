package master.resume.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import master.resume.entity.ResponseBody;
import master.resume.mapper.ResumeWorkExperienceMapper;
import master.resume.model.ResumeWorkExperienceDO;
import master.resume.service.ResumeWorkExperienceService;
import master.resume.vo.request.ResumeWorkExperienceAddRequest;
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
public class ResumeWorkExperienceServiceImpl extends ServiceImpl<ResumeWorkExperienceMapper, ResumeWorkExperienceDO> implements ResumeWorkExperienceService {

    @Autowired
    private ResumeWorkExperienceMapper resumeWorkExperienceMapper;

    @Override
    public ResponseBody<String> add(List<ResumeWorkExperienceAddRequest> requestList) {
        //判断条件
        if(requestList == null || requestList.size() == 0){
            return ResponseBody.fail("请至少填写一段工作经历");
        }
        //遍历添加
        for (ResumeWorkExperienceAddRequest request : requestList) {
            ResumeWorkExperienceDO resumeWorkExperienceDO = new ResumeWorkExperienceDO();
            BeanUtils.copyProperties(request,resumeWorkExperienceDO);
            resumeWorkExperienceMapper.insert(resumeWorkExperienceDO);
        }
        return ResponseBody.success("添加成功");
    }
}

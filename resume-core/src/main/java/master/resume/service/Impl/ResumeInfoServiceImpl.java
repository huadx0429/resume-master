package master.resume.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import master.resume.entity.ResponseBody;
import master.resume.mapper.ResumeEducationMapper;
import master.resume.mapper.ResumeInfoMapper;
import master.resume.mapper.ResumeWorkExperienceMapper;
import master.resume.model.ResumeEducationDO;
import master.resume.model.ResumeInfoDO;
import master.resume.model.ResumeWorkExperienceDO;
import master.resume.service.ResumeInfoService;
import master.resume.utils.PdfUtil;
import master.resume.vo.request.ResumeInfoAddRequest;
import master.resume.vo.response.ResumeAllInfoResponse;
import master.resume.vo.response.ResumeEducationResponse;
import master.resume.vo.response.ResumeWorkExperienceResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huadexuan
 * @since 2023-12-09
 */
@Service
@Slf4j
public class ResumeInfoServiceImpl extends ServiceImpl<ResumeInfoMapper, ResumeInfoDO> implements ResumeInfoService {


    @Autowired
    private ResumeWorkExperienceMapper resumeWorkExperienceMapper;

    @Autowired
    private ResumeEducationMapper resumeEducationMapper;

    @Autowired
    private PdfUtil pdfUtil;


    /**
     * 生成pdf简历
     * @param resumeId
     * @return
     */
    @Override
    public ResponseBody<String> packagePdf(Long resumeId) {
        //查询简历信息
        ResumeAllInfoResponse response =  this.resumeAllInfoById(resumeId);
        //生成pdf
        Map<String, Object> map = new HashMap<>();
        map.put("data", response);
        System.out.println("====开始执行转化=====");
        try {
            String filePath = "";
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                //windows
                filePath = "C:\\Users\\huade\\Desktop\\123\\" + response.getId() + ".pdf";
            } else {
                //linux
                filePath = "/home/data/resume/" + response.getId() + ".pdf";
            }
            log.info("pdf文件路径:{}", filePath);
            pdfUtil.convert(map, filePath);
            //从服务器获取链接
            String ossUrl = "http://resume.project.cn/u/" + response.getId() + ".pdf";
            return ResponseBody.success(ossUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.fail("在线简历打包失败，请联系管理员");
        }
    }

    /**
     * 新增基本信息
     * @param request
     * @return
     */
    @Override
    public ResponseBody<String> add(ResumeInfoAddRequest request) {
        ResumeInfoDO resumeInfoDO = new ResumeInfoDO();
        BeanUtils.copyProperties(request,resumeInfoDO);
        boolean save = this.save(resumeInfoDO);
        return save ? ResponseBody.success("添加成功") : ResponseBody.fail("新增失败");
    }


    /**
     * 查询简历详细信息
     * @param resumeId
     * @return
     */
    private ResumeAllInfoResponse resumeAllInfoById(Long resumeId) {
        ResumeAllInfoResponse response = new ResumeAllInfoResponse();
        //查询简历基本信息
        ResumeInfoDO resumeInfoDO = this.getById(resumeId);
        BeanUtils.copyProperties(resumeInfoDO,response);
        //查询教育经历
        List<ResumeEducationDO> resumeEducationDOS = resumeEducationMapper.selectList(new LambdaQueryWrapper<ResumeEducationDO>().eq(ResumeEducationDO::getResumeId, resumeId));
        List<ResumeEducationResponse> resumeEducationResponses = resumeEducationDOS.stream().map(resumeEducationDO -> {
            ResumeEducationResponse resumeEducationResponse = new ResumeEducationResponse();
            BeanUtils.copyProperties(resumeEducationDO,resumeEducationResponse);
            return resumeEducationResponse;
        }).collect(Collectors.toList());
        //查询工作经历
        List<ResumeWorkExperienceResponse> resumeWorkExperienceResponses = resumeWorkExperienceMapper.selectList(new LambdaQueryWrapper<ResumeWorkExperienceDO>().eq(ResumeWorkExperienceDO::getResumeId, resumeId)).stream().map(resumeWorkExperienceDO -> {
            ResumeWorkExperienceResponse resumeWorkExperienceResponse = new ResumeWorkExperienceResponse();
            BeanUtils.copyProperties(resumeWorkExperienceDO,resumeWorkExperienceResponse);
            return resumeWorkExperienceResponse;
        }).collect(Collectors.toList());
        response.setEducationExperience(resumeEducationResponses);
        response.setWorkExperience(resumeWorkExperienceResponses);
        return response;
    }
}

package master.resume.controller;


import master.resume.entity.ResponseBody;
import master.resume.mapper.ResumeWorkExperienceMapper;
import master.resume.service.ResumeWorkExperienceService;
import master.resume.vo.request.ResumeWorkExperienceAddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huadexuan
 * @since 2023-12-09
 */
@RestController
@RequestMapping("/api/resumeWorkExperience/v1")
public class ResumeWorkExperienceController {

    @Autowired
    private ResumeWorkExperienceService resumeWorkExperienceService;

    @PutMapping("/add")
    public ResponseBody<String> add(@RequestBody List<ResumeWorkExperienceAddRequest> requestList){
        return resumeWorkExperienceService.add(requestList);
    }

}


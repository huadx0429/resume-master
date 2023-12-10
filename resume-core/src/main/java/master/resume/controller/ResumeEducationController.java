package master.resume.controller;


import master.resume.entity.ResponseBody;
import master.resume.service.ResumeEducationService;
import master.resume.vo.request.ResumeEducationAddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/resumeEducation/v1/")
public class ResumeEducationController {

    @Autowired
    private ResumeEducationService resumeEducationService;

    /**
     * 新增教育经历
     */
    @PutMapping("/add")
    public ResponseBody<String> add(@RequestBody List<ResumeEducationAddRequest> requestList) {
        return resumeEducationService.add(requestList);
    }

}


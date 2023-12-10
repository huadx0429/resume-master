package master.resume.controller;


import jakarta.validation.Valid;
import master.resume.entity.ResponseBody;
import master.resume.service.ResumeInfoService;
import master.resume.vo.request.ResumeInfoAddRequest;
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
@RequestMapping("/api/resumeInfo/v1/")
public class ResumeInfoController {

    @Autowired
    private ResumeInfoService resumeInfoService;


    @PutMapping("/add")
    public ResponseBody<String> add(@RequestBody @Valid ResumeInfoAddRequest request){
        return resumeInfoService.add(request);
    }


    @PostMapping("/pdf_package")
    public ResponseBody<String> packagePdf(Long resumeId){
        return resumeInfoService.packagePdf(resumeId);
    }

}


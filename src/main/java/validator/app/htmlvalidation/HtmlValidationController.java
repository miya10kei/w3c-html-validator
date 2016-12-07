package validator.app.htmlvalidation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import validator.domain.service.HtmlValidationService;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/htmlvalidation")
@Slf4j
public class HtmlValidationController {

    @Autowired
    HtmlValidationService htmlValidationService;

    @RequestMapping("init")
    public String init() {
        return "html-validation/HtmlValidation";
    }

    @PostMapping(value = "validate", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public Resource validate(FileUploadForm fileUploadForm) {
        log.info(fileUploadForm.getFile().getOriginalFilename() + ", " + fileUploadForm.getFile().getSize());
        String filePath = null;
        try {
            filePath = htmlValidationService.validate(fileUploadForm.getFile().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FileSystemResource(new File(filePath));
    }
}

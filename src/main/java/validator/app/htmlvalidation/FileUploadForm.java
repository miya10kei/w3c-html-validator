package validator.app.htmlvalidation;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import validator.app.validation.UploadFileMaxSize;
import validator.app.validation.UploadFileNotEmpty;
import validator.app.validation.UploadFileRequired;

import java.io.Serializable;

@Data
public class FileUploadForm implements Serializable {

    @UploadFileRequired
    @UploadFileNotEmpty
    @UploadFileMaxSize
    private MultipartFile file;

}

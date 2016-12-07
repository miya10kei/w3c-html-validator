package validator.app.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UploadFileMaxSizeValidator implements ConstraintValidator<UploadFileMaxSize, MultipartFile> {

    private UploadFileMaxSize uploadFileMaxSize;

    @Override
    public void initialize(UploadFileMaxSize uploadFileMaxSize) {
        this.uploadFileMaxSize = uploadFileMaxSize;
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (uploadFileMaxSize.value() < 0 || multipartFile == null) {
            return true;
        }
        return multipartFile.getSize() <= uploadFileMaxSize.value();
    }

}

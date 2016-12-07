package validator.domain.service;

import java.io.InputStream;

public interface HtmlValidationService {

    String validate(InputStream inputStream);
}

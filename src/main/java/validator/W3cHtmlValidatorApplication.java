package validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import validator.domain.service.HtmlValidationService;

@SpringBootApplication
@Slf4j
public class W3cHtmlValidatorApplication {

	@Autowired
	HtmlValidationService validatorService;

	public static void main(String[] args) {
        SpringApplication.run(W3cHtmlValidatorApplication.class, args);
	}

	@Bean
    RestTemplate restTemplate() {
	    return new RestTemplate();
    }
}

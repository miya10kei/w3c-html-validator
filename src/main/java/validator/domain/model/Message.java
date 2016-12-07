package validator.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message implements Serializable {

    private String type;

    private String subType;

    private String messageid;

    private String message;

    private String explanation;

    private Long lastLine;

    private Long lastColumn;

}

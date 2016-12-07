package validator.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Root implements Serializable {

    private String url;

    private List<Message> messages;

//    private List<Source> sourceList;

//    private String language;

}

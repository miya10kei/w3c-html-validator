package validator.domain.model;

import com.orangesignal.csv.annotation.CsvColumn;
import com.orangesignal.csv.annotation.CsvEntity;
import lombok.Data;

@Data
@CsvEntity
public class HtmlValidationCsv {

    @CsvColumn(position = 0)
    private String url;

    @CsvColumn(position = 1)
    private String type;

    @CsvColumn(position = 2)
    private String subType;

    @CsvColumn(position = 3)
    private String messageid;

    @CsvColumn(position = 4)
    private String message;

    @CsvColumn(position = 5)
    private String explanation;

    @CsvColumn(position = 6)
    private Long lastLine;

    @CsvColumn(position = 7)
    private Long lastColumn;

}

package validator.domain.service;

import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.manager.CsvManager;
import com.orangesignal.csv.manager.CsvManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import validator.domain.model.HtmlValidationCsv;
import validator.domain.model.Message;
import validator.domain.model.Root;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class HtmlValidationServiceImpl implements HtmlValidationService {

    @Autowired
    RestOperations restOperations;

    @Value("${url.w3c.html.validation}")
    private String w3cHtmlValidationUrl;

    @Value("${path.w3c.html.output}")
    private String csvOutputFilePath;

    @Override
    public String validate(InputStream inputStream) {
        List<String> urlList = getUrlList(inputStream);
        List<Root> rootList = CommunicationValidationApi(urlList);
        List<HtmlValidationCsv> csvObjList = createCsvObject(rootList);
        return outputCsv(csvObjList);

    }

    private List<String> getUrlList(InputStream inputStream) {
        List<String> urlList = null;
        try {
            urlList = IOUtils.readLines(inputStream, "UTF-8");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return urlList;
    }

    private List<Root> CommunicationValidationApi(final List<String> urlList) {
        List<Root> rootList = new ArrayList<>();
        for (String url : urlList) {
            log.info("start communication");
            log.info("url: {}", url);
            Root root = restOperations.getForObject(
                    w3cHtmlValidationUrl + "?uri=" + url + "&output=json",
                    Root.class);
            log.info("return: {}", root.toString());
            log.info("end communication");
            rootList.add(root);
        }
        return rootList;
    }

    private List<HtmlValidationCsv> createCsvObject(final List<Root> rootList) {
        log.info("create csv object");
        List<HtmlValidationCsv> csvObjList = new ArrayList<>();
        for(final Root root : rootList) {
            for (final Message message : root.getMessages()) {
                HtmlValidationCsv htmlValidationCsv = new HtmlValidationCsv();
                BeanUtils.copyProperties(root, htmlValidationCsv);
                BeanUtils.copyProperties(message, htmlValidationCsv);
                csvObjList.add(htmlValidationCsv);
            }
        }
        log.info("complete csv object");
        return csvObjList;

    }

    private String outputCsv(final List<HtmlValidationCsv> csvList) {
        log.info("start output csv");
        CsvManager csvManager = CsvManagerFactory.newCsvManager().config(new CsvConfig(',', '"', '"'));
        try {
            if (csvList != null) {
                csvManager.save(csvList, HtmlValidationCsv.class).to(new File(csvOutputFilePath));
            } else {
                log.info("csv = null");
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        log.info("end output csv");
        return csvOutputFilePath;
    }

}

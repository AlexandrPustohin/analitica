package com.service.analytics.services.fileservice;

import com.service.analytics.services.parserxml.ParserXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
@EnableAutoConfiguration
public class FileService {

    @Value("${mypath}")
    String mypath;
    @Autowired
    ParserXML parserXML;
    //раскоментить для выполнения по расписанию!!!
    //@Scheduled(fixedRateString = "${FixedRate}")
    public  void getListFileFromDir() throws IOException {
        List<File> fileList = null;
        fileList =  new ArrayList<>();
        final File file = new File(mypath);
        if (file.isDirectory()) {
             for (String nextFilename : file.list()) {
                    //лучше проверить расширение...
                    if (nextFilename.contains(".xml"))
                        fileList.add(new File(file.getAbsolutePath() + File.separator + nextFilename));
                }
            }

        //парсим найденные файлы
        if (fileList.size()>0) {
            fileList.stream().forEach(files -> parserXML.parse(files));
            //TODO - удалить файлы
        }
    }
}

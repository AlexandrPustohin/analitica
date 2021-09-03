package com.service.analytics.services.fileservice;

import com.service.analytics.model.LoadFile;
import com.service.analytics.services.parserxml.ParserXML;
import com.service.analytics.services.saleservice.LoadFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
@EnableAutoConfiguration
public class FileService {

    @Value("${mypath}")
    String mypath;
    @Autowired
    ParserXML parserXML;
    @Autowired
    LoadFileServiceImpl loadFileService;
    //раскомментить для выполнения по расписанию!!!
    @Scheduled(fixedRateString = "${FixedRate}")
    public  void getListFileFromDir() {
        List<File> fileList = null;
        String fileLoadResult = "Успешно загружен.";
        fileList =  new ArrayList<>();
        final File file = new File(mypath);
        //грузим только из папки
        if (file.isDirectory()) {
             for (String nextFilename : file.list()) {
                    //можно получить последние четыре символа и проверить,
                    //но если будет "кривой" файл - просто не загрузиться
                    if (nextFilename.contains(".xml"))
                        fileList.add(new File(file.getAbsolutePath() + File.separator + nextFilename));
                }
            }

        //парсим найденные файлы
        if (fileList.size()>0) {
            for(File files: fileList) {
                //проверяем - былали загрузка такого файла
                boolean isLoadFile = loadFileService.isLoadFile(files.getName().toString());
                try {
                    //если нет - загружаем
                    if(!isLoadFile) parserXML.parse(files);
                
                } catch (ParserConfigurationException e) {
                    fileLoadResult = e.toString();
                } catch (IOException e) {
                    fileLoadResult = e.toString();
                } catch (SAXException e) {
                    fileLoadResult = e.toString();
                }
                //сохраняем имена файлов в БД
                if(!isLoadFile)
                loadFileService.saveFileName(new LoadFile(files.getName().toString(), new Date(), fileLoadResult));
            }
        }
    }
}

package com.service.analytics.services.saleservice;

import com.service.analytics.model.LoadFile;
import com.service.analytics.repositories.LoadFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LoadFileServiceImpl implements LoadFileService{
    @Autowired
    LoadFileRepository loadFileRepository;
    @Override
    public void saveFileName(LoadFile loadFile) {
        loadFileRepository.save(loadFile);
    }

    @Override
    public boolean isLoadFile(String fileName) {
        Optional<LoadFile> loadFile = Optional.ofNullable(loadFileRepository.findByFileName(fileName));
        if(loadFile.isPresent()) return true;
        return false;
    }
}

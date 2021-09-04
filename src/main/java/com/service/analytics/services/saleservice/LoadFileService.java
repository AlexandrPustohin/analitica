package com.service.analytics.services.saleservice;

import com.service.analytics.model.LoadFile;

public interface LoadFileService {
    void saveFileName(LoadFile loadFile);
    boolean isLoadFile(String fileName);
}

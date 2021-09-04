package com.service.analytics.repositories;

import com.service.analytics.model.LoadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoadFileRepository extends JpaRepository<LoadFile, Long> {
    LoadFile findByFileName(String filename);
}

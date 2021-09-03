package com.service.analytics.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "load_file")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoadFile {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String fileName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date  loadTime;
    private String fileLoadResult;



    public LoadFile(String fileName, Date loadTime, String fileLoadResult) {
        this.fileName = fileName;
        this.loadTime = loadTime;
        this.fileLoadResult = fileLoadResult;
    }
}

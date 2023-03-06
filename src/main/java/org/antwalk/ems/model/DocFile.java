package org.antwalk.ems.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class DocFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    @Column(columnDefinition = "ENUM('IMG','PDF')")
    private String fileType;
    
    @Column
    private String fileName;

    @Column
    private int fileNum;

    public DocFile() {
    }

    public DocFile(Long fileId, String fileType, String fileName, int fileNum) {
        this.fileId = fileId;
        this.fileType = fileType;
        this.fileName = fileName;
        this.fileNum = fileNum;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileNum() {
        return fileNum;
    }

    public void setFileNum(int fileNum) {
        this.fileNum = fileNum;
    }

    
}

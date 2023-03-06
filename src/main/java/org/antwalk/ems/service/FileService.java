package org.antwalk.ems.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.antwalk.ems.model.DocFile;
import org.antwalk.ems.repository.DocFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    
    @Value("${storage.location}")
    private String location;

    @Autowired
    private DocFileRepository docFileRepository;

    public DocFile save(MultipartFile file) throws IOException{
        String fileName = file.getOriginalFilename();
        Path directory = Paths.get(location);
        if (!Files.exists(directory)){
            Files.createDirectories(directory);
        }
        DocFile docFile = new DocFile();
        docFile.setFileName(fileName);
        DocFile updatedDocFile = docFileRepository.save(docFile);
        String newFileName = updatedDocFile.getFileId() + fileName.substring(fileName.lastIndexOf("."));
        Path newPath = Paths.get(location + "/" + newFileName);
        InputStream inputStream = file.getInputStream();
        Files.copy(inputStream, newPath);
        inputStream.close();

        return docFile;
    }
}

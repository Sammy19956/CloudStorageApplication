package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FilesService {

    FilesMapper filesMapper;

    // Constructor
    public FilesService(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;
    }

    public Files getFile(Integer fileId) {
        return filesMapper.selectFileById(fileId);
    }

    public List<Files> getAllFiles(Integer userId) {
        return filesMapper.selectAllFiles(userId);
    }

    public Integer saveFile(MultipartFile multipartFile, Integer userId) throws IOException {
        Files file = new Files();

        file.setUserId(userId);
        file.setFileName(multipartFile.getOriginalFilename());
        file.setContentType(multipartFile.getContentType());
        file.setFileSize(Long.toString(multipartFile.getSize()));
        file.setFileData(multipartFile.getBytes());

        return filesMapper.saveFile(file);
    }

    public void deleteFile(Integer fileId) {
        filesMapper.deleteFile(fileId);
    }

    public Boolean doesFileExists(MultipartFile multipartFile) {
        return filesMapper.getFileByName(multipartFile.getOriginalFilename()) != null;
    }
}
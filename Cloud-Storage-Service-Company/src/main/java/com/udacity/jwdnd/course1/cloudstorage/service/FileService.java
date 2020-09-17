package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;
    private UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public List<File> getAllUserFiles(int userId) {
        return fileMapper.getAllUserFiles(userId);
    }

    public void createFile(MultipartFile fileUpload, String username) {
        int userId = userMapper.getUserIdByUsername(username);
        File newFile = new File(null, fileUpload.getOriginalFilename(),
                fileUpload.getContentType(),
                String.valueOf(fileUpload.getSize()),
                userId,
                fileUpload.getBytes());
        fileMapper.insertFile(newFile);
    }

}

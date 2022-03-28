package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;
    public final UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }


    public int addFile(MultipartFile file, Authentication authentication) throws IOException {
        User currentUser = userMapper.getUser(authentication.getName());
        Integer userId = currentUser.getUserId();
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        return fileMapper.addFile(new File(null, fileName, contentType, file.getSize(),  userId, file.getBytes()));
    }

    public List<File> getAllFiles(Integer userId){
//        System.out.println(fileMapper.getAllFiles(userId));
        return fileMapper.getAllFiles(userId);
    }

    public File getFileByName(String fileName){
        return fileMapper.getFileByName(fileName);
    }

    public void deleteFile(Integer fileId){
        fileMapper.deleteFile(fileId);
    }

    public File downloadFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }
}


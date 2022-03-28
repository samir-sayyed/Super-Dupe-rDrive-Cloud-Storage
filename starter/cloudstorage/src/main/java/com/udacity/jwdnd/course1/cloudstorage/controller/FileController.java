package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;


@Controller
@RequestMapping("/file")
public class FileController {

//     Logger logger = LoggerFactory.getLogger(FileController.class);

    private final FileService fileService;
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/add")
    public String addFile(@RequestParam("fileUpload") MultipartFile file, Model model, Authentication authentication) throws IOException {
       String error = null;
       String success = null;

       if (file.isEmpty()){
           model.addAttribute("error", "Ohh noo! you are trying to upload empty file");
           return "result";
       }

       String fileName = file.getOriginalFilename();
//        System.out.println(fileName);
        File getFile = fileService.getFileByName(fileName);
//        System.out.println(getFile);
        if (getFile == null){
            try {
                fileService.addFile(file, authentication );
                success = "Ola! File uploaded successfully";
            }catch (Exception e ){
                System.out.println(e.getMessage());
                error = "Error in uploading file";
            }
        }else {
            error = "Ohh! file already exist";
        }


       if (error == null){
           model.addAttribute("success", success);
       }else {
           model.addAttribute("error", error);
       }
        return "result";
    }


    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model){
        try {
            fileService.deleteFile(fileId);
            model.addAttribute("success", "File deleted!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "result";
    }


    @GetMapping("/download/{fileId}")
    public ResponseEntity downloadView(@PathVariable Integer fileId) {
        File file = fileService.downloadFile(fileId);

//        Reference- https://stackoverflow.com/questions/35680932/download-a-file-from-spring-boot-rest-service
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(file.getFileData()));
    }
}


package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    private final UsersService usersService;
    private final FilesService filesService;

    // Constructor
    public FileController(UsersService usersService, FilesService filesService) {
        this.usersService = usersService;
        this.filesService = filesService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile,
                             Authentication auth,
                             Model model) throws IOException {
        Boolean isOk;
        String msg = null;

        if (multipartFile.isEmpty()) {
            isOk = false;
            msg = "The file uploaded is empty";
        } else {
            if (!filesService.doesFileExists(multipartFile)) {
                isOk = filesService.saveFile(multipartFile,
                                            usersService.getLoggedInUserId(auth)) > 0;
            } else {
                isOk = false;
                msg = multipartFile.getOriginalFilename() + " file already exist.";
            }
        }

        return "redirect:/result?isOk=" + isOk+"&message="+msg;
    }

    @GetMapping("/view")
    public ResponseEntity<InputStreamResource> viewFile(@RequestParam("fileId") Integer fileId) {
        Files files = filesService.getFile(fileId);

        // get the file data
        InputStreamResource inputStrResource = new InputStreamResource(new ByteArrayInputStream(files.getFileData()));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:fileName=" + files.getFileName())
                .contentType(MediaType.parseMediaType(files.getContentType()))
                .body(inputStrResource);
    }

    @GetMapping("/delete")
    public String deleteFile(@RequestParam("fileId") Integer fileId) {
        String msg = null;
        Boolean isOk = fileId > 0;
        if (isOk) {
            filesService.deleteFile(fileId);
        }else{
            msg = fileId +" was not deleted.";
        }
        return "redirect:/result?isOk=" + isOk+"&message="+msg;
    }
}

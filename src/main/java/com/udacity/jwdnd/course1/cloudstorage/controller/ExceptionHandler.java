package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequestMapping("/result")
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(MaxUploadSizeExceededException exception) {
        Boolean isOk = false;
        String msg = "File could not be uploaded because it is larger than the expected size.";
        return "redirect:/result?isOk=" + isOk+"&message="+msg;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String exception(Exception exception) {
        Boolean isOk = false;
        String msg = "Error uploading file";
        return "redirect:/result?isOk=" + isOk+"&message="+msg;
    }
}
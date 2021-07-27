package com.example.EcoConsciousApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class LocalDocumentController {

    private static String UPLOADED_PATH = "E:\\EnigmaCamp\\Final Project\\";

    @GetMapping("/localdocs")
    public String index(){
        return "index";
    }

    @PostMapping("/localdocs/uploads")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){

        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:status";
        }

        try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_PATH + file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message", "You successfully upload the file " + file.getOriginalFilename());
        } catch (IOException e){
            e.printStackTrace();
        }
        return "redirect:status";
    }

    @GetMapping("/localdocs/status")
    public String uploadStatus(){
        return "status";
    }

}

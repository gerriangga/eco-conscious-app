package com.example.EcoConsciousApp.controller;

import com.example.EcoConsciousApp.entity.Doc;
import com.example.EcoConsciousApp.service.impl.DocStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ServerDocumentController {

    @Autowired
    DocStorageService docStorageService;

    @GetMapping("/docs")
    public String get(Model model){
        List<Doc> docs = docStorageService.getFiles();
        model.addAttribute("docs", docs);
        return "doc";
    }

    @PostMapping("/docs/uploadFiles")
    public String uploadMultipleFiles(@RequestParam("files")MultipartFile[] files){
        for (MultipartFile file : files) {
            docStorageService.saveFile(file);
        }
        return "redirect:/docs";
    }

    @GetMapping("/docs/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
        Doc doc = docStorageService.getFile(fileId).get();
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(doc.getDocType()))
                                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
                                    .body(new ByteArrayResource(doc.getData()));
    }
}

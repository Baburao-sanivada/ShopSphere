package com.backend.ShopSphere;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.ShopSphere.Logs.LogController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FileUploadController {
  Logger logger 
  = LoggerFactory.getLogger(LogController.class);

  @Autowired
  private ImageService imageService;

  @PostMapping("/upload")
  @ResponseBody
  public ResponseEntity<String> uploadFile(@RequestPart MultipartFile img) {
    try {
      String uploadDirectory = "shopsphere-backend/src/main/java/com/backend/ShopSphere/Images";
      String uniqName = imageService.saveImageToStorage(uploadDirectory, img);
      return ResponseEntity.ok("File uploaded successfully"+uniqName);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
    }
  }

  @GetMapping("/getImages/{adsId}")
    public ResponseEntity<byte[]> getImages(@PathVariable String adsId) throws IOException {
      
      try {
        String imageDirectory = "shopsphere-backend/src/main/java/com/backend/ShopSphere/Images/";

        byte[] imageBytes = imageService.getImage(imageDirectory, imageDirectory+adsId);
        logger.info(imageDirectory+imageBytes);
        // Respond with the image data and an OK status code
        return ResponseEntity.ok().body(imageBytes);
      }catch (Exception e) {
          // Handle exceptions and provide appropriate error responses
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}


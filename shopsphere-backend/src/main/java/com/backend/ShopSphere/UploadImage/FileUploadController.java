package com.backend.ShopSphere.UploadImage;

import com.backend.ShopSphere.CommonUtility.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@RestController
public class FileUploadController {
  Logger logger
  = LoggerFactory.getLogger(FileUploadController.class);

  @Autowired
  private ImageService imageService;

  @PostMapping("/upload")
  @ResponseBody
  public ResponseEntity<ApiResponse> uploadFile(@RequestPart MultipartFile product) {
    System.out.println("Upload Method Called");
    try {
      String uploadDirectory = "shopsphere-backend/src/main/resources/static/Images";
      String uniqName = imageService.saveImageToStorage(uploadDirectory, product);
      return new ResponseEntity<>(new ApiResponse("/Images/"+uniqName,true),HttpStatus.OK );
    } catch (IOException e) {
      e.printStackTrace();
      return new ResponseEntity<>(new ApiResponse("Unable to upload",false),HttpStatus.OK );
    }
  }

  @GetMapping("/getImages/{adsId}")
  public ResponseEntity<byte[]> getImages(@PathVariable String adsId) throws IOException {
    
    try {
      String imageDirectory = "shopsphere-backend/src/main/java/com/backend/ShopSphere/Images/";

      byte[] imageBytes = imageService.getImage(imageDirectory, adsId);
      logger.info(imageDirectory+imageBytes);
      // Respond with the image data and an OK status code
      return ResponseEntity.ok().body(imageBytes);
    }catch (Exception e) {
        // Handle exceptions and provide appropriate error responses
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
      }
  }

  @DeleteMapping("/delete/{adsId}")
  public ResponseEntity<String> DeleteImage(@PathVariable String adsId) throws IOException {
    
    try {
      String imageDirectory = "shopsphere-backend/src/main/java/com/backend/ShopSphere/Images/";

      String response = imageService.deleteImage(imageDirectory, adsId);
      logger.info(response);
      // Respond with the image data and an OK status code
      return ResponseEntity.ok().body(response);
    }catch (Exception e) {
        // Handle exceptions and provide appropriate error responses
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete");
      }
  }

}


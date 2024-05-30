package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.ImageDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageDataService imageService;

    public ImageController(ImageDataService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<Object> uploadImage(@RequestParam("file")MultipartFile multipartFile)
            throws IOException {
        String image = imageService.uploadImage(multipartFile);

        return ResponseEntity.ok("File \"" + image + "\" has been uploaded");
    }

    }

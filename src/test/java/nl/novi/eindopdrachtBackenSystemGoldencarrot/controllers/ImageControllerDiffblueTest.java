package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.ImageDataFile;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.ImageDataRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.ImageDataService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class ImageControllerDiffblueTest {
    /**
     * Method under test: {@link ImageController#uploadImage(MultipartFile)}
     */
    @Test
    void testUploadImage() throws IOException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ImageDataFile imageDataFile = new ImageDataFile();
        imageDataFile.setImageData("AXAXAXAX".getBytes("UTF-8"));
        imageDataFile.setName("Name");
        imageDataFile.setType("Type");
        ImageDataRepository imageRepos = mock(ImageDataRepository.class);
        when(imageRepos.save(Mockito.<ImageDataFile>any())).thenReturn(imageDataFile);
        Optional<ImageDataFile> emptyResult = Optional.empty();
        when(imageRepos.findByName(Mockito.<String>any())).thenReturn(emptyResult);
        ImageController imageController = new ImageController(new ImageDataService(imageRepos));

        // Act
        ResponseEntity<Object> actualUploadImageResult = imageController
                .uploadImage(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Assert
        verify(imageRepos).findByName(eq(""));
        verify(imageRepos).save(isA(ImageDataFile.class));
        assertEquals("File \"Name\" has been uploaded", actualUploadImageResult.getBody());
        assertEquals(200, actualUploadImageResult.getStatusCodeValue());
        assertTrue(actualUploadImageResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ImageController#uploadImage(MultipartFile)}
     */
    @Test
    void testUploadImage2() throws IOException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ImageDataService imageService = mock(ImageDataService.class);
        when(imageService.uploadImage(Mockito.<MultipartFile>any())).thenReturn("Upload Image");
        ImageController imageController = new ImageController(imageService);

        // Act
        ResponseEntity<Object> actualUploadImageResult = imageController
                .uploadImage(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Assert
        verify(imageService).uploadImage(isA(MultipartFile.class));
        assertEquals("File \"Upload Image\" has been uploaded", actualUploadImageResult.getBody());
        assertEquals(200, actualUploadImageResult.getStatusCodeValue());
        assertTrue(actualUploadImageResult.getHeaders().isEmpty());
    }
}

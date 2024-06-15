package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.ImageDataFile;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.ImageDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ImageDataService.class})
@ExtendWith(SpringExtension.class)
class ImageDataServiceDiffblueTest {
    @MockBean
    private ImageDataRepository imageDataRepository;

    @Autowired
    private ImageDataService imageDataService;

    /**
     * Method under test: {@link ImageDataService#uploadImage(MultipartFile)}
     */
    @Test
    void testUploadImage() throws IOException {
        // Arrange
        ImageDataFile imageDataFile = new ImageDataFile();
        imageDataFile.setImageData("AXAXAXAX".getBytes("UTF-8"));
        imageDataFile.setName("Name");
        imageDataFile.setType("Type");
        Optional<ImageDataFile> ofResult = Optional.of(imageDataFile);
        when(imageDataRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> imageDataService
                .uploadImage(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(imageDataRepository).findByName(eq(""));
    }

    /**
     * Method under test: {@link ImageDataService#uploadImage(MultipartFile)}
     */
    @Test
    void testUploadImage2() throws IOException {
        // Arrange
        ImageDataFile imageDataFile = new ImageDataFile();
        imageDataFile.setImageData("AXAXAXAX".getBytes("UTF-8"));
        imageDataFile.setName("Name");
        imageDataFile.setType("Type");
        when(imageDataRepository.save(Mockito.<ImageDataFile>any())).thenReturn(imageDataFile);
        Optional<ImageDataFile> emptyResult = Optional.empty();
        when(imageDataRepository.findByName(Mockito.<String>any())).thenReturn(emptyResult);

        // Act
        String actualUploadImageResult = imageDataService
                .uploadImage(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Assert
        verify(imageDataRepository).findByName(eq(""));
        verify(imageDataRepository).save(isA(ImageDataFile.class));
        assertEquals("Name", actualUploadImageResult);
    }

    /**
     * Method under test: {@link ImageDataService#uploadImage(MultipartFile)}
     */
    @Test
    void testUploadImage3() throws IOException {
        // Arrange
        when(imageDataRepository.findByName(Mockito.<String>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> imageDataService
                .uploadImage(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(imageDataRepository).findByName(eq(""));
    }

}

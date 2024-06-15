package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ImageDataFileDiffblueTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link ImageDataFile#setImageData(byte[])}
     *   <li>{@link ImageDataFile#setName(String)}
     *   <li>{@link ImageDataFile#setType(String)}
     *   <li>{@link ImageDataFile#getImageData()}
     *   <li>{@link ImageDataFile#getName()}
     *   <li>{@link ImageDataFile#getType()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() throws UnsupportedEncodingException {
        // Arrange
        ImageDataFile imageDataFile = new ImageDataFile();
        byte[] imageData = "AXAXAXAX".getBytes("UTF-8");

        // Act
        imageDataFile.setImageData(imageData);
        imageDataFile.setName("Name");
        imageDataFile.setType("Type");
        byte[] actualImageData = imageDataFile.getImageData();
        String actualName = imageDataFile.getName();

        // Assert that nothing has changed
        assertEquals("Name", actualName);
        assertEquals("Type", imageDataFile.getType());
        assertSame(imageData, actualImageData);
    }
}

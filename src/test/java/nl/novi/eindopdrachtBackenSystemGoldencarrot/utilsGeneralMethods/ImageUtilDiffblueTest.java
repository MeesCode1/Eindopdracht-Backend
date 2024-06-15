package nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImageUtilDiffblueTest {
    /**
     * Method under test: {@link ImageUtil#compressImage(byte[])}
     */
    @Test
    void testCompressImage() throws UnsupportedEncodingException {
        // Arrange, Act and Assert
        assertArrayEquals(new byte[]{'x', -38, 's', -116, 'p', 4, 'C', 0, '\n', -100, 2, 'e'},
                ImageUtil.compressImage("AXAXAXAX".getBytes("UTF-8")));
    }

    /**
     * Method under test: {@link ImageUtil#decompressImage(byte[])}
     */
    @Test
    void testDecompressImage() throws UnsupportedEncodingException {
        // Arrange, Act and Assert
        assertThrows(RuntimeException.class, () -> ImageUtil.decompressImage("AXAXAXAX".getBytes("UTF-8")));
    }
}

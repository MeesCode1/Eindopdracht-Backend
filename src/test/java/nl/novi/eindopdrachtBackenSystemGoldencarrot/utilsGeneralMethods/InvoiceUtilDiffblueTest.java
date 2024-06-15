package nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InvoiceUtilDiffblueTest {
    /**
     * Method under test: {@link InvoiceUtil#compressInvoice(byte[])}
     */
    @Test
    void testCompressInvoice() throws UnsupportedEncodingException {
        // Arrange, Act and Assert
        assertArrayEquals(new byte[]{'x', -38, 's', -116, 'p', 4, 'C', 0, '\n', -100, 2, 'e'},
                InvoiceUtil.compressInvoice("AXAXAXAX".getBytes("UTF-8")));
    }

    /**
     * Method under test: {@link InvoiceUtil#decompressInvoice(byte[])}
     */
    @Test
    void testDecompressInvoice() throws UnsupportedEncodingException {
        // Arrange, Act and Assert
        assertThrows(RuntimeException.class, () -> InvoiceUtil.decompressInvoice("AXAXAXAX".getBytes("UTF-8")));
    }
}

package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.InvoiceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {InvoiceController.class})
@ExtendWith(SpringExtension.class)
class InvoiceControllerDiffblueTest {
    @Autowired
    private InvoiceController invoiceController;

    @MockBean
    private InvoiceService invoiceService;

    /**
     * Method under test: {@link InvoiceController#downloadInvoices()}
     */
    @Test
    void testDownloadInvoices() throws Exception {

        when(invoiceService.getAllInvoices()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/invoices");

        MockMvcBuilders.standaloneSetup(invoiceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/zip"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "PK\u0005\u0006\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000"));
    }

    /**
     * Method under test:
     * {@link InvoiceController#downloadInvoicesFromCustomer(String)}
     */
    @Test
    void testDownloadInvoicesFromCustomer() throws Exception {
        // Arrange
        when(invoiceService.getInvoicesByCustomer(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/invoices/customer/{customerCompany}",
                "Customer Company");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(invoiceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/zip"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "PK\u0005\u0006\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000"));
    }


    /**
     * Method under test: {@link InvoiceController#getInvoice(Long)}
     */
    @Test
    void testGetInvoice() throws Exception {
        // Arrange
        when(invoiceService.getInvoiceFromOrder(Mockito.<Long>any())).thenReturn("AXAXAXAX".getBytes("UTF-8"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/invoices/{orderID}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(invoiceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/pdf"))
                .andExpect(MockMvcResultMatchers.content().string("AXAXAXAX"));
    }

    /**
     * Method under test: {@link InvoiceController#getInvoice(Long)}
     */
    @Test
    void testGetInvoice2() throws Exception {
        // Arrange
        when(invoiceService.getInvoiceFromOrder(Mockito.<Long>any())).thenReturn("AXAXAXAX".getBytes("UTF-8"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/invoices/{orderID}", 1L);
        requestBuilder.accept("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(invoiceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/pdf"))
                .andExpect(MockMvcResultMatchers.content().string("AXAXAXAX"));
    }
}

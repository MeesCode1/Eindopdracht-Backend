package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Customer;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Invoice;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Order;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.ImageDataRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.InvoiceRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.OrderRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.ImageUtil;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.InvoiceUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {InvoiceService.class})
@ExtendWith(SpringExtension.class)
class InvoiceServiceDiffblueTest {
    @MockBean
    private InvoiceUtil invoiceUtil;

    @MockBean
    private ImageDataService imageDataService;

    @MockBean
    private ImageUtil imageUtil;

    @MockBean
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceService invoiceService;

    @MockBean
    private OrderRepository orderRepository;

    /**
     * Method under test: {@link InvoiceService#generateInvoicePdf(Long)}
     */
    @Test
    void testGenerateInvoicePdf() throws UnsupportedEncodingException {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setBankAccount("3");
        customer2.setCompany("Company");
        customer2.setDob(LocalDate.of(1970, 1, 1));
        customer2.setEmailAddress("42 Main St");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setOrders(new ArrayList<>());
        customer2.setPhoneNumber("6625550144");

        Customer customer3 = new Customer();
        customer3.setAddress("42 Main St");
        customer3.setBankAccount("3");
        customer3.setCompany("Company");
        customer3.setDob(LocalDate.of(1970, 1, 1));
        customer3.setEmailAddress("42 Main St");
        customer3.setFirstName("Jane");
        customer3.setId(1L);
        customer3.setLastName("Doe");
        customer3.setOrders(new ArrayList<>());
        customer3.setPhoneNumber("6625550144");

        Invoice invoice = new Invoice();
        invoice.setCustomer(new Customer());
        invoice.setCustomerCompany("Customer Name");
        invoice.setId(1L);
        invoice.setInvoiceData("AXAXAXAX".getBytes("UTF-8"));
        invoice.setOrder(new Order());
        invoice.setOrderNumber(10);

        Order order = new Order();
        order.setCustomer(customer3);
        order.setId(1L);
        order.setInvoice(invoice);
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderTime("Order Time");
        order.setProducts(new ArrayList<>());
        order.setTotalPriceInEur(10.0d);

        Invoice invoice2 = new Invoice();
        invoice2.setCustomer(customer2);
        invoice2.setCustomerCompany("Customer Name");
        invoice2.setId(1L);
        invoice2.setInvoiceData("AXAXAXAX".getBytes("UTF-8"));
        invoice2.setOrder(order);
        invoice2.setOrderNumber(10);

        Order order2 = new Order();
        order2.setCustomer(customer);
        order2.setId(1L);
        order2.setInvoice(invoice2);
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderTime("Order Time");
        order2.setProducts(new ArrayList<>());
        order2.setTotalPriceInEur(10.0d);
        Optional<Order> ofResult = Optional.of(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(imageDataService.getImage(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> invoiceService.generateInvoicePdf(1L));
        verify(orderRepository).findById(eq(1L));
        verify(imageDataService).getImage(eq("${invoice.image.toppage.name}"));
    }

    /**
     * Method under test: {@link InvoiceService#getInvoiceFromOrder(Long)}
     */
    @Test
    void testGetInvoiceFromOrder() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Invoice invoice = mock(Invoice.class);
        when(invoice.getInvoiceData()).thenThrow(new RuntimeException("foo"));
        Optional<Invoice> ofResult = Optional.of(invoice);
        InvoiceRepository repos = mock(InvoiceRepository.class);
        when(repos.findByOrderNumber(Mockito.<Long>any())).thenReturn(ofResult);
        OrderRepository oRepos = mock(OrderRepository.class);
        ImageDataService imageService = new ImageDataService(mock(ImageDataRepository.class));

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> (new InvoiceService(repos, oRepos, imageService, new InvoiceUtil())).getInvoiceFromOrder(1L));
        verify(invoice).getInvoiceData();
        verify(repos).findByOrderNumber(eq(1L));
    }


    /**
     * Method under test: {@link InvoiceService#getAllInvoices()}
     */
    @Test
    void testGetAllInvoices() {
        // Arrange
        when(invoiceRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<byte[]> actualAllInvoices = invoiceService.getAllInvoices();

        // Assert
        verify(invoiceRepository).findAll();
        assertTrue(actualAllInvoices.isEmpty());
    }

    /**
     * Method under test: {@link InvoiceService#getAllInvoices()}
     */
    @Test
    void testGetAllInvoices2() {
        // Arrange
        when(invoiceRepository.findAll()).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> invoiceService.getAllInvoices());
        verify(invoiceRepository).findAll();
    }

    /**
     * Method under test: {@link InvoiceService#getInvoicesByCustomer(String)}
     */
    @Test
    void testGetInvoicesByCustomer() {
        // Arrange
        when(invoiceRepository.findByCustomer_Company(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<byte[]> actualInvoicesByCustomer = invoiceService.getInvoicesByCustomer("Customer Company");

        // Assert
        verify(invoiceRepository).findByCustomer_Company(eq("Customer Company"));
        assertTrue(actualInvoicesByCustomer.isEmpty());
    }

    /**
     * Method under test: {@link InvoiceService#getInvoicesByCustomer(String)}
     */
    @Test
    void testGetInvoicesByCustomer2() {
        // Arrange
        when(invoiceRepository.findByCustomer_Company(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> invoiceService.getInvoicesByCustomer("Customer Company"));
        verify(invoiceRepository).findByCustomer_Company(eq("Customer Company"));
    }

    /**
     * Method under test: {@link InvoiceService#getTextBold(String)}
     */
    @Test
    void testGetTextBold() {
        // Arrange and Act
        Text actualTextBold = InvoiceService.getTextBold("String");

        // Assert
        assertEquals("String", actualTextBold.getText());
        assertNull(actualTextBold.getHeight());
        assertEquals((byte) 6, actualTextBold.getRole().getType());
        assertTrue(actualTextBold.isEmpty());
    }

    /**
     * Method under test: {@link InvoiceService#getHeaderTextCell(String)}
     */
    @Test
    void testGetHeaderTextCell() {
        // Arrange and Act
        Cell actualHeaderTextCell = InvoiceService.getHeaderTextCell("42");

        // Assert
        IElement getResult = actualHeaderTextCell.getChildren().get(0);
        IElement getResult2 = ((Paragraph) getResult).getChildren().get(0);
        assertEquals("42", ((Text) getResult2.getRenderer().getModelElement()).getText());
        assertNull(actualHeaderTextCell.getHeight());
        assertNull(((Paragraph) getResult).getHeight());
        assertNull(((Text) getResult2).getHeight());
        assertEquals(1, actualHeaderTextCell.getColspan());
        assertEquals(1, actualHeaderTextCell.getRowspan());
        assertEquals((byte) 6, actualHeaderTextCell.getRole().getType());
        assertEquals((byte) 6,
                ((Paragraph) ((Paragraph) getResult.getRenderer().getModelElement()).getRenderer().getModelElement()).getRole()
                        .getType());
        assertEquals((byte) 6, ((Text) getResult2.getRenderer().getModelElement()).getRole().getType());
        assertFalse(actualHeaderTextCell.isEmpty());
        assertFalse(((Paragraph) ((Paragraph) getResult.getRenderer().getModelElement()).getRenderer().getModelElement())
                .isEmpty());
        assertTrue(((Text) getResult2.getRenderer().getModelElement()).isEmpty());
    }

    /**
     * Method under test: {@link InvoiceService#getHeaderTextCell(String)}
     */
    @Test
    void testGetHeaderTextCell2() {
        // Arrange and Act
        Cell actualHeaderTextCell = InvoiceService.getHeaderTextCell("Header Text Value");

        // Assert
        IElement getResult = actualHeaderTextCell.getChildren().get(0);
        IElement getResult2 = ((Paragraph) getResult).getChildren().get(0);
        assertEquals("Header Text Value", ((Text) getResult2.getRenderer().getModelElement()).getText());
        assertNull(actualHeaderTextCell.getHeight());
        assertNull(((Paragraph) getResult).getHeight());
        assertNull(((Text) getResult2).getHeight());
        assertEquals(1, actualHeaderTextCell.getColspan());
        assertEquals(1, actualHeaderTextCell.getRowspan());
        assertEquals((byte) 6, actualHeaderTextCell.getRole().getType());
        assertEquals((byte) 6,
                ((Paragraph) ((Paragraph) getResult.getRenderer().getModelElement()).getRenderer().getModelElement()).getRole()
                        .getType());
        assertEquals((byte) 6, ((Text) getResult2.getRenderer().getModelElement()).getRole().getType());
        assertFalse(actualHeaderTextCell.isEmpty());
        assertFalse(((Paragraph) ((Paragraph) getResult.getRenderer().getModelElement()).getRenderer().getModelElement())
                .isEmpty());
        assertTrue(((Text) getResult2.getRenderer().getModelElement()).isEmpty());
    }

    /**
     * Method under test: {@link InvoiceService#getHeaderTextCellValue(String)}
     */
    @Test
    void testGetHeaderTextCellValue() {
        // Arrange and Act
        Cell actualHeaderTextCellValue = InvoiceService.getHeaderTextCellValue("42");

        // Assert
        IElement getResult = actualHeaderTextCellValue.getChildren().get(0);
        IElement getResult2 = ((Paragraph) getResult).getChildren().get(0);
        assertEquals("42", ((Text) getResult2.getRenderer().getModelElement()).getText());
        assertNull(actualHeaderTextCellValue.getHeight());
        assertNull(((Paragraph) getResult).getHeight());
        assertNull(((Text) getResult2).getHeight());
        assertEquals(1, actualHeaderTextCellValue.getColspan());
        assertEquals(1, actualHeaderTextCellValue.getRowspan());
        assertEquals((byte) 6, actualHeaderTextCellValue.getRole().getType());
        assertEquals((byte) 6,
                ((Paragraph) ((Paragraph) getResult.getRenderer().getModelElement()).getRenderer().getModelElement()).getRole()
                        .getType());
        assertEquals((byte) 6, ((Text) getResult2.getRenderer().getModelElement()).getRole().getType());
        assertFalse(actualHeaderTextCellValue.isEmpty());
        assertFalse(((Paragraph) ((Paragraph) getResult.getRenderer().getModelElement()).getRenderer().getModelElement())
                .isEmpty());
        assertTrue(((Text) getResult2.getRenderer().getModelElement()).isEmpty());
    }
}

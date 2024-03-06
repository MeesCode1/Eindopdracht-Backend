package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Order;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.InvoiceService;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.OrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invoices")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service){
        this.service = service;
    }

    @GetMapping("/{orderID}")
    public ResponseEntity<byte[]> getInvoice(@PathVariable Long orderID) {

        byte[] invoicePdfBytes = service.GenerateInvoicePdf(orderID);

        if (invoicePdfBytes == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "invoice.pdf");

        return new ResponseEntity<>(invoicePdfBytes, headers, HttpStatus.OK);
    }


    @GetMapping("/download/{orderID}")
    public ResponseEntity<byte[]> downloadInvoice(@PathVariable Long orderID) {

        byte[] invoicePdfBytes = service.GenerateInvoicePdf(orderID);

        if (invoicePdfBytes == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String fileName = "invoice-" + orderID + ".pdf";
        headers.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(invoicePdfBytes, headers, HttpStatus.OK);
    }
}



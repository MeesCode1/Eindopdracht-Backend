package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.ImageDataFile;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.InvoiceService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("invoices")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }


    @GetMapping("/{orderID}")
    public ResponseEntity<byte[]> getInvoice(@PathVariable Long orderID) {

        byte[] invoicePdfBytes = service.getInvoiceFromOrder(orderID);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String fileName = "invoice-" + orderID + ".pdf";
        headers.setContentDispositionFormData("inline", fileName);

        return new ResponseEntity<>(invoicePdfBytes, headers, HttpStatus.OK);
    }

    @GetMapping(produces = "application/zip")
    public ResponseEntity<byte[]> downloadInvoices() throws IOException {

        List<byte[]> invoicesData = service.getAllInvoices();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        for (int i = 0; i < invoicesData.size(); i++) {
            ZipEntry zipEntry = new ZipEntry("invoice-insight" + (i + 1) + ".pdf");
            zipEntry.setSize(invoicesData.get(i).length);
            zos.putNextEntry(zipEntry);
            zos.write(invoicesData.get(i));
            zos.closeEntry();
        }
        zos.close();
        byte[] zipBytes = baos.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/zip"));
        headers.setContentDispositionFormData("inline", "invoices-insight.zip");

        return new ResponseEntity<>(zipBytes, headers, HttpStatus.OK);
    }

    @GetMapping(value="/customer/{customerCompany}", produces = "application/zip")
    public ResponseEntity<byte[]> downloadInvoicesFromCustomer(@PathVariable String customerCompany)
            throws IOException {

        List<byte[]> invoicesData = service.getInvoicesByCustomer(customerCompany);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        for (int i = 0; i < invoicesData.size(); i++) {
            ZipEntry zipEntry = new ZipEntry("invoice-insight" + (i + 1) + ".pdf");
            zipEntry.setSize(invoicesData.get(i).length);
            zos.putNextEntry(zipEntry);
            zos.write(invoicesData.get(i));
            zos.closeEntry();
        }
        zos.close();
        byte[] zipBytes = baos.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/zip"));
        headers.setContentDispositionFormData("inline", "invoices-insight.zip");

        return new ResponseEntity<>(zipBytes, headers, HttpStatus.OK);
    }

}



package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Order;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.OrderItemLine;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.InvoiceRepository;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.OrderRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    final String ourCompany = "The Golden Carrot";
    final String ourAdress = "Industrieweg 338\n3455DC Utrecht";
    final String ourPhoneNumber = "+3130 444 555 20";
    final String ourIBAN = "NL20ABNA0005623417";
    final String ourTaxNumber = "815247011";


    private final OrderRepository oRepos;


    public InvoiceService(OrderRepository oRepos) throws FileNotFoundException {
        this.oRepos = oRepos;
    }


    public byte[] GenerateInvoicePdf(Long orderId){

    Order order = oRepos.findById(orderId).orElseThrow(() -> new ResourceNotFoundException
                ("order not found"));
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(outputStream);

        PdfDocument pdfDocument = new PdfDocument(writer);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);

        float twocol1 = 280f;
        float[] twocolwidth1 = {twocol1, twocol1};
        float twocol = 200f;
        float[] twoColumnWidth = {twocol, twocol};

        String imageSrc = "C:\\Users\\mees\\Downloads\\eindopdrachtBackenSystemGoldencarrot\\" +
        "eindopdrachtBackenSystemGoldencarrot\\src\\images\\goldencarrotImageTopPageInv.jpg";
        ImageData imageDate = ImageDataFactory.create(imageSrc);
        Image image1 = new Image(imageDate);

        Table overArchingTable1 = new Table(twocolwidth1);

        overArchingTable1.addCell(new Cell().add(image1).setBorder(Border.NO_BORDER));

        Table nestedTable = new Table(new float[]{twocol1 / 2, twocol1 / 2});
        nestedTable.addCell(getHeaderTextCell("GC Company: "));
        nestedTable.addCell(getHeaderTextCellValue(ourCompany));
        nestedTable.addCell(getHeaderTextCell("GC Address: "));
        nestedTable.addCell(getHeaderTextCellValue(ourAdress));
        nestedTable.addCell(getHeaderTextCell("GC Phone: "));
        nestedTable.addCell(getHeaderTextCellValue(ourPhoneNumber));
        nestedTable.addCell(getHeaderTextCell("GC IBAN: "));
        nestedTable.addCell(getHeaderTextCellValue(ourIBAN));
        nestedTable.addCell(getHeaderTextCell("GC Tax nmbr: "));
        nestedTable.addCell(getHeaderTextCellValue(ourTaxNumber));

        overArchingTable1.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));

        float[] width = {160f, 210f, 70f, 70f, 50f};
        Table table2 = new Table(width);
        Table table3 = new Table(width);
        Table overArchingTable2 = new Table(1);

        float[] fullwidth = {190f, 190f, 190f};
        Border greyBorderSmall = new SolidBorder(Color.DARK_GRAY, 1 / 2f);
        Table deviderSmall = new Table(fullwidth);
        deviderSmall.setBorder(greyBorderSmall);
        Border greyBorderBig = new SolidBorder(Color.DARK_GRAY, 2f);
        Table deviderBig = new Table(fullwidth);
        deviderBig.setBorder(greyBorderBig);

        String[] cellHeaders = {"item", "description", "price eur", "qnt", "total"};
        for (int i = 0; i < 5; i++) {
            table2.addCell(getHeaderTextCell(cellHeaders[i]));
        }

        List<OrderItemLine> itemLines = order.getProducts();

        String[][] orderItemLinesTable = new String[itemLines.size()][5];
        for (int i=0; i<itemLines.size(); i++){
            OrderItemLine itemLine = itemLines.get(i);
            orderItemLinesTable[i][0] = itemLine.getProduct().getName();
            orderItemLinesTable[i][1] = itemLine.getProduct().getShortDescription();
            orderItemLinesTable[i][2] = String.valueOf(itemLine.getProduct().getPriceInEur());
            orderItemLinesTable[i][3] = String.valueOf(itemLine.getQuantity());
            orderItemLinesTable[i][4] = String.valueOf(itemLine.getTotalPrice());
        };

        for (int i = 0; i < itemLines.size(); i++) {
            for (int j = 0; j < 5; j++) {
                table3.addCell(getHeaderTextCellValue(orderItemLinesTable[i][j]));
            }
        }

        table3.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table3.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table3.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table3.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table3.addCell(new Cell().add(deviderSmall).setBorder(Border.NO_BORDER));

        float[] totalwidth3 = {490f, 50f};
        Table table4 = new Table(totalwidth3);
        table4.addCell(new Cell().add("total price").setBold().setBorder(Border.NO_BORDER));
        table4.addCell(new Cell().add(String.valueOf(order.getTotalPriceInEur())).setBorder(Border.NO_BORDER));


        overArchingTable2.addCell(new Cell().add(table2).setBorder(Border.NO_BORDER));
        overArchingTable2.addCell(new Cell().add(deviderSmall).setBorder(Border.NO_BORDER));
        overArchingTable2.addCell(new Cell().add(table3).setBorder(Border.NO_BORDER));
        overArchingTable2.addCell(new Cell().add(table4).setBorder(Border.NO_BORDER));


        Table customerInfoTable = new Table(1)
                .setBorder(Border.NO_BORDER);
        customerInfoTable.addCell(new Cell().add(new Paragraph(""))
                .setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(new Paragraph("  "))
                .setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(new Paragraph(""))
                .setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(getHeaderTextCell("t.a.v:").setFontSize(14));
        customerInfoTable.addCell(getHeaderTextCellValue(order.getCustomer().getFirstName() + " " +
                order.getCustomer().getLastName()));
            customerInfoTable.addCell(getHeaderTextCell("company:"));
            customerInfoTable.addCell(getHeaderTextCellValue(order.getCustomer()
                    .getCompany()));


        Table invoiceInfoTable = new Table(new float[]{twocol / 2, twocol / 2});

        invoiceInfoTable.addCell(new Cell().add(deviderSmall).setBorder(Border.NO_BORDER));
        invoiceInfoTable.addCell(new Cell().add(deviderSmall).setBorder(Border.NO_BORDER));
        invoiceInfoTable.addCell(getHeaderTextCellValue("invoice numb: "));
        invoiceInfoTable.addCell(getHeaderTextCellValue(order.getId().toString()));

        invoiceInfoTable.addCell(getHeaderTextCellValue("date order: "));

        invoiceInfoTable.addCell(getHeaderTextCellValue(order.getOrderDate().toString()));

        invoiceInfoTable.addCell(getHeaderTextCellValue("time order"));

            invoiceInfoTable.addCell(getHeaderTextCellValue(order.getOrderTime().toString()));
        invoiceInfoTable.addCell(new Cell().add(deviderSmall).setBorder(Border.NO_BORDER));
        invoiceInfoTable.addCell(new Cell().add(deviderSmall).setBorder(Border.NO_BORDER));

        Table overArchingTable3 = new Table(twoColumnWidth).setBorder(Border.NO_BORDER);
        overArchingTable3.addCell(new Cell().add(customerInfoTable).setBorder(Border.NO_BORDER));
        overArchingTable3.addCell(new Cell().add(invoiceInfoTable).setBorder(Border.NO_BORDER));

String imageWatermarkSrc = "C:\\Users\\mees\\Downloads\\eindopdrachtBackenSystemGoldencarrot\\" +
        "eindopdrachtBackenSystemGoldencarrot\\src\\images\\goldencarrotImageWmInv.jpg";
        ImageData imageWatermarkDate = ImageDataFactory.create(imageWatermarkSrc);
        Image imageWatermark = new Image(imageWatermarkDate);

        float xLeft = 30;
        float y30Bottom = 30;
        imageWatermark.setFixedPosition(xLeft, y30Bottom);
        imageWatermark.setOpacity(0.9f);


 String imageWatermarkSrc2 = "C:\\Users\\mees\\Downloads\\eindopdrachtBackenSystemGoldencarrot" +
 "\\eindopdrachtBackenSystemGoldencarrot\\src\\images\\goldencarrotImageWmInv.jpg";

        ImageData imageWatermarkDate2 = ImageDataFactory.create(imageWatermarkSrc2);
        Image imageWatermark2 = new Image(imageWatermarkDate2);

        float xRight = 565.28f - 90f;
        imageWatermark2.setFixedPosition(xRight, y30Bottom);
        imageWatermark2.setOpacity(0.7f);

        Text officialGcDocText2 = new Text("Official document" + "\n of G.C Company");
        Text officialGcDocText = new Text("Official document" + "\n of the Golden carot");


        Paragraph paragraphWaterMarkRight = new Paragraph()
                .setFont(PdfFontFactory.createFont())
                .setFontSize(10)
                .setFixedPosition(xRight + 20f, y30Bottom + 5f, 200f)
                .setRotationAngle(0.4)
                .add(officialGcDocText);

        Text paidText = new Text("|$-----Paid-----$|");
        float angleInDegrees = 20f;
        float angleInRadians = (float) Math.toRadians(angleInDegrees);

        Paragraph paragraphWaterMarkLeft = new Paragraph()
                .setFont(PdfFontFactory.createFont())
                .setFontSize(10)
                .setFixedPosition(xLeft + 20f, y30Bottom + 15f, 70f)
                .setRotationAngle(0.4f)
                .setBorder(new SolidBorder(1f))
                .add(paidText);

        document.add(overArchingTable1);
        document.add(new Paragraph("\n"));
//       document.add(deviderBig);
        document.add(new Paragraph("\n"));
        document.add(overArchingTable3);
        document.add(new Paragraph("\n"));
        document.add(deviderBig);
        document.add(new Paragraph("\n"));
        document.add(overArchingTable2);
        document.add(imageWatermark);
        document.add(imageWatermark2);
        document.add(paragraphWaterMarkLeft);
        document.add(paragraphWaterMarkRight);


        pdfDocument.close();
        document.close();

            return outputStream.toByteArray();
        }
        catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    static Text getTextBold(String string){
        Text text = new Text(string);
        return text.setBold();
    }
    static Cell getHeaderTextCell(String headerTextValue){
        return new Cell().add(headerTextValue).setBold().setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getHeaderTextCellValue(String textValue){
        return new Cell().add(textValue).setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT);
    }
}

package com.onlinefoodcommercems.service.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.onlinefoodcommercems.entity.Order;
import com.onlinefoodcommercems.entity.OrderDetail;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PDFGenerateServiceImpl implements PDFGenerateService {


    @Override
    public void generate(Order order, HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(20);
        Font fontTitle1 = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle1.setSize(12);
        Paragraph paragraph1 = new Paragraph("List of Order Detail", fontTitle);
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph1);
        Paragraph customer = new Paragraph("Customer: " + order.getCustomer().getName() + order.getCustomer().getSurname(), fontTitle);
        customer.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(customer);
        Paragraph mobileNo = new Paragraph("Phone: " + order.getCustomer().getPhoneNumber(), fontTitle);
        mobileNo.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(mobileNo);
        Paragraph deliveryAddress = new Paragraph("Delivery address: "
                + order.getCustomer().getAddress().getCountry() + ","
                + order.getCustomer().getAddress().getCity() + ","
                + order.getCustomer().getAddress().getStreet() + ","
                + order.getCustomer().getAddress().getDistrict() + ","
                + order.getCustomer().getAddress().getApartmentNumber() + ",", fontTitle1);
        deliveryAddress.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(deliveryAddress);
        Paragraph paragraph3 = new Paragraph("Order Date:  " + order.getOrderDate(), fontTitle);
        paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph3);
        Paragraph paragraph4 = new Paragraph("Delivery Date:  " + order.getDeliveryDate(), fontTitle);
        paragraph4.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph4);
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{3, 3, 3, 3, 3});
        table.setSpacingBefore(5);
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.BLUE);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(CMYKColor.WHITE);

        cell.setPhrase(new Phrase("PRODUCT NAME", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("CATEGORY NAME", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("UNIT PRICE", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("QUANTITY", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("TOTAL PRICE", font));
        table.addCell(cell);
        var orderDetailList = order.getOrderDetails();
        for (OrderDetail orderDetail : orderDetailList
        ) {
            table.addCell(String.valueOf(orderDetail.getProduct().getName()));
            table.addCell(String.valueOf(orderDetail.getProduct().getCategory().getName()));
            table.addCell(String.valueOf(orderDetail.getPrice()));
            table.addCell(String.valueOf(orderDetail.getQuantity()));
            table.addCell(String.valueOf(orderDetail.getTotalPrice()));
        }
        document.add(table);
        Font fontTitle2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle2.setSize(20);
        Paragraph paragraph2 = new Paragraph("Total Amount: " + order.getTotalAmount() + " AZN", fontTitle2);
        paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph2);
        document.close();


    }


}

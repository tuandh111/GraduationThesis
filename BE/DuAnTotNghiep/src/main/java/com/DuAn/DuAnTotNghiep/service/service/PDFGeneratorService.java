package com.DuAn.DuAnTotNghiep.service.service;


import com.DuAn.DuAnTotNghiep.model.request.BillRequest;
import com.DuAn.DuAnTotNghiep.model.request.InvoiceRequest;
import com.DuAn.DuAnTotNghiep.model.request.PaymentRequest;
import com.DuAn.DuAnTotNghiep.model.response.AppointmentWithServicesResponse;
import com.DuAn.DuAnTotNghiep.model.response.InvoiceRes;
import com.DuAn.DuAnTotNghiep.utils.CurrencyFormatter;
import com.DuAn.DuAnTotNghiep.utils.DateUtils;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.border.*;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;

import com.itextpdf.layout.Document;

import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;

@Service
public class PDFGeneratorService {
    @Value("${app.pdf.directory}")
    private String pdfDirectory;
    @Autowired
    BillService billService;


    public void export(String directory, String filename, String text, AppointmentWithServicesResponse appointmentWithServicesResponseList) throws IOException {
        try {
            String fontPath = "font/ARIAL.TTF";

            PdfFont vietnameseFont = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H, true);
            Path filePath = Paths.get(directory, filename);
            Files.createDirectories(filePath.getParent());
            PdfWriter pdfWriter = new PdfWriter(filePath.toString());
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);

            pdfDocument.setDefaultPageSize(PageSize.A4);
            Document document = new Document(pdfDocument);
            String imagePath = "files/Logo.jpg";
            ImageData imageData = ImageDataFactory.create(imagePath);
            Image image = new Image(imageData);
            image.setWidth(200);
            image.setMarginLeft(100);
            image.setMarginTop(50);
            float x = pdfDocument.getDefaultPageSize().getWidth() / 2;
            float y = pdfDocument.getDefaultPageSize().getHeight() / 2;
            image.setFixedPosition(x - 200, y - 220);
            image.setOpacity(0.3f);
            document.add(image);
            float threecol = 190f;
            float twocol = 230f;
            float twocol150 = twocol + 150f;
            float twocolumnWidth[] = {twocol150, twocol};
            float fullwidth[] = {threecol * 3};
            float threeColumnWidth[] = {100f, 100f, 100f, 100f, 120f};
            Paragraph onesp = new Paragraph("\n");
            Table table = new Table(twocolumnWidth);
            table.addCell(new Cell().add("Hóa đơn").setFont(vietnameseFont).setFontSize(18f).setBorder(Border.NO_BORDER).setBold());
            Table nestedTable = new Table(new float[]{twocol / 2, twocol / 2});
            nestedTable.addCell(getHeaderTextCell("Số hóa đơn:")).setFont(vietnameseFont);
            nestedTable.addCell(getHeaderTextCellValue("USHODAYA17010")).setFont(vietnameseFont);
            nestedTable.addCell(getHeaderTextCell("Ngày tạo:")).setFont(vietnameseFont);
            LocalDate currentDate = LocalDate.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDate = currentDate.format(formatter);
            nestedTable.addCell(getHeaderTextCellValue(formattedDate));
            nestedTable.addCell(getHeaderTextCell("GSTIN :")).setFont(vietnameseFont);
            nestedTable.addCell(getHeaderTextCellValue("12CUJPB8751J1Z3")).setFont(vietnameseFont);
            table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));
            com.itextpdf.layout.border.Border border = new SolidBorder(Color.GRAY, 2f);
            Table divider = new Table(fullwidth);
            divider.setBorder(border);
            document.add(table);
            document.add(onesp);
            document.add(divider);
            document.add(onesp);

            Table twoColTable = new Table(twocolumnWidth);
            twoColTable.addCell(getBillingandShippingCell("Thông tin người nhận")).setFont(vietnameseFont);
            twoColTable.addCell(getBillingandShippingCell("Thông tin công ty")).setFont(vietnameseFont);
            document.add(twoColTable.setMarginBottom(10f));

            Table twoColTable2 = new Table(twocolumnWidth);
            twoColTable2.addCell(getCell10fLeft("Họ và tên", true)).setFont(vietnameseFont);
            twoColTable2.addCell(getCell10fLeft("Họ và tên ", true)).setFont(vietnameseFont);
            twoColTable2.addCell(getCell10fLeft(appointmentWithServicesResponseList.getAppointment().getPatient().getFullName(), false)).setFont(vietnameseFont);
            twoColTable2.addCell(getCell10fLeft("B Manoj Kumar Reddy", false)).setFont(vietnameseFont);
            document.add(twoColTable2);

            Table twoColTable3 = new Table(twocolumnWidth);
            twoColTable3.addCell(getCell10fLeft("Ngày sinh", true)).setFont(vietnameseFont);
            twoColTable3.addCell(getCell10fLeft("Địa chỉ", true)).setFont(vietnameseFont);
            twoColTable3.addCell(getCell10fLeft(DateUtils.formatDate(appointmentWithServicesResponseList.getAppointment().getPatient().getBirthday()), false)).setFont(vietnameseFont);
            twoColTable3.addCell(getCell10fLeft("ấp Khánh Hội, Thị trấn Ngã Sáu,\n Huyện Châu Thành, Hậu Giang.", false)).setFont(vietnameseFont);
            document.add(twoColTable3);

            float oneColumnWidth[] = {twocol150};

            Table oneColTable1 = new Table(oneColumnWidth);
            oneColTable1.addCell(getCell10fLeft("Địa chỉ", true)).setFont(vietnameseFont);
            oneColTable1.addCell(getCell10fLeft("ấp Khánh Hội, Thị trấn Ngã Sáu, Huyện Châu Thành, Hậu Giang.", false)).setFont(vietnameseFont);
            oneColTable1.addCell(getCell10fLeft("Email", true));
            oneColTable1.addCell(getCell10fLeft(appointmentWithServicesResponseList.getAppointment().getPatient().getUser().getEmail(), false));
            oneColTable1.addCell(getCell10fLeft("Số điện thoại", true)).setFont(vietnameseFont);
            oneColTable1.addCell(getCell10fLeft(appointmentWithServicesResponseList.getAppointment().getPatient().getPhoneNumber(), false)).setFont(vietnameseFont);
            document.add(oneColTable1.setMarginBottom(10f));

            Table tableDivider2 = new Table(fullwidth);
            Border border1 = new DashedBorder(Color.GRAY, 1f);
            document.add(tableDivider2.setBorder(border1));

            Paragraph productParagraph = new Paragraph("Thông tin hóa đơn").setFont(vietnameseFont);
            document.add(productParagraph.setBold());

            Table threeColumnTable1 = new Table(threeColumnWidth);
            threeColumnTable1.setBackgroundColor(Color.BLACK, 0.7f);
            threeColumnTable1.addCell(new Cell().add("Số thứ tự").setFont(vietnameseFont).setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            threeColumnTable1.addCell(new Cell().add("Dich vụ").setFont(vietnameseFont).setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            threeColumnTable1.addCell(new Cell().add("Số lượng").setFont(vietnameseFont).setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            threeColumnTable1.addCell(new Cell().add("Đơn giá").setFont(vietnameseFont).setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            threeColumnTable1.addCell(new Cell().add("Thành tiền").setFont(vietnameseFont).setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(16f));
            document.add(threeColumnTable1);

          /*  java.util.List<InvoiceRes> invoiceResList = new ArrayList<>();
            invoiceResList = appointmentWithServicesResponseList.getServices();
            invoiceResList.add(new InvoiceRes("Dịch vụ nhổ răng", 1, 300, 300));
            invoiceResList.add(new InvoiceRes("Dịch vụ tẩy trắng", 2, 150, 300));
            invoiceResList.add(new InvoiceRes("Dịch vụ điều trị sâu răng", 1, 200, 200));
            invoiceResList.add(new InvoiceRes("Dịch vụ cạo vôi răng", 3, 50, 150));
*/
            Table threeColTable2 = new Table(threeColumnWidth);

            float totalSum = 0f;
            int flag = 1;
            for (com.DuAn.DuAnTotNghiep.entities.Service invoiceRes : appointmentWithServicesResponseList.getServices()) {
                double total = 1 * invoiceRes.getPrice();
                totalSum += total;
                threeColTable2.addCell(new Cell().add(String.valueOf(flag)).setBorder(Border.NO_BORDER)).setMarginLeft(10f);
                threeColTable2.addCell(new Cell().add(String.valueOf(invoiceRes.getServiceName())).setFont(vietnameseFont).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
                threeColTable2.addCell(new Cell().add(String.valueOf(1)).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
                threeColTable2.addCell(new Cell().add(String.valueOf(CurrencyFormatter.formatCurrency(invoiceRes.getPrice()))).setFont(vietnameseFont).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
                threeColTable2.addCell(new Cell().add(String.valueOf(CurrencyFormatter.formatCurrency(total))).setFont(vietnameseFont).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
                flag++;
            }
            document.add(threeColTable2.setMarginBottom(20f));

            float totalDashedLine[] = {threecol + 125f, threecol * 2};
            Table threeColTable4 = new Table(totalDashedLine);
            threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
            threeColTable4.addCell(new Cell().add(tableDivider2).setBorder(Border.NO_BORDER));
            document.add(threeColTable4);

            Table threeColTable3 = new Table(threeColumnWidth);
            threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER)).setMarginLeft(10f);
            threeColTable3.addCell(new Cell().add("Tổng số tiền").setFont(vietnameseFont).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            threeColTable3.addCell(new Cell().add(String.valueOf(CurrencyFormatter.formatCurrency(totalSum))).setFont(vietnameseFont).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER)).setMarginRight(15f);

            document.add(threeColTable3);
            float ColumnWidth[] = {100f, 100f, 200f, 0f, 120f};
            Table threeColTable5 = new Table(ColumnWidth);
            threeColTable5.addCell(new Cell().add("").setBorder(Border.NO_BORDER)).setMarginLeft(10f);
            threeColTable5.addCell(new Cell().add("Thành chữ").setFont(vietnameseFont).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            threeColTable5.addCell(new Cell().add(String.valueOf(text + " đồng")).setFont(vietnameseFont).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER)).setMarginRight(15f);

            document.add(threeColTable5);
            document.add(tableDivider2);
            document.add(new Paragraph("\n"));
            document.add(divider.setBorder(new SolidBorder(Color.GRAY, 1)).setMarginBottom(15f));

            Table table1 = new Table(fullwidth);
            table1.addCell(new Cell().add("Ghi chú\n").setFont(vietnameseFont).setFontSize(10f).setBold().setBorder(Border.NO_BORDER));
            List<String> tncList = new ArrayList<>();
            tncList.add("1. Người bán sẽ không chịu trách nhiệm trực tiếp hoặc gián tiếp với Người mua về bất kỳ tổn thất hoặc thiệt hại nào mà Người mua phải chịu.");
            tncList.add("2. Ghi thêm điều khoản nếu có.");

            for (String tnc : tncList) {
                table1.addCell(new Cell().add(tnc).setFont(vietnameseFont).setFontSize(10f).setBorder(Border.NO_BORDER));
            }
            document.add(table1);
            BillRequest billRequest = BillRequest.builder().paymentMethod("Tiền mặt").status("Đã thanh toán").totalCost(totalSum).appointmentId(appointmentWithServicesResponseList.getAppointment().getAppointmentId()).createAt(new Date()).build();
            billService.saveBill(billRequest);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Lỗi khi tạo PDF", e);
        }
    }

    public byte[] read(String folder, String name) {
        try {
            Path path = Paths.get(folder, name);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi đọc file", e);
        }
    }

    static Cell getHeaderTextCell(String textValue) {
        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }

    static Cell getHeaderTextCellValue(String textValue) {
        return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }

    static Cell getBillingandShippingCell(String textValue) {
        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getCell10fLeft(String textValue, Boolean isBold) {
        Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        return isBold ? myCell.setBold() : myCell;
    }
}

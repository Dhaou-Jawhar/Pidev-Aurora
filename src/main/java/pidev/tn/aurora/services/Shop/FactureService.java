package pidev.tn.aurora.services.Shop;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Shop.Cart;
import pidev.tn.aurora.entities.Shop.CartItems;
import pidev.tn.aurora.entities.Shop.Facture;
import pidev.tn.aurora.entities.Shop.Order_Produit;
import pidev.tn.aurora.entities.enumeration.FactureType;
import pidev.tn.aurora.entities.enumeration.PaymentMethod;
import pidev.tn.aurora.repository.Shop.CartItemsRepository;
import pidev.tn.aurora.repository.Shop.CarteRepository;
import pidev.tn.aurora.repository.Shop.FactureRepository;
import pidev.tn.aurora.repository.Shop.OrderRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.List;

@Service
@Slf4j
public class FactureService implements IFactureService {

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CarteRepository carteRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Override
    public Facture generateInvoice(Order_Produit order) throws IOException {

        Facture facture = new Facture();

        long factureNumber = System.currentTimeMillis() + (long)(Math.random() * 1000000);
        facture.setNumber(factureNumber);

        double TotalPrice = order.getTotalprice();
        facture.setPrice(TotalPrice);

    
        PaymentMethod paymentMethod = order.getPaymentMethod();
        if (paymentMethod == PaymentMethod.PAYPAL) {
            facture.setFactureType(FactureType.INVOICE);
        } else {
            facture.setFactureType(FactureType.RECEIPT);
        }

        facture.setDate(new Date());

        factureRepository.save(facture);

        order.setFacture(facture);

        orderRepository.save(order);

        /*------------[Creation PDF]---------------*/
        String path = "D://4SE5 2nd PART//PIDEV//Aurora//Aurora//src//main//resources//templates//assets//facture//facture.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();

        /*---[Water Mark]----*/
        String waterMark = "src/main/resources/templates/assets/logo.png";
        ImageData imageData = ImageDataFactory.create(waterMark);
        Image image = new Image(imageData);
        image.setFixedPosition(pdfDocument.getDefaultPageSize().getWidth()/2-250, pdfDocument.getDefaultPageSize().getHeight()/2-160);
        image.setOpacity(0.1f);

        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        float col = 280f;
        float columnWidth[] = {col, col};
        Table table = new Table(columnWidth);

        table.setBackgroundColor(new DeviceRgb(63, 169, 219))
                        .setFontColor(Color.WHITE);
        table.addCell(new Cell().add("FACTURE")
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setFontSize(30f)
                .setBorder(Border.NO_BORDER)
        );
        table.addCell(new Cell().add("Aurora E-SHOP\n#2083 ESPRIT\nN°24232423")
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setBorder(Border.NO_BORDER)
                .setMarginRight(10f)
        );

        float colWidth[] = {80, 300, 100, 80};
        Table customerInfoTable = new Table(colWidth);
        customerInfoTable.addCell(new Cell(0,4)
                .add("Customer Information")
                .setBold());
        customerInfoTable.addCell(new Cell().add("Name :").setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add("Dhaou Jawhar").setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add("Invoice No.").setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(String.valueOf(facture.getNumber())).setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add("Total Price").setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(String.valueOf(facture.getPrice()+" DT")).setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add("Date").setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(String.valueOf(new Date())).setBorder(Border.NO_BORDER));

        float itemInfoColumnWidth[] = {140, 140, 140, 140};
        Table itemInfoTable = new Table(itemInfoColumnWidth);

        itemInfoTable.addCell(new Cell().add("Product").setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE));
        itemInfoTable.addCell(new Cell().add("Qantity").setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE));
        itemInfoTable.addCell(new Cell().add("Price").setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE));
        itemInfoTable.addCell(new Cell().add("Type").setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE));

        itemInfoTable.addCell(new Cell().add("Tente 3 Places"));
        itemInfoTable.addCell(new Cell().add("1"));
        itemInfoTable.addCell(new Cell().add("650")
                .setTextAlignment(TextAlignment.RIGHT));
        itemInfoTable.addCell(new Cell().add("tente")
                .setTextAlignment(TextAlignment.RIGHT));

        itemInfoTable.addCell(new Cell().add("Sleeping Bag -40°"));
        itemInfoTable.addCell(new Cell().add("1"));
        itemInfoTable.addCell(new Cell().add("50")
                .setTextAlignment(TextAlignment.RIGHT));
        itemInfoTable.addCell(new Cell().add("Sleeping Bag")
                .setTextAlignment(TextAlignment.RIGHT));

        itemInfoTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
                .setBorder(Border.NO_BORDER));
        itemInfoTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
                .setBorder(Border.NO_BORDER));
        itemInfoTable.addCell(new Cell().add("Total Price").setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.RIGHT));
        itemInfoTable.addCell(new Cell().add(String.valueOf(facture.getPrice()+" DT")).setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.RIGHT));


        document.add(image);
        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(customerInfoTable);
        document.add(new Paragraph("\n"));
        document.add(itemInfoTable);
        document.close();

        return facture;
    }

    @Override
    public Facture orderCart(Integer cartID, PaymentMethod paymentMethod) throws FileNotFoundException, MalformedURLException {

        Cart cart = carteRepository.findById(cartID).get();

        Order_Produit order = new Order_Produit();

        order.setCart(cart);
        order.setCreateddate(new Date());
        order.setTotalprice(cart.getTotalprice());
        order.setPaymentMethod(paymentMethod);

        Facture facture = new Facture();

        long factureNumber = System.currentTimeMillis() + (long)(Math.random() * 1000000);
        facture.setNumber(factureNumber);

        double TotalPrice = order.getTotalprice();
        facture.setPrice(TotalPrice);

        if (paymentMethod == PaymentMethod.PAYPAL) {
            facture.setFactureType(FactureType.INVOICE);
        } else {
            facture.setFactureType(FactureType.RECEIPT);
        }

        facture.setDate(new Date());

        factureRepository.save(facture);

        order.setFacture(facture);
        order.setCart(cart);

        orderRepository.save(order);

        /*------------[Creation PDF]---------------*/
        String path = "D://4SE5 2nd PART//PIDEV//Aurora//Aurora//src//main//resources//templates//assets//facture//facture.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();

        /*---[Water Mark]----*/
        String waterMark = "src/main/resources/templates/assets/logo.png";
        ImageData imageData = ImageDataFactory.create(waterMark);
        Image image = new Image(imageData);
        image.setFixedPosition(pdfDocument.getDefaultPageSize().getWidth()/2-250, pdfDocument.getDefaultPageSize().getHeight()/2-160);
        image.setOpacity(0.1f);

        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        float col = 280f;
        float columnWidth[] = {col, col};
        Table table = new Table(columnWidth);

        table.setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE);
        table.addCell(new Cell().add("FACTURE")
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setFontSize(30f)
                .setBorder(Border.NO_BORDER)
        );
        table.addCell(new Cell().add("Aurora E-SHOP\n#2083 ESPRIT\nN°24232423")
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setBorder(Border.NO_BORDER)
                .setMarginRight(10f)
        );

        float colWidth[] = {80, 300, 100, 80};
        Table customerInfoTable = new Table(colWidth);
        customerInfoTable.addCell(new Cell(0,4)
                .add("Customer Information")
                .setBold());
        customerInfoTable.addCell(new Cell().add("Name :").setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add("Dhaou Jawhar").setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add("Invoice No.").setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(String.valueOf(facture.getNumber())).setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add("Total Price").setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(String.valueOf(facture.getPrice()+" DT")).setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add("Date").setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(String.valueOf(new Date())).setBorder(Border.NO_BORDER));

        float itemInfoColumnWidth[] = {140, 140, 140, 140};
        Table itemInfoTable = new Table(itemInfoColumnWidth);

        itemInfoTable.addCell(new Cell().add("Product").setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE));
        itemInfoTable.addCell(new Cell().add("Qantity").setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE));
        itemInfoTable.addCell(new Cell().add("Price").setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE));
        itemInfoTable.addCell(new Cell().add("Type").setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE));

        List<CartItems> cartItems = cartItemsRepository.findAll();


        /*--[Parcourir la list de Products dans la cart]--*/

        for ( CartItems ct : cartItems){
            itemInfoTable.addCell(new Cell().add(ct.getProduct().getName()));
            itemInfoTable.addCell(new Cell().add(String.valueOf(ct.getQuantity())));
            itemInfoTable.addCell(new Cell().add(String.valueOf(ct.getProduct().getPrice()*ct.getQuantity()))
                    .setTextAlignment(TextAlignment.RIGHT));
            itemInfoTable.addCell(new Cell().add(String.valueOf(ct.getProduct().getCat()))
                    .setTextAlignment(TextAlignment.RIGHT));
        }

        itemInfoTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
                .setBorder(Border.NO_BORDER));
        itemInfoTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
                .setBorder(Border.NO_BORDER));
        itemInfoTable.addCell(new Cell().add("Total Price").setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.RIGHT));
        itemInfoTable.addCell(new Cell().add(String.valueOf(facture.getPrice()+" DT")).setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.RIGHT));


        document.add(image);
        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(customerInfoTable);
        document.add(new Paragraph("\n"));
        document.add(itemInfoTable);
        document.close();

        return facture;
    }
}

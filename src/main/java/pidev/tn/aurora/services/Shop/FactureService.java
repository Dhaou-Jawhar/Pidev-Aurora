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
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.FactureType;
import pidev.tn.aurora.entities.enumeration.PaymentMethod;
import pidev.tn.aurora.repository.Shop.CartItemsRepository;
import pidev.tn.aurora.repository.Shop.CarteRepository;
import pidev.tn.aurora.repository.Shop.FactureRepository;
import pidev.tn.aurora.repository.Shop.OrderRepository;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;

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

    @Autowired
    private UserAppRepository userAppRepository;

    @Override
    public Facture orderCart(Integer cartID, PaymentMethod paymentMethod , Integer userID) throws FileNotFoundException, MalformedURLException {

        Cart cart = carteRepository.findById(cartID).get();
        UserApp user = userAppRepository.findById(userID).get();
        List<CartItems> cartItems = cartItemsRepository.findAll();

        Order_Produit order = new Order_Produit();

        order.setCart(cart);
        order.setCreateddate(new Date());
        order.setTotalprice(cart.getTotalprice());
        order.setPaymentMethod(paymentMethod);

        Facture facture = new Facture();

        long factureNumber = System.currentTimeMillis() + (long)(Math.random() * 1000000);
        facture.setNumber(factureNumber);

        double TotalPrice = order.getTotalprice()-(order.getTotalprice()*((double)user.getDiscount()/100));
        facture.setPrice(TotalPrice);


        if (paymentMethod == PaymentMethod.PAYPAL) {
            facture.setFactureType(FactureType.INVOICE);
        } else {
            facture.setFactureType(FactureType.RECEIPT);
        }

        facture.setDate(new Date());
        facture.setState(true);

        factureRepository.save(facture);

        order.setFacture(facture);
        order.setCart(cart);
        order.setUserApp(user);
        orderRepository.save(order);

        cart.setActive(false);
        cart.setOrder_Produit(order);
        cart.setUserApp(user);
        carteRepository.save(cart);

        user.setDiscount(0);
        userAppRepository.save(user);

        /*------------[Creation PDF]---------------*/
        String path = "D://4SE5 2nd PART//PIDEV//Aurora//Aurora//src//main//resources//templates//assets//facture//facture"+user.getFirstName()+facture.getId()+".pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();
        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);


        /*---[Water Mark]----*/
        String waterMark = "src/main/resources/templates/assets/logo.png";
        ImageData imageData = ImageDataFactory.create(waterMark);
        Image image = new Image(imageData);
        image.setFixedPosition(pdfDocument.getDefaultPageSize().getWidth()/2-250, pdfDocument.getDefaultPageSize().getHeight()/2-160);
        image.setOpacity(0.1f);

        /*---[BANNING STAMP]----*/
        String banMark = "src/main/resources/templates/assets/ban.png";
        ImageData banData = ImageDataFactory.create(banMark);
        Image banImg = new Image(banData);
        banImg.setFixedPosition(pdfDocument.getDefaultPageSize().getWidth()/2-250, pdfDocument.getDefaultPageSize().getHeight()/2-160);
        banImg.setOpacity(0.6f);

        /*----[Signature]----*/
        String Sign = "src/main/resources/templates/assets/signature.png";
        ImageData signdata = ImageDataFactory.create(Sign);
        Image signimg = new Image(signdata);
        signimg.setFixedPosition(pdfDocument.getDefaultPageSize().getWidth()/2-150, pdfDocument.getDefaultPageSize().getHeight()/2-160);
        signimg.setOpacity(0.6f);


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
        table.addCell(new Cell().add("Aurora E-SHOP\n#2083 ESPRIT\nN° "+facture.getNumber())
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
        customerInfoTable.addCell(new Cell().add(user.getLastName() + " " + user.getFirstName()).setBorder(Border.NO_BORDER));
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
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(customerInfoTable);
        document.add(new Paragraph("\n"));
        document.add(itemInfoTable);
        if (cartItems.isEmpty()){
            document.add(banImg);
            facture.setState(false);
        }
        document.add(signimg);
        document.close();

        cartItemsRepository.deleteAll();

        return facture;
    }
    @Scheduled(cron = "*/20 * * * * *")
    public String deleteFile(){
        List<Facture> factureList = factureRepository.findAllByState(false);

        for(Facture f : factureList) {
            String fileName = "facture"+f.getOrder_produit().getUserApp().getFirstName()+f.getId()+".pdf";
            String directoryPath = "src/main/resources/templates/assets/facture";
            File fileToDelete = new File(directoryPath + File.separator + fileName);
            fileToDelete.delete();
            factureRepository.delete(f);
            log.info("-------[ All Facture With BAN STAMP ]-------");
            log.info("Facture : "+"facture"+f.getOrder_produit().getUserApp().getFirstName()+f.getId()+".pdf"+" Successfully deleted");
        }
        return "False Factures Are Deleted";
    }
}
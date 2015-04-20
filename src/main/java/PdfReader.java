import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.util.PDFTextStripper;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by fabiankaupmann on 28.03.15.
 */
public class PdfReader {

    Logger logger = Logger.getAnonymousLogger();

    PDFParser pdfParser;

    PDFTextStripper pdfTextStripper;

    public PdfReader(){

    }


    public String pdfToString(String source){
        String parsedText = null;
        PDDocument pdDocument;
        COSDocument cosDocument;
        File file = new File(source);


        if(!file.isFile()  || !file.getName().endsWith(".pdf")){
            System.err.println("Kein PDF!");
            return null;
        }

        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            pdfParser = new PDFParser(fileInputStream);
        } catch (IOException e) {
            System.out.println("PDF-Parser konnte nicht geöffnet werden");
        }

        try {
            pdfParser.parse();
            cosDocument = pdfParser.getDocument();

            pdDocument = new PDDocument(cosDocument);

            pdfTextStripper = new PDFTextStripper();

            parsedText = pdfTextStripper.getText(pdDocument);

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(fileInputStream != null){
            try {
                fileInputStream.close();
            } catch (IOException e) {
                System.err.println("Datei konnte nicht geschlossen werden");
            }
        }


        return parsedText;
    }

//    public String pdfToString(String source){
//        //TODO: exceptionhandling
//
//        String parsedText = null;
//        PDDocument pdDocument;
//        COSDocument cosDocument;
//        File file = new File(source);
//
//        if(!file.isFile() || !file.getName().endsWith(".pdf")){
//            System.err.println("Keine PDF-Datei!");
//            return null;
//        }
//
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            pdfParser = new PDFParser(fileInputStream);
//            fileInputStream.close();
//        } catch (IOException e) {
//            logger.log(Level.WARNING, "PDF-Parser konnte nicht " +
//                    "geöffnet werden.");
//        }
//
//
//        try {
//            pdfParser.parse();
//
//            cosDocument = pdfParser.getDocument();
//
//            pdDocument = new PDDocument(cosDocument);
//
//            pdfTextStripper = new PDFTextStripper();
//
//            parsedText = pdfTextStripper.getText(pdDocument);
//
//        } catch (IOException e) {
//            logger.log(Level.WARNING, "PDF konnte nicht geparst werden.");
//        }
//        return parsedText;
//    }


//    public ArrayList<Image> pdfToImages(String source){
//        //TODO: exceptionhandling, Bildformat
//        ArrayList<Image> images = new ArrayList<Image>();
//        COSDocument cosDocument;
//        PDDocument pdDocument;
//        File file = new File(source);
//        if(!file.isFile() || !file.getName().endsWith(".pdf")){
//            System.err.println("Keine PDF-Datei!");
//            return null;
//        }
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            pdfParser = new PDFParser(fileInputStream);
//            fileInputStream.close();
//        } catch (IOException e) {
//            logger.log(Level.WARNING, "PDF-Parser konnte nicht " +
//                    "geöffnet werden.");
//        }
//        try {
//            pdfParser.parse();
//
//            cosDocument = pdfParser.getDocument();
//
//            pdDocument = new PDDocument(cosDocument);
//
//            java.util.List<PDPage> pageList = pdDocument.getDocumentCatalog().getAllPages();
//
//            for (PDPage page : pageList) {
//
//                PDResources pdResources = page.getResources();
//
//                Map pageImages = pdResources.getImages();
//                if (pageImages != null) {
//
//                    Iterator imageIter = pageImages.keySet().iterator();
//                    while (imageIter.hasNext()) {
//                        String key = (String) imageIter.next();
//                        PDXObjectImage pdxObjectImage = (PDXObjectImage) pageImages.get(key);
//                        images.add(pdxObjectImage.getRGBImage());
//                    }
//                } else {
//                    System.err.println("Kein Bild in der PDF-Datei gefunden.");
//                }
//            }
//        } catch (IOException e) {
//            logger.log(Level.WARNING, "PDF konnte nicht geparst werden.");
//        }
//
//        return images;
//
//
//
//    }

}

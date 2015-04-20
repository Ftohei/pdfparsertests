import java.util.ArrayList;

/**
 * Created by fabiankaupmann on 18.04.15.
 */
public class Main {

    public static void main(String[] args) {


        PdfReader pdfReader = new PdfReader();
        Extractor extractor = new Extractor();

        String pdf;

        //Hier Pfade für die jeweiligen PDFs angeben


//        pdf = pdfReader.pdfToString("/Users/fabiankaupmann/Downloads/Lebenslauf - Meierhoff, Franziska.pdf");
//        pdf = pdfReader.pdfToString("/Users/fabiankaupmann/Downloads/Lebenslauf - M_Barek, Elyas.pdf");
//       pdf = pdfReader.pdfToString("/Users/fabiankaupmann/Downloads/Lebenslauf - Popolski, Pawel.pdf");
        pdf = pdfReader.pdfToString("/Users/fabiankaupmann/Downloads/Anschreiben_1_150419_164540.pdf");


        extractor.extractData(extractor.splitInLines(pdf));

    }

}


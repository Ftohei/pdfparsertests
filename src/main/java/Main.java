import java.util.ArrayList;

/**
 * Created by fabiankaupmann on 18.04.15.
 */
public class Main {

    public static void main(String[] args) {
        // write your code here
        PdfReader pdfReader = new PdfReader();
        Extractor extractor = new Extractor();


        String pdf;


        //

//        pdf = pdfReader.pdfToString("/Users/fabiankaupmann/Downloads/Lebenslauf - Meierhoff, Franziska.pdf");
//        pdf = pdfReader.pdfToString("/Users/fabiankaupmann/Downloads/Lebenslauf - M_Barek, Elyas.pdf");
       pdf = pdfReader.pdfToString("/Users/fabiankaupmann/Downloads/Lebenslauf - Popolski, Pawel.pdf");
//        pdf = pdfReader.pdfToString("/Users/fabiankaupmann/Downloads/Anschreiben_1_150419_164540.pdf");


        System.out.println(pdf);


//        System.out.println(lebenslauf);

        //extraktion mit einfachen regexen
//
//        extractor.regexMatchMail(lebenslauf);
//        System.out.println("---------");
//        extractor.regexMatchPhonenumber(lebenslauf);
//        System.out.println("---------");
//        extractor.regexMatchPostalCode(lebenslauf);

//        extractor.analyzeWithScanner(pdf);

        String pdfGetaggt = extractor.addPostags(pdf);

        System.out.println(pdfGetaggt);

//
//        ArrayList<String> relevantWords = extractor.analyzePostaggedString(pdfGetaggt);
//
//        for(String word : relevantWords){
//            int i = 0;
//            System.out.println(word);
//        }

    }


    //

}


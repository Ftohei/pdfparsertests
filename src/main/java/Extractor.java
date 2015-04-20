import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fabiankaupmann on 18.04.15.
 */
public class Extractor {


    private boolean nextCity = false;

    public Extractor() {
    }

    public void extractData(ArrayList<String[]> lines){

        for(String[] line : lines){

            for(String word : line){

                //extraktionsmethoden prüfen das wort, speichern bei Match in das Data-Objekt


                if(this.checkForMail(word)){
                    System.out.println("Email: " + word);
                }

                if(this.checkForCity(word)){
                    System.out.println("Stadt: " + word);
                }

                if(this.checkForPostalCode(word)){
                    System.out.println("Postleitzahl: " + word);
                }

                if(this.checkForPhoneNumber(word)){
                    System.out.println("Telefonnummer: " + word);
                }

            }

        }

    }

    public ArrayList<String[]> splitInLines(String string){
        ArrayList<String[]> lines = new ArrayList<String[]>();

        Scanner scanner = new Scanner(string);
        scanner.useDelimiter("\n");

        while(scanner.hasNext()){
            String line = scanner.next();

            String[] lineArray = line.split("\\s+");

            lines.add(lineArray);
        }

        return lines;
    }


    public boolean checkForStreetAdress(String word){




        return false;
    }


    public boolean checkForCity(String word){

        String cityRegex = "([A-Za-zäöü])+";

        if(word.matches(cityRegex) && this.nextCity){

            this.nextCity = false;
            return true;

        }

        return false;
    }

    public boolean checkForPostalCode(String word){

        //TODO: nächstes Wort ist immer Stadt. Falls nicht, dann verwerfen

        String postalCodeRegex = "([0-9]){5}";

        if(word.matches(postalCodeRegex)){

            this.nextCity = true;
            return true;
        }


        return false;
    }

    public boolean checkForMail(String word){

        String mailRegex = "([A-Za-z0-9+\\-.,!@#$%^&*()\\\\/|<>\"'_])+\\@([a-z])+(\\.([a-z])+\\.([a-z])+|\\.([a-z])+)";

        if(word.matches(mailRegex)){
            return true;
        }

        return false;

    }

    public boolean checkForPhoneNumber(String word){

        String phoneNumberRegex = "([0-9]){4,}\\s*\\/\\s*([0-9])+";

        if(word.matches(phoneNumberRegex)){
            return true;
        }

        return false;
    }

    public String addPostags(String stringForTagging){
        MaxentTagger tagger = new MaxentTagger("/Users/fabiankaupmann/DEV/intellijProjects/pdfparsertests/taggers/german-fast.tagger");

        String result = tagger.tagString(stringForTagging);

        return result;
    }

}

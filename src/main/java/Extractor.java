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

    private HashSet<String> keywords;

    public Extractor() {
        this.keywords = new HashSet<String>();
        this.keywords.add("Name:");
        this.keywords.add("Vorname:");
        this.keywords.add("Geburtsdatum:");

    }

    public boolean regexMatchMail (String word){

        Pattern mailAddress = Pattern.compile("([A-Za-z0-9+\\-.,!@#$%^&*()\\\\/|<>\"'_])+\\@([a-z])+(\\.([a-z])+\\.([a-z])+|\\.([a-z])+)");

        Matcher mailMatcher = mailAddress.matcher(word);

        if(mailMatcher.find()){
            return true;
        }

        return false;
    }


    public boolean regexMatchPhonenumber ( String word){
        Pattern phoneNumber = Pattern.compile("([0-9]){4,}\\s*\\/\\s*([0-9])+");

        Matcher phoneNumberMatcher = phoneNumber.matcher(word);

        if(phoneNumberMatcher.find()){
            return true;
        }

        return false;
    }

    public boolean regexMatchPostalCode (String word){
        Pattern postalCodeAndCity =  Pattern.compile("([0-9]){5}");

        Matcher postalMatcher = postalCodeAndCity.matcher(word);

        if(postalMatcher.find()){
            return true;
        }

        return false;
    }

    public void analyzeWithScanner(String lebenslauf){
        Scanner scanner = new Scanner(lebenslauf);
        scanner.useDelimiter("\\s+");

        boolean nextImportant = false;
        while(scanner.hasNext()){
            String word = scanner.next();

            if(nextImportant){
                System.out.println("Durch keyword entdeckt: " + word);
            }

            if(this.regexMatchMail(word)){
                System.out.println("Mail:" + word);
            }

            if(this.regexMatchPhonenumber(word)){
                System.out.println("Telefonnummer: " + word);
            };

            if(this.regexMatchPostalCode(word)){
                System.out.println("Postleitzahl: " + word);
            }

            nextImportant = this.matchKeywords(word);

        }

    }

    public boolean matchKeywords (String word){

        String pattern = "";

        for(String keyword : this.keywords){
            pattern = pattern + keyword + "|";
        }


//        System.out.println(pattern);

        Pattern keywordPattern =  Pattern.compile("pattern");

        Matcher keywordMatcher = keywordPattern.matcher(word);

        if(keywordMatcher.find()){
            return true;
        } else {
            return false;
        }
    }


    public String addPostags(String stringForTagging){
        MaxentTagger tagger = new MaxentTagger("/Users/fabiankaupmann/DEV/intellijProjects/pdfparsertests/taggers/german-fast.tagger");

        String result = tagger.tagString(stringForTagging);

        System.out.println(result);

        return result;
    }

    public ArrayList<String> analyzePostaggedString(String postaggedString){
        ArrayList<String> result = new ArrayList();
        ArrayList<String> postags = new ArrayList();
        ArrayList<String> words = new ArrayList<String>();
        Scanner scanner = new Scanner(postaggedString);
        scanner.useDelimiter("\\s+|_+");

        while(scanner.hasNext()){
            words.add(scanner.next());
            if(scanner.hasNext()) {
                postags.add(scanner.next());
            }
        }

        int i = 0;

        for(String word : words){
            if(postags.get(i).equals("NE")){
                result.add(word);
            }
            ++i;
        }

        return result;
    }

//
//        System.out.println("---------------");
//        System.out.println("Postleitzahl + Stadt:");
//

//
//        System.out.println("---------------");
//        System.out.println("Geburtstage: (Daten der Form TT.MM.JJJJ):");
//
//        Pattern datePattern = Pattern.compile("([0-9]){2}\\.([0-9]){2}\\.([0-9]){4}|([0-9]){2}\\.\\s*([A-Z])([a-z])+\\s*([0-9]){4}");
//
//        Matcher dateMatcher = datePattern.matcher(lebenslauefe);
//
//        while(dateMatcher.find()){
//            String date = dateMatcher.group();
//            System.out.println(date);
//        }
//
//        System.out.println("---------------");
//        System.out.println("Straße und Hausnummer");
//
//        Pattern addressPattern = Pattern.compile("([A-Z])([a-z]|ä|ö|ü)+\\s([0-9])+|([A-Z])([a-z]|ä|ö|ü)+\\s([A-Z])([a-z]|ä|ö|ü)+\\.\\s([0-9])+");
//
//        Matcher addressMatcher = addressPattern.matcher(lebenslauefe);
//
//        while(addressMatcher.find()){
//            String address = addressMatcher.group();
//            System.out.println(address);




}

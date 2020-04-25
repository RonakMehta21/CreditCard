import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Scanner;
public class Client {
    public static void main(String args[]) throws IOException, ParserConfigurationException, SAXException, TransformerException {

        FileContext fc = new FileContext();
        Scanner inputObj = new Scanner(System.in);

        //Read input file name
        System.out.println("Enter Input File Name:");
        String inputFilename = inputObj.nextLine();

        //Read the output file name
        System.out.println("Enter Output File Name:");
        String outputFilename = inputObj.nextLine();

        //TODO Remove hardcoded file names
        inputFilename = "/Users/ronakmehta/Desktop/CreditCard/src/Sample.json";
        outputFilename = "/Users/ronakmehta/Desktop/CreditCard/src/Output.json";

        fc.parse(inputFilename, outputFilename);
    }
}

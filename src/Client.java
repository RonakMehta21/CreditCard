import java.util.Scanner;
public class Client {
    public static void main(String args[]){
        try {
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
        }catch(Exception e){
            System.out.print("Error:"+e);
        }
    }
}

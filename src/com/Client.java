package com;

public class Client {

    public static void main(String args[]){
        try {
            FileContext fc = new FileContext();

            //Read input file name
            String inputFilename = "com/"+args[0];

            //Read the output file name
            String outputFilename = "com/"+args[1];

            fc.parse(inputFilename, outputFilename);
        }catch(Exception e){
            System.out.print("Error: "+e);
        }
    }
}

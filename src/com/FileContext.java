package com;

import java.io.File;

public class FileContext {
    private FileParser fileParser;
    public void parse(String inputFilename, String outputFilename){
        String input_file_extension = inputFilename.substring(inputFilename.lastIndexOf(".") + 1).toLowerCase();
        String output_file_extension = outputFilename.substring(outputFilename.lastIndexOf(".") + 1).toLowerCase();
        if(!input_file_extension.equals(output_file_extension)){
            System.out.println("Please enter the same type of files.");
            return;
        }
        try {
            if (inputFilename.endsWith(".csv")) {
                fileParser = new CsvFileParser();
            } else if (inputFilename.endsWith(".json")) {
                fileParser = new JsonFileParser();
            } else if (inputFilename.endsWith(".xml")) {
                fileParser = new XMLFileParser();
            } else {
                System.out.print(input_file_extension + " is not a supported file type");
                System.out.println("Please Enter a Valid file type");
                return;
            }

            fileParser.fetchRecords(inputFilename);

            File file = new File(outputFilename);
            file.createNewFile();

            fileParser.processRecords(outputFilename);
        }catch(Exception e){
            System.out.print("Error while Parsing File:"+e);
        }
    }
}

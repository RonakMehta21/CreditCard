package com;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CsvFileParser implements FileParser {
    private List<String> records;
    private List<String> output_records;
    public CsvFileParser() {
        records = new ArrayList<>();
        output_records = new ArrayList<>();
    }

    @Override
    public void fetchRecords(String inputFilename) {
        try {
            records.addAll(Files.readAllLines(Paths.get(inputFilename)));
            if(records.get(0).toLowerCase().startsWith("card")){
                records.remove(0);
            }
        } catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void processRecords(String outputFilename){
        try {
            RecordsIterator iterator = new RecordsIteratorImpl(records);

            output_records.add("CardNumber,CardType,Error");

            String record;
            String output;

            while (!iterator.isDone()) {
                record = iterator.currentString();

                if(record.equals("")){
                    output_records.add("null,Invalid,LineIsBlank");
                    iterator.next();
                    continue;
                }

                output = this.processEachRecord(record);

                output_records.add(output);
                iterator.next();
            }

            this.writeFile(outputFilename);
        }catch(Exception e){
            System.out.println("Error while processing records:"+e);
        }

    }

    @Override
    public void writeFile(String outputFilename){
        try {
            FileWriter fileWriter = new FileWriter(outputFilename);
            RecordsIterator iterator = new RecordsIteratorImpl(output_records);

            while (!iterator.isDone()) {
                fileWriter.write(iterator.currentString() + "\n");
                iterator.next();
            }

            fileWriter.close();
        }catch(Exception e){
            System.out.println("Error:"+e);
        }
    }

    public String processEachRecord(String record){
        String output;
        String card_number = "";
        String card_type = "Invalid";
        String error_message = "None";
        Date expiry_date;

        card_number = record.split(",")[0];

        // Implementing Factory Method Pattern to get the Card Factory object
        CardFactory cardFactory = new CardFactoryImpl();

        // Using the factory object to create the appropriate Object of Subclass of Card
        Card card = cardFactory.createCard(card_number);

        //Finally validate the card and return the card_type
        if(card==null){
            error_message = "InvalidCardNumber";
            card_type = "Invalid";
            return (card_number + "," + card_type + "," + error_message);
        }

        output = card.validateCardType(card_number);

        if(output.equals("Invalid")) {
            error_message = "InvalidCardNumber";
        }
        card_type = output;
        return (card_number + "," + card_type + "," + error_message);
    }
}

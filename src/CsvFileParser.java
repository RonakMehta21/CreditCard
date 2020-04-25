import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CsvFileParser implements FileParser{
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
        } catch (Exception e) {
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

                output = this.processEachRecord(record);

                output_records.add(output);
                iterator.next();
            }

            this.writeFile(outputFilename);
        }catch(Exception e){
            System.out.println("Error:"+e);
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
        Card c = null;
        String card_type = "Invalid";
        String error_message = "None";

        card_number = record.split(",")[0];
        c = new Card(card_number);

        output = c.validateCardType();

        if(output.equals("Error")){
            error_message = "InvalidCardNumber";
        } else {
            card_type = output;
        }
        return (card_number + "," + card_type + "," + error_message);
    }
}

import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
public class JsonFileParser implements FileParser{
    private JSONArray output_records;
    private JSONArray records;

    public JsonFileParser() {
        records = new JSONArray();
        output_records = new JSONArray();
    }

    @Override
    public void fetchRecords(String inputFilename){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(inputFilename))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            records = (JSONArray) obj;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processRecords(String outputFilename) {

        RecordsIterator iterator = new RecordsIteratorImpl(records, records.size());
        JSONObject output;

        while(!iterator.isDone()){
            JSONObject record = iterator.currentObject();
            output = this.processEachRecord(record);
            output_records.add(output);
            iterator.next();
        }
        this.writeFile(outputFilename);
    }

    public JSONObject processEachRecord(JSONObject record){
        String output;
        String card_type = "Invalid";
        String error_message = "None";
        String card_number;
        JSONObject row = new JSONObject();

        card_number = record.get("CardNumber").toString();

        // Implementing Factory Method Pattern to get the Card Factory object
        CardFactory cardFactory = new CardFactoryImpl();

        // Using the factory object to create the appropriate Object of Subclass of Card
        Card card = cardFactory.createCard(card_number);

        //Finally validate the card and return the card_type
        if(card==null){
            error_message = "InvalidCardNumber";
            card_type = "Invalid";
            row.put("CardNumber",card_number);
            row.put("CardType",card_type);
            row.put("Error",error_message);
            return row;
        }

        output = card.validateCardType(card_number);

        if(output.equals("Invalid")){
            error_message = "InvalidCardNumber";
        } else {
            card_type = output;
        }
        row.put("CardNumber",card_number);
        row.put("CardType",card_type);
        row.put("Error",error_message);
        return row;
    }


    @Override
    public void writeFile(String outputFilename){
        //Write JSON file
        try (FileWriter file = new FileWriter(outputFilename)) {

            file.write(output_records.toJSONString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

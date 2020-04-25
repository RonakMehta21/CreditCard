import java.io.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
public class JsonFileParser implements FileParser{

    private JSONParser jsonParser;
    private List<String> records;


    public JsonFileParser() {
        records = new ArrayList<String>();
    }

    @Override
    public void fetchRecords(String inputFilename){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(inputFilename))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            //Iterate over employee array
//            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );

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

    }


    @Override
    public void writeFile(String outputFilename){

    }
}

import java.io.File;
import java.util.List;

public class FileContext {
    private FileParser fileParser;
    private List<String> records;
    public void parse(String inputFilename, String outputFilename){
        try {
            if (inputFilename.endsWith(".csv")) {
                fileParser = new CsvFileParser();
            } else if (inputFilename.endsWith(".json")) {
                fileParser = new JsonFileParser();
            } else if (inputFilename.endsWith(".xml")) {
                fileParser = new XMLFileParser();
            } else {
                System.out.print(inputFilename.substring(inputFilename.lastIndexOf(".") + 1).toLowerCase() + " is not a supported file type");
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

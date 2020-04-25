import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface FileParser {
    void processRecords(String outputFilename);

    void fetchRecords(String inputFilename);

    void writeFile(String outputFilename) throws TransformerException;
}

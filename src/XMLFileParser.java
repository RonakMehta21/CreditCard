import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;


public class XMLFileParser implements FileParser{
    private NodeList records;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    Document outputDoc;
    public XMLFileParser() throws ParserConfigurationException {
        records = new NodeList() {
            @Override
            public Node item(int index) {
                return null;
            }

            @Override
            public int getLength() {
                return 0;
            }
        };

        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
    }

    @Override
    public void fetchRecords(String inputFilename){
        try {
            File file = new File(inputFilename);
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            records = doc.getElementsByTagName("row");
        }catch(Exception e){
            System.out.println("Error:"+e);
        }
    }

    @Override
    public void processRecords(String outputFilename){
        try {
            RecordsIterator iterator = new RecordsIteratorImpl(records);
            outputDoc = dBuilder.newDocument();

            Element root = outputDoc.createElement("root");
            outputDoc.appendChild(root);

            Element element;
            String card_number = "";
            Card c = null;
            String output = "";
            String card_type = "Invalid";
            String error_message = "None";
            while (!iterator.isDone()) {
                element = (Element) iterator.currentNode();
                card_number = element.getElementsByTagName("CardNumber").item(0).getTextContent();
                c = new Card(card_number);
                output = c.validateCardType();

                if (output.equals("Invalid")) {
                    error_message = "InvalidCardNumber";
                } else {
                    card_type = output;
                }

                Element row = outputDoc.createElement("row");
                root.appendChild(row);

                Element cardNumber = outputDoc.createElement("CardNumber");
                cardNumber.appendChild(outputDoc.createTextNode(card_number));
                row.appendChild(cardNumber);

                Element cardType = outputDoc.createElement("CardType");
                cardType.appendChild(outputDoc.createTextNode(card_type));
                row.appendChild(cardType);

                Element error = outputDoc.createElement("Error");
                error.appendChild(outputDoc.createTextNode(error_message));
                row.appendChild(error);

                iterator.next();
            }

            this.writeFile(outputFilename);
        }catch(Exception e){
            System.out.println("Error:"+e);
        }
    }

    @Override
    public void writeFile(String outputFilename) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(outputDoc);
        StreamResult streamResult = new StreamResult(new File(outputFilename));

        transformer.transform(domSource, streamResult);
    }
}

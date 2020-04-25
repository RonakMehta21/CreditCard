import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;

public class RecordsIteratorImpl implements RecordsIterator{

    private List<String> csv_records;
    private NodeList xml_records;
    private int cursor;
    private int max;

    public RecordsIteratorImpl(List<String> r){
        csv_records = r;
        cursor = 0;
        max = r.size();
    }

    public RecordsIteratorImpl(NodeList r){
        xml_records = r;
        cursor = 0;
        max = r.getLength();
    }

    public void next(){
        cursor++;
    }

    public String currentString(){
        return csv_records.get(cursor);
    }

    public Node currentNode(){
        return xml_records.item(cursor);
    }

    public boolean isDone(){
         return (max==cursor);
    }
}

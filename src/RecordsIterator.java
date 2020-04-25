import org.w3c.dom.Node;

public interface RecordsIterator {

    public boolean isDone();

    public void next();

    public String currentString();

    public Node currentNode();

}
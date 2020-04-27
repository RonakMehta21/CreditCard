package com;

import javax.xml.transform.TransformerException;

public interface FileParser {
    void processRecords(String outputFilename);

    void fetchRecords(String inputFilename);

    void writeFile(String outputFilename) throws TransformerException;
}

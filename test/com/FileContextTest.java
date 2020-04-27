package com;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileContextTest {
    @Test
    public void testParse(){
        FileContext fc = new FileContext();
        String input_filename = "input.csv";
        String output_filename = "output.csv";

        CsvFileParser csvFileParser = new CsvFileParser();
        JsonFileParser jsonFileParser = new JsonFileParser();
        fc.parse(input_filename,output_filename);{
            assertTrue(csvFileParser instanceof FileParser);
        }
    }
}
package com;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class JsonFileParserTest {

    private JsonFileParser jsonFileParserUnderTest;

    @Before
    public void setUp() {

        jsonFileParserUnderTest = new JsonFileParser();
    }

    @Test
    public void testProcessEachValidRecord() {
        // Setup
        final JSONObject record = new JSONObject(new HashMap<>());
        final JSONObject expectedResult = new JSONObject(new HashMap<>());

        record.put("CardNumber","5410000000000000");
        record.put("ExpirationDate","3/20/2030");
        record.put("NameOfCardholder","Alice");

        expectedResult.put("CardNumber",Long.parseLong("5410000000000000"));
        expectedResult.put("CardType","MasterCard");
        expectedResult.put("Error","None");

        // Run the test
        final JSONObject result = jsonFileParserUnderTest.processEachRecord(record);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}

package com;

import org.junit.Test;

import static org.junit.Assert.*;

public class MasterCardTest {

    @Test
    public void testValidateMasterCardType() {
        MasterCard mc = new MasterCard("5.41E+15");
        String result = mc.validateCardType("5.41E+15");
        assertEquals("MasterCard", result);
    }

    @Test
    public void testSecondDigitNotInclusiveOneAndFive(){
        MasterCard mc = new MasterCard("5.61E+15");
        String result = mc.validateCardType("5.61E+15");
        assertEquals("Invalid", result);
    }

    @Test
    public void testNoMasterCardNumber(){
        MasterCard mc = new MasterCard("");
        String result = mc.validateCardType("");
        assertEquals("Invalid", result);
    }

    @Test
    public void testMasterCardNumberWithWhiteSpaces(){
        MasterCard mc = new MasterCard("  ");
        String result = mc.validateCardType("  ");
        assertEquals("Invalid", result);
    }

    @Test
    public void testMasterCardNumberWithSpecialCharacters(){
        MasterCard mc = new MasterCard("541000000+000000");
        String result = mc.validateCardType("541000000+000000");
        assertEquals("Invalid", result);
    }

    @Test
    public void testMasterCardNumberWithCharacter(){
        MasterCard mc = new MasterCard("541000000v000000");
        String result = mc.validateCardType("541000000v000000");
        assertEquals("Invalid", result);
    }
}
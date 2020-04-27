package com;

import org.junit.Test;

import static org.junit.Assert.*;

public class AmericanExpressCardTest {

    @Test
    public void testValidateAmericanExpCardType() {
        AmericanExpressCard ax = new AmericanExpressCard("3.41E+14");
        String result = ax.validateCardType("3.41E+14");
        assertEquals("AmericanExpress", result);
    }

    @Test
    public void testNoAmericanExpNumber(){
        AmericanExpressCard ax = new AmericanExpressCard("");
        String result = ax.validateCardType("");
        assertEquals("Invalid", result);
    }

    @Test
    public void testSecondDigitAmericanExpNumber(){
        AmericanExpressCard ax = new AmericanExpressCard("351000000000000");
        String result = ax.validateCardType("351000000000000");
        assertEquals("Invalid", result);
    }

    @Test
    public void Should_ReturnValid_When_SecondDigitIs7(){
        AmericanExpressCard ax = new AmericanExpressCard("371000000000000");
        String result = ax.validateCardType("371000000000000");
        assertEquals("AmericanExpress", result);
    }

    @Test
    public void testAmericanExpWithSpecialCharacters(){
        AmericanExpressCard ax = new AmericanExpressCard("37100000,000000");
        String result = ax.validateCardType("37100000,000000");
        assertEquals("Invalid", result);
    }

    @Test
    public void testAmericanExpWithWhiteSpacesInBetween(){
        AmericanExpressCard ax = new AmericanExpressCard("37100000  000000");
        String result = ax.validateCardType("37100000  000000");
        assertEquals("Invalid", result);
    }

    @Test
    public void testAmericanExpWithOnlyWhiteSpaces(){
        AmericanExpressCard ax = new AmericanExpressCard("   ");
        String result = ax.validateCardType("   ");
        assertEquals("Invalid", result);
    }

    @Test
    public void testAmericanExpWithFirstDigit(){
        AmericanExpressCard ax = new AmericanExpressCard("471000000000000");
        String result = ax.validateCardType("471000000000000");
        assertEquals("Invalid", result);
    }

}
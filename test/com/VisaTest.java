package com;

import org.junit.Test;

import static org.junit.Assert.*;

public class VisaTest {

    @Test
    public void testNoVisaCardNumber(){
        Visa v = new Visa("");
        String result = v.validateCardType("");
        assertEquals("Invalid", result);
    }

    @Test
    public void testValidateVisaCardType() {
        Visa v = new Visa("4.12E+12");
        String result = v.validateCardType("4.12E+12");
        assertEquals("Visa", result);
    }

    @Test
    public void testVisaCardWhiteSpaces() {
        Visa v = new Visa("  ");
        String result = v.validateCardType("  ");
        assertEquals("Invalid", result);
    }

    @Test
    public void testVisaCardWith14Digits() {
        Visa v = new Visa("45678901234567");
        String result = v.validateCardType("45678901234567");
        assertEquals("Invalid", result);
    }

    @Test
    public void testVisaCardWithCharacters() {
        Visa v = new Visa("45678a1234567");
        String result = v.validateCardType("45678a1234567");
        assertEquals("Invalid", result);
    }

    @Test
    public void testWrongVisaNumber() {
        Visa v = new Visa("5567891234567765");
        String result = v.validateCardType("5567891234567765");
        assertEquals("Invalid", result);
    }

    @Test
    public void testValid16DigitVisaCard() {
        Visa v = new Visa("4567891234567765");
        String result = v.validateCardType("4567891234567765");
        assertEquals("Visa", result);
    }

    @Test
    public void test_Invalid_SpecialCharacter_In_VisaCard() {
        Visa v = new Visa("45678912_4567765");
        String result = v.validateCardType("45678912_4567765");
        assertEquals("Invalid", result);
    }
}
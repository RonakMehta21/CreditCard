package com;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiscoverTest {

    @Test
    public void testNoDiscoverCardNumber(){
        Discover d = new Discover("");
        String result = d.validateCardType("");
        assertEquals("Invalid", result);
    }

    @Test
    public void testValidateDiscoverCardType() {
        Discover d = new Discover("6.011E+15");
        String result = d.validateCardType("6.011E+15");
        assertEquals("Discover", result);
    }

    @Test
    public void testFalseDiscoverCardType() {
        Discover d = new Discover("6.010E+15");
        String result = d.validateCardType("6.010E+15");
        assertEquals("Invalid", result);
    }

    @Test
    public void testInvalidDiscoverCardType(){
        Discover d = new Discover("6123456789012345");
        String result = d.validateCardType("6123456789012345");
        assertEquals("Invalid", result);
    }

    @Test
    public void testWrongDiscoverCardType(){
        Discover d = new Discover("1234567890126011");
        String result = d.validateCardType("1234567890126011");
        assertEquals("Invalid", result);
    }

    @Test
    public void Should_ReturnInvalid_WhenCharacter_InFirstFourIndex(){
        Discover d = new Discover("6a11567890123456");
        String result = d.validateCardType("6a11567890123456");
        assertEquals("Invalid", result);
    }

    @Test
    public void Should_ReturnInvalid_WhenCharacter_InAnyIndex(){
        Discover d = new Discover("601156789012_456");
        String result = d.validateCardType("601156789012_456");
        assertEquals("Invalid", result);
    }

    @Test
    public void Should_ReturnInvalid_With_WhiteSpaces(){
        Discover d = new Discover("   ");
        String result = d.validateCardType("  ");
        assertEquals("Invalid", result);
    }

    @Test
    public void testValidDiscoverCardType(){
        Discover d = new Discover("6011582364756127");
        String result = d.validateCardType("6011582364756127");
        assertEquals("Discover", result);
    }
}
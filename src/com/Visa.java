package com;

public class Visa extends Card {

    public Visa(String card_number) {
        super(card_number);
    }

    @Override
    String validateCardType(String card_number) {
        if (card_number.equals("")){
            return "Invalid";
        }
        try {
            long number = Double.valueOf(card_number).longValue();
            String value = Long.toString(number);
            if ((value.length()==13 || value.length()==16) && value.charAt(0)=='4'){
                return "Visa";
            }
        }catch(Exception e) {
            return "Invalid";
        }
        return "Invalid";
    }
}

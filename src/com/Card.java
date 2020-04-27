package com;

abstract class Card {

    private String card_number;

    public Card(String card_number){
        this.card_number = card_number;
    }

    abstract String validateCardType(String card_number);

}

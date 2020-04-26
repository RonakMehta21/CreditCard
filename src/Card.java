public class Card {

    private String card_number;
    private String card_type;

    public Card(String card_number){
        this.card_number = card_number;
        this.card_type = "";
    }

    public String validateCardType(){

        //TODO Factory Implementation of Card
        //TODO Validate Card Number and return Card Type

        return "VISA";
        //return card_type;
    }

}

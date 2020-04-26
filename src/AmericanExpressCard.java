public class AmericanExpressCard extends Card{

    public AmericanExpressCard(String card_number) {
        super(card_number);
    }

    @Override
    String validateCardType(String card_number) {
        return "AmericanExpress";
    }
}

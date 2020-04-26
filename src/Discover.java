public class Discover extends Card{

    public Discover(String card_number) {
        super(card_number);
    }

    @Override
    String validateCardType(String card_number) {
        return "Discover";
    }
}

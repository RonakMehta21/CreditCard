public class Visa extends Card{

    public Visa(String card_number) {
        super(card_number);
    }

    @Override
    String validateCardType(String card_number) {
        return "Visa";
    }
}

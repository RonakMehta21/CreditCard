public class MasterCard extends Card{

    public MasterCard(String card_number) {
        super(card_number);
    }

    @Override
    String validateCardType(String card_number) {
        return "MasterCard";
    }
}

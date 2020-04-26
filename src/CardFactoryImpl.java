import org.apache.commons.lang3.StringUtils;
public class CardFactoryImpl implements CardFactory{
    public Card createCard(String card_number){

        long number = Double.valueOf(card_number).longValue();
        System.out.println(number);
        String value = Long.toString(number);
        if(value.length()<=16){

            if(value.length()==15){
                return new AmericanExpressCard(card_number);

            }else if(value.length()==16 && value.charAt(0)=='5'){
                return new MasterCard(card_number);

            }else if(value.length()==16 && value.charAt(0)=='6'){
                return new Discover(card_number);

            }else if(value.length()>=13 && value.charAt(0)=='4'){
                return new Visa(card_number);

            }else{
                return null;
            }

        }
        return null;
    }
}

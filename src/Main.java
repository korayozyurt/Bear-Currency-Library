import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
	
    public static void main(String[] args) {
      BearCurrencyConverter bearCurrencyConverter = BearCurrencyConverter.getInstance();
        try {
            Date date = new GregorianCalendar(2005, Calendar.JUNE, 06).getTime();
            System.out.println("6 haziran dolar alış: " + bearCurrencyConverter.getExchangeRate(BearCurrencyCode.USD, BearCurrencyAttributes.BANKNOTE_BUYING, date));

            for(BearCurrency bearCurrency : bearCurrencyConverter.getAllCurrencies()) {
                System.out.println(bearCurrency.toString());
                System.out.println("************");
            }

            System.out.println("USD to AUD: " +
                    bearCurrencyConverter.getExchangeRate(BearCurrencyCode.USD, BearCurrencyCode.AUD, BearCurrencyAttributes.BANKNOTE_BUYING));

            System.out.println("Forex buying of EURO: " + bearCurrencyConverter.getExchangeRate(BearCurrencyCode.EUR, BearCurrencyAttributes.FOREX_BUYING));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (BearCurrencyException e) {
            e.printStackTrace();
        }
    }

}
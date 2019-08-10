import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface IBearCurrencyConverter {

	List<BearCurrency> getAllCurrencies() throws IOException, ParserConfigurationException, SAXException;
	List<BearCurrency> getAllCurrencies(Date date) throws IOException, ParserConfigurationException, SAXException;
	BearCurrency getBearCurrency(BearCurrencyCode bearCurrencyCode) throws IOException, ParserConfigurationException, SAXException, BearCurrencyException;
	BearCurrency getBearCurrency(BearCurrencyCode bearCurrencyCode, Date date) throws IOException, ParserConfigurationException, SAXException, BearCurrencyException;
	BigDecimal getExchangeRate(BearCurrencyCode bearCurrencyCode, BearCurrencyAttributes bearCurrencyAttribute) throws IOException, ParserConfigurationException, SAXException, BearCurrencyException;
	BigDecimal getExchangeRate(BearCurrencyCode bearCurrencyCode, BearCurrencyAttributes bearCurrencyAttribute, Date date) throws IOException, ParserConfigurationException, SAXException, BearCurrencyException;
	BigDecimal getExchangeRate(BearCurrencyCode from, BearCurrencyCode to, BearCurrencyAttributes bearCurrencyAttribute) throws IOException, ParserConfigurationException, SAXException, BearCurrencyException;
	BigDecimal getExchangeRate(BearCurrencyCode from, BearCurrencyCode to, BearCurrencyAttributes bearCurrencyAttribute, Date date) throws IOException, ParserConfigurationException, SAXException, BearCurrencyException;

}
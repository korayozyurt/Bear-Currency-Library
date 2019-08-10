import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class BearCurrencyConverter implements IBearCurrencyConverter {

	private static final String CURRENCY_CODE = "CurrencyCode";	// USD, AUD...
	private static final String UNIT = "Unit";
	private static final String FOREX_BUYING = "ForexBuying";
	private static final String FOREX_SELLING = "ForexSelling";
	private static final String BANKNOTE_BUYING = "BanknoteBuying";
	private static final String BANKNOTE_SELLING = "BanknoteSelling";
	private static final String CROSSRATE_USD = "CrossRateUSD";

	private static BearCurrencyConverter instance;

	private BearCurrencyConverter() {}

	public static synchronized BearCurrencyConverter getInstance() {
		if(instance == null) {
			instance = new BearCurrencyConverter();
		}
		return instance;
	}

	@Override
	public List<BearCurrency> getAllCurrencies() throws IOException, ParserConfigurationException, SAXException  {
		return getAllCurrencies(null);
	}

	@Override
	public List<BearCurrency> getAllCurrencies(Date date) throws IOException, ParserConfigurationException, SAXException {
		List<BearCurrency> bearCurrencies = new ArrayList<>();
		NodeList nodes = getCurrencyElements(date);
		for(int i=0;i<nodes.getLength();i++) {
			Element element = (Element) nodes.item(i);
			bearCurrencies.add(fillBearCurrencyByElement(element));
		}

		return bearCurrencies;
	}

	@Override
	public BearCurrency getBearCurrency(BearCurrencyCode bearCurrencyCode) throws IOException, ParserConfigurationException, SAXException, BearCurrencyException {
		return getBearCurrency(bearCurrencyCode, null);
	}

	@Override
	public BearCurrency getBearCurrency(BearCurrencyCode bearCurrencyCode, Date date) throws IOException, ParserConfigurationException, SAXException, BearCurrencyException {
		NodeList nodes = getCurrencyElements(date);
		for(int i=0;i<nodes.getLength();i++) {
			Element element = (Element) nodes.item(i);
			if(element.getAttribute(CURRENCY_CODE).equals(bearCurrencyCode.name())) {
				return fillBearCurrencyByElement(element);
			}
		}
		throw new BearCurrencyException("Currency Code Not Found");
	}

	@Override
	public BigDecimal getExchangeRate(BearCurrencyCode bearCurrencyCode, BearCurrencyAttributes bearCurrencyAttribute) throws IOException, ParserConfigurationException, SAXException, BearCurrencyException {
		return getExchangeRate(bearCurrencyCode, bearCurrencyAttribute, null);
	}

	@Override
	public BigDecimal getExchangeRate(BearCurrencyCode bearCurrencyCode, BearCurrencyAttributes bearCurrencyAttribute, Date date) throws IOException, ParserConfigurationException, SAXException, BearCurrencyException {
		NodeList nodes = getCurrencyElements(date);
		for(int i=0;i<nodes.getLength();i++) {
			Element element = (Element) nodes.item(i);
			if(element.getAttribute(CURRENCY_CODE).equals(bearCurrencyCode.name())) {
				return getDoubleValue(element, bearCurrencyAttribute.getAttribute());
			}
		}
		throw new BearCurrencyException("Currency Code or Attribute Not Found");
	}

	@Override
	public BigDecimal getExchangeRate(BearCurrencyCode from, BearCurrencyCode to,
								  BearCurrencyAttributes bearCurrencyAttribute) throws IOException, ParserConfigurationException, SAXException, BearCurrencyException {
		return getExchangeRate(from, to, bearCurrencyAttribute, null);
	}

	@Override
	public BigDecimal getExchangeRate(BearCurrencyCode from, BearCurrencyCode to,
								  BearCurrencyAttributes bearCurrencyAttribute, Date date) throws IOException, ParserConfigurationException, SAXException, BearCurrencyException {
		BigDecimal f = getExchangeRate(from, bearCurrencyAttribute, date);
		BigDecimal t = getExchangeRate(to, bearCurrencyAttribute, date);
		return f.divide(t, 2, RoundingMode.HALF_UP);
	}

	private BearCurrency fillBearCurrencyByElement(Element element) {
		BearCurrency bearCurrency = new BearCurrency();
		bearCurrency.setCurrencyName(element.getAttributeNode(CURRENCY_CODE).getValue());
		bearCurrency.setUnit(getDoubleValue(element, UNIT));
		bearCurrency.setForexBuying(getDoubleValue(element, FOREX_BUYING));
		bearCurrency.setForexSelling(getDoubleValue(element, FOREX_SELLING));
		bearCurrency.setBanknoteBuying(getDoubleValue(element, BANKNOTE_BUYING));
		bearCurrency.setBanknoteSelling(getDoubleValue(element, BANKNOTE_SELLING));
		bearCurrency.setCrossRateUsd(getDoubleValue(element, CROSSRATE_USD));
		return bearCurrency;
	}

	private NodeList getCurrencyElements(Date date) throws IOException, ParserConfigurationException, SAXException {
		String url = "http://www.tcmb.gov.tr/kurlar/";
		url += date == null ? "today.xml" : parseTCMBDate(date) + ".xml";
		InputStream xml = new URL(url).openStream();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		return document.getElementsByTagName("Currency");
	}

	private String getValue(Element element, String label) {
		String result = "";
		Element requiredElement = (Element) element.getElementsByTagName(label).item(0);

		Node child = requiredElement.getFirstChild();
		if(child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			result = cd.getData();
		}
		return result;
	}

	private BigDecimal getDoubleValue(Element element, String label) {
		String value = getValue(element, label);
		return value.equals("") ? null : BigDecimal.valueOf(Double.parseDouble(value));
	}

	private String parseTCMBDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		int monthN = calendar.get(Calendar.MONTH) + 1;
		String month = monthN < 10 ? "0" + monthN  : String.valueOf(monthN);
		String day = calendar.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + calendar.get(Calendar.DAY_OF_MONTH) : String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		return year + "" + month + "/" + day + month + year;
	}

}

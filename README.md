# Bear-Currency-Library ![Alt-text](https://github.com/koryOzyurt/Bear-Currency-Api/blob/master/logo.jpg "Logo")

***

This library return currencies from **Central Bank of Republic Of Turkey**.  
Capabilities are listed as;  
* getAllCurrencies() 
* getAllCurrencies(Date date)
  > returns *BearCurrency* Object.
* getBearCurrency(BearCurrencyCode bearCurrencyCode)
* getBearCurrency(BearCurrencyCode bearCurrencyCode, Date date)
  > returns *BearCurrency* Object. BearCurrencyCode is an enum list. The enum list would be
  * USD
  * AUD and etc... **(default Turkish Lira)**
* getExchangeRate(BearCurrencyCode bearCurrencyCode, BearCurrencyAttributes bearCurrencyAttribute)
* getExchangeRate(BearCurrencyCode bearCurrencyCode, BearCurrencyAttributes bearCurrencyAttribute)
  > returns BigDecimal. *BearCurrencyAttributes* is an enum list. User can specify the currency attribute. 
  * FOREX_BUYING
  * BANKNOTE_SELLING
  * CROSSRATE_USD and etc...
 * BigDecimal getExchangeRate(BearCurrencyCode from, BearCurrencyCode to, BearCurrencyAttributes bearCurrencyAttribute)
 * BigDecimal getExchangeRate(BearCurrencyCode from, BearCurrencyCode to, BearCurrencyAttributes bearCurrencyAttribute, Date date)
  > returns BigDecimal. User can convert any currency to other. For Example USD to GBP.  
  
  ## Code Examples
  
  First of all get an instance of BearCurrencyConverter.
  ```Java
  BearCurrencyConverter bearCurrencyConverter = BearCurrencyConverter.getInstance();
  ```
  
  Then to get currency Tl to USD at specific date:
  
  ```Java
   Date date = new GregorianCalendar(2005, Calendar.JUNE, 06).getTime();
   System.out.println("6 june TL to USD: " + bearCurrencyConverter.getExchangeRate(BearCurrencyCode.USD, BearCurrencyAttributes.BANKNOTE_BUYING, date));
  ```
  > Remember! Default currency is Turkish Lira.
  
  To get USD to AUD BankNoteBuying:
  
  ```Java
  System.out.println("USD to AUD: " +
                    bearCurrencyConverter.getExchangeRate(BearCurrencyCode.USD, BearCurrencyCode.AUD, BearCurrencyAttributes.BANKNOTE_BUYING));
  ```

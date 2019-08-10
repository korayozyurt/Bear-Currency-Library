public enum BearCurrencyAttributes {
	UNIT("my unit"),
	FOREX_BUYING("ForexBuying"),
	FOREX_SELLING("ForexSelling"),
	BANKNOTE_BUYING("BanknoteBuying"),
	BANKNOTE_SELLING("BanknoteSelling"),
	CROSSRATE_USD("CrossRateUSD");

	private String attribute;

	BearCurrencyAttributes(String attribute) {
		this.attribute = attribute;
	}

	public String getAttribute() {
		return attribute;
	}

}
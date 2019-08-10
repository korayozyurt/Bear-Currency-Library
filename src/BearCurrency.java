import java.math.BigDecimal;

public class BearCurrency {

	private String currencyName;
	private BigDecimal unit;
	private BigDecimal forexBuying;
	private BigDecimal forexSelling;
	private BigDecimal banknoteBuying;
	private BigDecimal banknoteSelling;
	private BigDecimal crossRateUsd;

	public BearCurrency() {

	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public BigDecimal getUnit() {
		return unit;
	}

	public void setUnit(BigDecimal unit) {
		this.unit = unit;
	}

	public BigDecimal getForexBuying() {
		return forexBuying;
	}

	public void setForexBuying(BigDecimal forexBuying) {
		this.forexBuying = forexBuying;
	}

	public BigDecimal getForexSelling() {
		return forexSelling;
	}

	public void setForexSelling(BigDecimal forexSelling) {
		this.forexSelling = forexSelling;
	}

	public BigDecimal getBanknoteBuying() {
		return banknoteBuying;
	}

	public void setBanknoteBuying(BigDecimal banknoteBuying) {
		this.banknoteBuying = banknoteBuying;
	}

	public BigDecimal getBanknoteSelling() {
		return banknoteSelling;
	}

	public void setBanknoteSelling(BigDecimal banknoteSelling) {
		this.banknoteSelling = banknoteSelling;
	}

	public BigDecimal getCrossRateUsd() {
		return crossRateUsd;
	}

	public void setCrossRateUsd(BigDecimal crossRateUsd) {
		this.crossRateUsd = crossRateUsd;
	}

	@Override
	public String toString() {
		return "BearCurrency{" +
				"currencyName='" + currencyName + '\'' +
				", unit=" + unit +
				", forexBuying=" + forexBuying +
				", forexSelling=" + forexSelling +
				", banknoteBuying=" + banknoteBuying +
				", banknoteSelling=" + banknoteSelling +
				", crossRateUsd=" + crossRateUsd +
				'}';
	}
}
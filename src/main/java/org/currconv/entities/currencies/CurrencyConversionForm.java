package org.currconv.entities.currencies;

/**
 *  Currency conversion form
 */
public class CurrencyConversionForm {

	private String originalCode;
    private String convertedCode;
	private String originalValue;
    private String convertedValue;

	public String getOriginalCode() {
		return this.originalCode;
	}

	public void setOriginalCode(String originalCode) {
		this.originalCode = originalCode;
	}

	public String getOriginalValue() {
		return this.originalValue;
	}

	public void setOriginalValue(String originalValue) {
		this.originalValue = originalValue;
	}

	public String getConvertedCode() {
		return this.convertedCode;
	}

	public void setConvertedCode(String convertedCode) {
		this.convertedCode = convertedCode;
	}

	public String getConvertedValue() {
		return this.convertedValue;
	}

	public void setConvertedValue(String convertedValue) {
		this.convertedValue = convertedValue;
	}

}

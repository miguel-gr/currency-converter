package org.currconv.entities.external;

import org.joda.money.Money;
import org.joda.money.CurrencyUnit;
import java.util.Date;
import java.util.Map;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Rates response from openexchangerates.org
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatesResponse {

	private long timestamp;
    private Map<String, BigDecimal> rates;

	public RatesResponse() {
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Map<String, BigDecimal> getRates() {
		return this.rates;
	}

	public void setConversionDate(Map<String, BigDecimal> rates) {
		this.rates = rates;
	}

}

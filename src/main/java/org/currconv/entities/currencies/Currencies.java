package org.currconv.entities.currencies;

import java.util.Map;
import java.util.TreeMap;

/**
 * Currencies supported
 */
public class Currencies {

	private static final Map<String, String> currencies;
    static {
        currencies = new TreeMap<String, String>();
        currencies.put("AUD", "Australian Dollar");
        currencies.put("CAD", "Canadian Dollar");
        currencies.put("CHF", "Swiss Franc");
        currencies.put("EUR", "Euro");
        currencies.put("GBP", "British pound");
        currencies.put("JPY", "Japanese Yen");
        currencies.put("USD", "United States Dollar");
    }

	public static Map<String, String> getCurrencies() {
		return Currencies.currencies;
	}


}

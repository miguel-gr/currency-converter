package org.currconv.services;

import java.util.List;

import org.currconv.entities.currencies.CurrencyConversion;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public interface CurrencyConverterService {

    CurrencyConversion getCurrencyConversion(final Money originalMoney, final CurrencyUnit currencyToConvert);

    void saveCurrencyConversion(CurrencyConversion currencyConversion);

    public List<CurrencyConversion> findRecentConversions(final int maxResults, final int userId);

}
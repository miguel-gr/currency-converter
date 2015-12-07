package org.currconv.dao;

import java.util.List;
 
import org.currconv.entities.currencies.CurrencyConversion;
 
public interface CurrencyConversionDao {

    CurrencyConversion findById(int id);

    void saveCurrencyConversion(CurrencyConversion currencyConversion);

    List<CurrencyConversion> findRecentConversions(final int maxResults, final int userId);
 
}
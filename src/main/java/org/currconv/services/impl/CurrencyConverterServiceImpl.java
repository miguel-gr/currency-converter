package org.currconv.services.impl;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Map;
import java.net.URL;
import org.currconv.dao.UserDao;
import org.currconv.entities.user.User;
import org.currconv.dao.CurrencyConversionDao;
import org.currconv.dao.RateDao;
import org.currconv.entities.currencies.CurrencyConversion;
import org.currconv.entities.currencies.Rate;
import org.currconv.services.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.apache.commons.collections4.CollectionUtils;
import org.currconv.entities.external.RatesResponse;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.IOException;
import org.currconv.service.utils.ConversionUtils;
import java.math.RoundingMode;

@Service("currencyConverterService")
@Transactional
public class CurrencyConverterServiceImpl implements CurrencyConverterService {
 
    private Log log = LogFactory.getLog(CurrencyConverterServiceImpl.class);
 
    @Autowired
    private CurrencyConversionDao currConvdao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RateDao rateDao;

    public CurrencyConversion getCurrencyConversion(final Money base, final CurrencyUnit destinyCurrency){
        BigDecimal destinyAmount;
        if(base.getCurrencyUnit().getCode().equals(destinyCurrency.getCode())){
            destinyAmount = base.getAmount();
        } else {
            List<Rate> rates = getRates();
            BigDecimal usdBasedBaseRate = null;
            BigDecimal usdBasedDestinyRate = null;
            for (Rate rate : rates) {
                if(base.getCurrencyUnit().getCode().equals(rate.getCode())){
                    usdBasedBaseRate = rate.getRate();
                }
                if(destinyCurrency.getCode().equals(rate.getCode())){
                    usdBasedDestinyRate = rate.getRate();
                }
            }
            destinyAmount = ConversionUtils.getConversionUsdBased(base.getCurrencyUnit().getCode(), base.getAmount(), usdBasedBaseRate, usdBasedDestinyRate);
        }
        Money destiny = Money.of(destinyCurrency, destinyAmount.setScale(destinyCurrency.getDecimalPlaces(), RoundingMode.CEILING));
        CurrencyConversion res = new CurrencyConversion(base, destiny);
        return res;
    }
    
    private List<Rate> getRates(){
        List<Rate> recentRates = rateDao.findRecentRates();
        if(CollectionUtils.isEmpty(recentRates)){
            recentRates = getRatesFromExternalService();
            for (Rate rate : recentRates) {
                rateDao.saveRate(rate);
            }
        }
        return recentRates;
    }
    
    /**
     * Get rates from an external service.
     * TODO create a service for this functionality
     */
    private List<Rate> getRatesFromExternalService(){
        ObjectMapper mapper = new ObjectMapper();
        RatesResponse ratesResponse = new RatesResponse();
        final String openexchangeratesUrl = "https://openexchangerates.org/api/latest.json?app_id=cba3a0436c7f478589b62c734d90c1c9";
        try {
            ratesResponse = mapper.readValue(new URL(openexchangeratesUrl), RatesResponse.class);
        } catch (JsonGenerationException e) {
			log.error("getRatesFromExternalService", e);
		} catch (JsonMappingException e) {
			log.error("getRatesFromExternalService", e);
		} catch (IOException e) {
			log.error("getRatesFromExternalService", e);
		}
        List<Rate> rates = new ArrayList<Rate>();
        if(ratesResponse.getRates()!=null){
            for (Map.Entry<String, BigDecimal> entry : ratesResponse.getRates().entrySet()) {
                Rate rate = new Rate(entry.getKey(), entry.getValue(), ratesResponse.getTimestamp()*1000);
                rates.add(rate);
            }
        }
        return rates;
    }
 
    public void saveCurrencyConversion(CurrencyConversion currencyConversion) {
        currConvdao.saveCurrencyConversion(currencyConversion);
    }
 
    public List<CurrencyConversion> findRecentConversions(final int maxResults, final int userId){
        return currConvdao.findRecentConversions(maxResults, userId);
    }

}
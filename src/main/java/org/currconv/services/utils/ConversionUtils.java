package org.currconv.service.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Conversion utils
 */
public class ConversionUtils {
    
    private ConversionUtils(){};

    public static BigDecimal getConversionUsdBased(
            final String destinycode, final BigDecimal amount, final BigDecimal usdBasedBaseRate, BigDecimal usdBasedDestinyRate){

        if(destinycode.equals("USD")){
            return amount.multiply(usdBasedDestinyRate);
        }else{
            BigDecimal inverted = BigDecimal.ONE.divide(usdBasedBaseRate, 2, RoundingMode.HALF_UP);
            BigDecimal baseRatio = usdBasedDestinyRate.multiply(inverted);
            return amount.multiply(baseRatio); 
        }
    }

}

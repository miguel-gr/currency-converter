package org.currconv.service.utils;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.math.BigDecimal;

public class ConversionUtilsTest {

    @DataProvider(name = "testDP")
    public static Object[][] conversionDataProvider() {
      return new Object[][] {
          {"USD", new BigDecimal("1"),  new BigDecimal("1"),     new BigDecimal("1"),     new BigDecimal("1")},
          {"USD", new BigDecimal("10"), new BigDecimal("1"),     new BigDecimal("1"),     new BigDecimal("10")},
          {"CAD", new BigDecimal("5"),  new BigDecimal("0.92"),  new BigDecimal("1.34"),  new BigDecimal("7.3030")},   //  5 EUR/CAD
          {"MXP", new BigDecimal("10"), new BigDecimal("0.92"),  new BigDecimal("16.69"), new BigDecimal("181.9210")}  // 10 EUR/MXP
      };
    }

    @Test(dataProvider = "testDP")
    public void testGetConversionUsdBased(
            final String code, final BigDecimal amount, final BigDecimal usdBasedBaseRate,
            BigDecimal usdBasedDestinyRate, BigDecimal expectedResult) {

        BigDecimal result = ConversionUtils.getConversionUsdBased(code, amount, usdBasedBaseRate, usdBasedDestinyRate);
        Assert.assertEquals(expectedResult, result);
    }
}

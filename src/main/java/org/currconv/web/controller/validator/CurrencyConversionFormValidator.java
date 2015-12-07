package org.currconv.web.controller.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.currconv.entities.currencies.Currencies;
import org.currconv.entities.currencies.CurrencyConversionForm;
import org.joda.money.Money;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator; 


@Component
public class CurrencyConversionFormValidator implements Validator {

    private Log log = LogFactory.getLog(CurrencyConversionFormValidator.class);

    private String[] notEmpty = {
        "originalCode", "originalValue", "convertedCode"
    };
    public boolean supports(Class<?> paramClass) {
		return CurrencyConversionForm.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
        for (String fieldName : notEmpty) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, fieldName, "validation.empty."+fieldName);
        }
        CurrencyConversionForm form = (CurrencyConversionForm)obj;
        boolean invalidOriginal = (StringUtils.isNotEmpty(form.getOriginalCode()) && Currencies.getCurrencies().get(form.getOriginalCode()) == null);
        if(invalidOriginal){
            form.setConvertedCode("");
            errors.rejectValue("originalValue", "validation.invalidOriginalCode");
            log.warn("Invalid base code");
        }
        if(StringUtils.isNotEmpty(form.getConvertedCode()) && Currencies.getCurrencies().get(form.getConvertedCode()) == null){
            form.setConvertedCode("");
            errors.rejectValue("originalValue", "validation.invalidConvertedCode");
            log.warn("Invalid destiny code");
        }
        try{
            if(invalidOriginal){
                Money.parse("USD "+form.getOriginalValue());
            } else {
                Money.parse(form.getOriginalCode()+" "+form.getOriginalValue());
            }
        }catch(IllegalArgumentException iae){
            errors.rejectValue("originalValue", "validation.invalidAmount");
        }catch(ArithmeticException ae){
            errors.rejectValue("originalValue", "validation.excesiveAmount");
        }
	}
}	
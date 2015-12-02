package org.currconv.web.controller.validator;

import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.StringUtils;
import org.currconv.entities.app.Locations;
import org.currconv.entities.user.User;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private String[] notEmpty = {
        "username", "email", "dateOfBirth", "street", "zip", "city", "country", "password", "passwordVer"
    };
    public boolean supports(Class<?> paramClass) {
		return User.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
        for (String fieldName : notEmpty) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, fieldName, "validation.empty."+fieldName);
        }
        User usr = (User)obj;
        if(null!=usr.getStreet() && !StringUtils.isAlphanumericSpace(usr.getStreet())){
            errors.rejectValue("street", "validation.mustBeAlphaNumberSpace");
        }
        if(null!=usr.getZip() && !StringUtils.isAlphanumericSpace(usr.getZip())){
            errors.rejectValue("zip", "validation.mustBeAlphaNumberSpace");
        }
        if(null!=usr.getCity() && !StringUtils.isAlphanumericSpace(usr.getCity())){
            errors.rejectValue("city", "validation.mustBeAlphaNumberSpace");
        }
        if(null!=usr.getUsername() && !StringUtils.isAlphanumeric(usr.getUsername())){
            errors.rejectValue("username", "validation.mustBeAlphaNumber");
        }
        
        if(usr.getDateOfBirth()!=null){
            DateTime dt = new DateTime(usr.getDateOfBirth());
            if(dt.isAfterNow()){
                errors.rejectValue("dateOfBirth", "validation.invalidDate");
            }
        }
        if(StringUtils.isNotEmpty(usr.getPasswordVer()) && !usr.getPassword().equals(usr.getPasswordVer())){
            errors.rejectValue("passwordVer", "validation.passwordsDontMatch");
        }
        if(StringUtils.isNotEmpty(usr.getCountry()) && null==Locations.getCountries().get(usr.getCountry())){
            errors.rejectValue("country", "validation.invalidCountry");
        }
        if(StringUtils.isNotEmpty(usr.getEmail())){
            try {
                InternetAddress emailAddr = new InternetAddress(usr.getEmail());
                emailAddr.validate(); // RFC822
            } catch (Exception ex) {
                errors.rejectValue("email", "validation.invalidEmail");
            }
        }

        //System.out.println("errors:\n------------------------------"+errors);
	}
}	
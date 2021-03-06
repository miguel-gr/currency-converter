package org.currconv.web.controller.validator;

import org.currconv.entities.user.UserLogin;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserLoginValidator implements Validator {

    private String[] notEmpty = {
        "username", "password"
    };
    public boolean supports(Class<?> paramClass) {
		return UserLogin.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
        for (String fieldName : notEmpty) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, fieldName, "validation.empty."+fieldName);
        }
	}
}	
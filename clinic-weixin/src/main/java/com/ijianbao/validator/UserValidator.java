package com.ijianbao.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ijianbao.model.User;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "mobile", null, "mobile is empty.");  
		User user = (User) target;  
		if (StringUtils.isEmpty(user.getMobile()))  
			errors.rejectValue("password", null, "Password is empty.");  
		
	}

}

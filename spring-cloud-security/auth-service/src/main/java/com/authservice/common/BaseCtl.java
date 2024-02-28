package com.authservice.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class BaseCtl {
	public Response validate(BindingResult bindingResult) {
		Response res = new Response(true);
		System.out.println("inside the validate method of baseCtl...");
		if (bindingResult.hasErrors()) {

			res.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();

			List<FieldError> list = bindingResult.getFieldErrors();

			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
				System.out.println("Inside Basectl.....validate");
				System.out.println("Inside Basectl Field :: " + e.getField() + "  Message :: " + e.getDefaultMessage());
			});
			res.addInputError(errors);
		}
		return res;

	}
}

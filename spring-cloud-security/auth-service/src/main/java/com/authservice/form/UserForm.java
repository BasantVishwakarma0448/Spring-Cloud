package com.authservice.form;

import com.authservice.common.BaseDTO;
import com.authservice.common.BaseForm;
import com.authservice.dto.UserDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserForm extends BaseForm {
	@NotEmpty(message = "please enter firstname")
	private String firstName;

	@NotEmpty(message = "please enter lastname")
	private String lastName;

	@NotEmpty(message = "please enter loginId")
	private String loginId;

	@NotEmpty(message = "please enter Password")
	private String password;

	public UserForm() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public BaseDTO getDto() {
		UserDTO dto = initDTO(new UserDTO());
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setLoginId(loginId);
		dto.setPassword(password);

		return dto;
	}

}

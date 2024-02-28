package com.authservice.ctl;

import org.apache.hc.client5.http.UserTokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.common.BaseCtl;
import com.authservice.common.Response;
import com.authservice.dto.UserDTO;
import com.authservice.form.LoginForm;
import com.authservice.form.UserForm;
import com.authservice.service.UserServiceInt;
import com.authservice.utils.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Auth")
public class UserCtl extends BaseCtl {
	@Autowired
	private UserServiceInt serviceInt;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("save")
	public Response save(@Valid @RequestBody UserForm form, BindingResult bindingResult) {
		Response res = validate(bindingResult);
		if (res.isSuccess() == false) {
			return res;
		}

		try {
			UserDTO dto = (UserDTO) form.getDto();
			if (dto.getId() != null && dto.getId() > 0) {
				serviceInt.update(dto);
			} else {
				if (dto.getUniqueKey() != null && !dto.getUniqueKey().equals("")) {
					UserDTO existDto = (UserDTO) serviceInt.findByUniqueKey(dto.getUniqueKey(), dto.getUniquevalue());
					if (existDto != null) {
						res.addMessage(dto.getLabel() + " already exist");
						res.setSuccess(false);
						return res;
					}
				}

				serviceInt.add(dto);
			}
			res.addData(dto.getId());
		} catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	@PostMapping("login")
	public Response signIn(@Valid @RequestBody LoginForm form, BindingResult bindingResult) {

		Response res = validate(bindingResult);
		if (res.isSuccess() == false) {
			return res;
		}

		UserDTO dto = serviceInt.authenticate(form.getLoginId(), form.getPassword());

		if (dto != null) {
			String token = jwtUtil.generateToken(form.getLoginId());

			res.addData(dto);
			res.addResult("token", token);
		} else {
			res.addMessage("Login ID & Password is invalid...!!!");
		}
		return res;

	}
}

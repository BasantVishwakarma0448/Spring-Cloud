package com.authservice.service;

import com.authservice.dto.UserDTO;

public interface UserServiceInt {

	public long add(UserDTO dto);

	public void update(UserDTO dto);

	public void delete(long dto);

	public UserDTO findById(long pk);

	public UserDTO authenticate(String loginId, String password);

	public UserDTO findByLoginId(String loginId);

	public UserDTO findByUniqueKey(String attribute, String value);

}

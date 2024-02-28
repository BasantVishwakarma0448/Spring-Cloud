package com.authservice.dao;

import com.authservice.dto.UserDTO;

public interface UserDaoInt {
	public long add(UserDTO dto);

	public void update(UserDTO dto);

	public void delete(UserDTO dto);

	public UserDTO findByPk(long pk);

	public UserDTO findByUniqueKey(String attribute, String value);

}

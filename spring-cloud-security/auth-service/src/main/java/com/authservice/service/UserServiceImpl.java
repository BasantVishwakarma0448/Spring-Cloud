package com.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authservice.dao.UserDaoInt;
import com.authservice.dto.UserDTO;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserServiceInt {

	@Autowired
	private UserDaoInt daoInt;

	@Transactional
	@Override
	public long add(UserDTO dto) {
		daoInt.add(dto);
		return dto.getId();
	}

	@Override
	public void update(UserDTO dto) {
		daoInt.update(dto);
	}

	@Override
	public void delete(long pk) {
		UserDTO dto = findById(pk);
		daoInt.delete(dto);

	}

	@Override
	public UserDTO findById(long pk) {
		return daoInt.findByPk(pk);
	}

	@Override
	public UserDTO authenticate(String loginId, String password) {
		UserDTO dto = findByLoginId(loginId);

		if (dto != null) {
			if (password.equals(dto.getPassword())) {
				return dto;
			} else {
				System.out.println("User Not be Authenticated...!!!");
			}
		}
		return null;
	}

	@Override
	public UserDTO findByLoginId(String loginId) {
		return daoInt.findByUniqueKey("loginId", loginId);
	}

	@Transactional
	@Override
	public UserDTO findByUniqueKey(String attribute, String value) {
		return daoInt.findByUniqueKey(attribute, value);
	}

}

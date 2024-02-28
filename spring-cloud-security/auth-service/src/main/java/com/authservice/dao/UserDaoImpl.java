package com.authservice.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.authservice.dto.UserDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class UserDaoImpl implements UserDaoInt {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public long add(UserDTO dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	@Override
	public void update(UserDTO dto) {
		entityManager.merge(dto);
	}

	@Override
	public void delete(UserDTO dto) {
		entityManager.remove(dto);

	}

	@Override
	public UserDTO findByPk(long pk) {
		UserDTO dto = entityManager.find(UserDTO.class, pk);
		return dto;
	}

	@Override
	public UserDTO findByUniqueKey(String attribute, String value) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserDTO> cq = builder.createQuery(UserDTO.class);

		Root<UserDTO> root = cq.from(UserDTO.class);
		Predicate condition = builder.like(root.get(attribute), value);

		cq.where(condition);

		TypedQuery<UserDTO> query = entityManager.createQuery(cq);
		List<UserDTO> list = query.getResultList();

		UserDTO dto = null;
		if (list.size() > 0) {
			dto = list.get(0);
		}
		return dto;
	}

}

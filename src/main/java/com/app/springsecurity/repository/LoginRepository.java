package com.app.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.springsecurity.entity.Login;

@Repository

public interface LoginRepository extends JpaRepository<Login, Integer> {
	public Login findByLoginUser(String name);

}

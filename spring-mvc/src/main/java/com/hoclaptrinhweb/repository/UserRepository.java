package com.hoclaptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoclaptrinhweb.entity.UserEntity;

/**
 * 
 * @author Hello World
 * extends JpaRepository class for executing method to get and alter database
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findOneByUserNameAndStatus(String name, int status);
}

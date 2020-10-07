package com.hoclaptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoclaptrinhweb.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	CategoryEntity findOneByCode(String code);
}

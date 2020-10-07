package com.hoclaptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoclaptrinhweb.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
	/**
	 * 
	 */
}

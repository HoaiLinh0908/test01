package com.hoclaptrinhweb.dao;

import java.util.List;

import com.hoclaptrinhweb.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{

	List<CategoryModel> findAll();
	CategoryModel findOne(long id);
	CategoryModel findOneByCode(String code);
}

package com.hoclaptrinhweb.dao;

import java.util.List;

import com.hoclaptrinhweb.model.NewsModel;

public interface INewsDAO extends GenericDAO<NewsModel>{
	List<NewsModel> findAll();
}

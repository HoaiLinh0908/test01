package com.hoclaptrinhweb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoclaptrinhweb.dao.INewsDAO;
import com.hoclaptrinhweb.mapper.NewsMapper;
import com.hoclaptrinhweb.model.NewsModel;

@Repository
public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO{

	@Override
	public List<NewsModel> findAll() {
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		return query(sql.toString(), new NewsMapper());
	}

}

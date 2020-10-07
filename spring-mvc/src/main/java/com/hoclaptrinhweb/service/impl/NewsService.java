package com.hoclaptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoclaptrinhweb.converter.NewsConverter;
import com.hoclaptrinhweb.dto.NewsDTO;
import com.hoclaptrinhweb.entity.CategoryEntity;
import com.hoclaptrinhweb.entity.NewsEntity;
import com.hoclaptrinhweb.repository.CategoryRepository;
import com.hoclaptrinhweb.repository.NewsRepository;
import com.hoclaptrinhweb.service.INewsService;

@Service
public class NewsService implements INewsService {

	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	private NewsConverter newsConverter;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<NewsDTO> findAll(Pageable pageable) {
		List<NewsDTO> models = new ArrayList<>();
		List<NewsEntity> entities = newsRepository.findAll(pageable).getContent();
		for(NewsEntity item : entities) {
			NewsDTO newsDTO = newsConverter.toDTO(item);
			models.add(newsDTO);
		}
		return models;
	}

	@Override
	public Integer getTotalItem() {
		return (int) newsRepository.count();
	}

	@Override
	public NewsDTO findById(long id) {
		NewsEntity entity = newsRepository.findOne(id);
		return newsConverter.toDTO(entity);
	}


	@Override
	@Transactional
	public NewsDTO save(NewsDTO dto) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(dto.getCategoryCode());
		NewsEntity newsEntity = new NewsEntity();
		if(dto.getId() != null) {
			NewsEntity oldNews = newsRepository.findOne(dto.getId());
			oldNews.setCategory(categoryEntity);
			newsEntity = newsConverter.toEntity(oldNews, dto);
		}else {
			newsEntity = newsConverter.toEntity(dto);
			newsEntity.setCategory(categoryEntity);
		}
		return newsConverter.toDTO(newsRepository.save(newsEntity));
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id : ids) {
			newsRepository.delete(id);
		}
	}

	

}

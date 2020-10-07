
package com.hoclaptrinhweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hoclaptrinhweb.dto.CategoryDTO;
import com.hoclaptrinhweb.dto.NewsDTO;

public interface INewsService {
	List<NewsDTO> findAll(Pageable pageable);
	Integer getTotalItem();
	NewsDTO findById(long id);
	NewsDTO save(NewsDTO dto);
	void delete(long[] ids);
}

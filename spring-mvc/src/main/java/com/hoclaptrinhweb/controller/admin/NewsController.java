package com.hoclaptrinhweb.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hoclaptrinhweb.dto.NewsDTO;
import com.hoclaptrinhweb.service.ICategoryService;
import com.hoclaptrinhweb.service.INewsService;
import com.hoclaptrinhweb.util.MessageUtil;

@Controller(value = "newsControllerOfAdmin")
public class NewsController {
	
	@Autowired
	private INewsService newsService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@RequestMapping(value = "quan-tri/news/list", method = RequestMethod.GET)
	   public ModelAndView showList(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpServletRequest request) {
		  NewsDTO model = new NewsDTO();
		  model.setPage(page);
		  model.setLimit(limit);
	      ModelAndView mav = new ModelAndView("admin/news/list");
	      Pageable pageable = new PageRequest(page-1, limit);
	      model.setListResult(newsService.findAll(pageable));
	      model.setTotalItem(newsService.getTotalItem());
	      model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
	      if(request.getParameter("message") != null) {
	    	  Map<String,String> message = messageUtil.getMessage(request.getParameter("message"));
	    	  mav.addObject("message", message.get("message"));
	    	  mav.addObject("alert", message.get("alert"));
	      }
	      mav.addObject("model", model);
	      return mav;
	   }
	
	@RequestMapping(value = "quan-tri/news/edit", method = RequestMethod.GET)
	   public ModelAndView editNews(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
	      ModelAndView mav = new ModelAndView("admin/news/edit");
	      NewsDTO model = new NewsDTO();
	      if(id != null) {
	    	  model = newsService.findById(id);
	      }
	      if(request.getParameter("message") != null) {
	    	  Map<String,String> message = messageUtil.getMessage(request.getParameter("message"));
	    	  mav.addObject("message", message.get("message"));
	    	  mav.addObject("alert", message.get("alert"));
	      }
	      mav.addObject("categories", categoryService.findAll());
	      mav.addObject("model",model );
	      return mav;
	   }

}

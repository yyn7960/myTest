package com.yappam.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.yappam.dao.INewsDao;
import com.yappam.model.News;
import com.yappam.service.INewsService;

public class NewsServiceImpl implements INewsService {
	private INewsDao newsDao ;
	
	public INewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(INewsDao newsDao) {
		this.newsDao = newsDao;
	}

	public void add(News news) throws DataAccessException {
		newsDao.save(news) ;
	}

	public void delete(int id) throws DataAccessException {
		News news = (News)newsDao.findById(News.class, id) ;
		newsDao.delete(news) ;
	}

	public List<News> findAll() throws DataAccessException {
		return newsDao.findByHQL("");
	}

	public List<News> findByFid(int fid) throws DataAccessException {
		return newsDao.findByHQL(" where fid="+fid);
	}

	public News findById(int id) throws DataAccessException {
		return (News)newsDao.findById(News.class, id);
	}

	public void update(News news) throws DataAccessException {
		newsDao.update(news) ;
	}

}

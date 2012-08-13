package com.yappam.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.yappam.model.News;

public interface INewsService {
	/**
	 * 添加
	 * @param news
	 * @throws DataAccessException
	 */
	public void add(News news) throws DataAccessException ;
	/**
	 * 查找
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public News findById(int id) throws DataAccessException ;
	/**
	 * 查找所有
	 * @return
	 * @throws DataAccessException
	 */
	public List<News> findAll() throws DataAccessException ;
	/**
	 * 根据类型查找
	 * @param fid
	 * @return
	 * @throws DataAccessException
	 */
	public List<News> findByFid(int fid) throws DataAccessException ;
	/**
	 * 修改
	 * @param news
	 * @throws DataAccessException
	 */
	public void update(News news) throws DataAccessException ;
	/**
	 * 删除
	 * @param id
	 * @throws DataAccessException
	 */
	public void delete(int id) throws DataAccessException ;
	
}

package com.yappam.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.yappam.model.User;

/**
 * yangyunan Jul 7, 2011-4:55:28 PM
 */
public interface IUserService {
	/**
	 * 根据id查找实体
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public User findById(int id) throws DataAccessException ;
	
	/**
	 * 查找实体是否存在
	 * @param user
	 * @return
	 * @throws DataAccessException
	 */
	public boolean have(int id) throws DataAccessException ;
	
	/**
	 * 添加用户
	 * @param user
	 * @throws DataAccessException
	 */
	public void add(User user) throws DataAccessException ;
	
	/**
	 * 修改用户
	 * @param user
	 * @throws DataAccessException
	 */
	public void update(User user) throws DataAccessException ;
	
}

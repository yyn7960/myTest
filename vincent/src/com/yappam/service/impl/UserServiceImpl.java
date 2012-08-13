package com.yappam.service.impl;

import org.springframework.dao.DataAccessException;

import com.yappam.dao.IUserDao;
import com.yappam.model.User;
import com.yappam.service.IUserService;

/**
 * yangyunan Jul 7, 2011-4:55:55 PM
 */
public class UserServiceImpl implements IUserService {

	private IUserDao userDao ;
	
	public User findById(int id) throws DataAccessException {
		
		return userDao.getById(id) ;
	}

	public boolean have(int id) throws DataAccessException {
		boolean b = false ;
		try {
			if(userDao.getById(id)!=null){
				b = true ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return b ;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void add(User user) throws DataAccessException {
		userDao.save(user) ;
	}

	public void update(User user) throws DataAccessException {
		userDao.update(user) ;
	}

	
}

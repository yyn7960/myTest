package com.yappam.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.yappam.dao.ITypeDao;
import com.yappam.model.Type;
import com.yappam.service.ITypeService;

public class TypeServiceImpl implements ITypeService {
	private ITypeDao typeDao ;
	
	public ITypeDao getTypeDao() {
		return typeDao;
	}

	public void setTypeDao(ITypeDao typeDao) {
		this.typeDao = typeDao;
	}

	public void add(Type type) throws DataAccessException {
		typeDao.save(type) ;
	}

	public void deleteById(int id) throws DataAccessException {
		Type type = (Type)typeDao.findById(Type.class, id) ;
		typeDao.delete(type) ;
	}

	public Type findById(int id) throws DataAccessException {
		return (Type)typeDao.findById(Type.class, id) ;
	}

	public List<Type> getAll() throws DataAccessException {
		return typeDao.findByHQL("") ;
	}

	public void update(Type type) throws DataAccessException {
		typeDao.update(type) ;
	}

	public List<Type> getListByLevel(int level) throws DataAccessException {
		return typeDao.findByHQL(" where level="+level);
	}

	public List<Type> getListByFid(int fid) throws DataAccessException {
		return typeDao.findByHQL(" where fid="+fid);
	}

}

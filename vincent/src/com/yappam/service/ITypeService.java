package com.yappam.service;

import org.springframework.dao.DataAccessException;

import java.util.List;

import com.yappam.model.Type;

public interface ITypeService {
	/**
	 * 添加
	 * @param type
	 * @throws DataAccessException
	 */
	public void add(Type type) throws DataAccessException ;
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public Type findById(int id) throws DataAccessException ;
	/**
	 * 删除
	 * @param id
	 * @throws DataAccessException
	 */
	public void deleteById(int id) throws DataAccessException ;
	/**
	 * 修改
	 * @param type
	 * @throws DataAccessException
	 */
	public void update(Type type) throws DataAccessException ;
	/**
	 * 查找所有
	 * @return
	 * @throws DataAccessException
	 */
	public List<Type> getAll() throws DataAccessException ;
	/**
	 * 根据级别查找
	 * @param level
	 * @return
	 * @throws DataAccessException
	 */
	public List<Type> getListByLevel(int level) throws DataAccessException ;
	/**
	 * 根据模块查找
	 * @param fid
	 * @return
	 * @throws DataAccessException
	 */
	public List<Type> getListByFid(int fid) throws DataAccessException ;
	
}

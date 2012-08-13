package com.yappam.dao;
import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

@SuppressWarnings("unchecked")
public interface IBaseDao<T,PK extends Serializable> {

	
	/**
	 * 保存指定实体类
	 * @param entity  实体类
	 * @return 返回产生的标识
	 * */
	public void save(T entity)throws DataAccessException;
	
	/**
	 * 更新或保存指定实体    
	 * @param entity 实体类
	 * */
	public void saveOrUpdate(T entity) throws DataAccessException;
	
	
	/**
	 * 批量保存数据
	 * @param entities 要批量保存的数据集合
	 * 
	 * */
	public void saveAll(List entities) throws DataAccessException;
	/**
	 * 删除指定实体
	 * @param entity 指定实体
	 * */
	public void delete(T entity) throws DataAccessException;
	
	/**
	 * 通过条件删除,大批量删除的时候此方法不推荐，效率低
	 * @param entityClass
	 * @param hql 只需写where语句，如where id=?
	 * @param params 参数
	 * @return 返回删除的条数
	 */
	public int deleteAll(Class entityClass,String hql, Object[] params) throws DataAccessException;
	
	/**
	 * 通过原生sql 进行删除
	 * @param sql
	 */
	public int deleteAllBySQL(String sql) throws RuntimeException;
	
	/**
	 * 通过条件删除
	 * @param entityClass
	 *            要删除的实体类,不能为空
	 * @param id
	 * 			  根据id来删除,不能为空
	 *            
	 */
	public void deleteById(Class entityClass, PK id) throws DataAccessException;
	/**
	 * 通过条件删除
	 * @param id
	 * 			  根据id来删除,不能为空
	 *            
	 */
	public void deleteById(PK id) throws DataAccessException;
	/**
	 * 更新实体
	 * @param entity 实体名 
	 * */
	public void update(T entity)throws DataAccessException;
	
	/**
	 * 更新实体
	 * @param hql hql语句 
	 * @return 返回int（更新的数据数量）
	 * */
	public int update(String hql)throws DataAccessException;
	
	/**
	 * 执行批量更新，删除的HQL
	 * @param hql
	 * @return 返回int（更新的数据数量）
	 */
	public int updateHql( String hql, Object[] params)throws DataAccessException;
	/**
	 * 采用原生sql进行直接批量更新
	 * @param strHQL 原生sql语句
	 * @return 返回int（更新的数据数量）
	 */
	public int updateAllBySql(String strHQL)throws RuntimeException;
	/**
	 *按HQL条件查询列表
	 *@param hql 查询语句,支持连接查询和多条件查询,只需要写where 语句
	 *@return 结果集List
	 * */
	public List<T> findByHQL( String hqlWhere) throws DataAccessException;
	/**
	 *按HQL条件查询列表
	 *@param hql 查询语句,支持连接查询和多条件查询,只需要写where 语句
	 *@param entityClass 
	 *@return 结果集List
	 * */
	public List findByHQL(Class entityClass, String hqlWhere) throws DataAccessException;
	
	/**
	 *按HQL条件查询列表
	 *@param hql 查询语句,支持连接查询和多条件查询
	 *@param params 参数数组,代替hql中的"?"号 
	 *@return 结果集List
	 * */
	public List<T> findByHQL( String hql, Object[] params)throws DataAccessException ;
	
	/**
	 *按HQL条件查询
	 *@param hql 查询语句,支持连接查询和多条件查询
	 *@param param 参数数组,代替hql中的"?"号 
	 *@return 结果集List
	 * */
	public List<T> findByHQL(String hql,Object param)throws DataAccessException ;
	
	/**
	 * 根据id查找实体类
	 * @param ID 实体PK
	 * @return 返回实体如果没有则返回null
	 * */
	public T loadById(PK ID) throws DataAccessException;
	
	/**
	 * 根据id查找实体类
	 * @param ID 实体PK
	 * @return 返回实体如果没有抛出异常
	 * */
	public Object findById(Class entityClass,Serializable ID) throws DataAccessException;
	
	/**
	 * 根据id查找实体类
	 * @param ID 实体PK
	 * @return 返回实体如果没有则返回null
	 * */
	public T getById(PK ID) throws DataAccessException;

	/**
	 * 通过实例查找
	 * @param entity 实例
	 * @return 集合
	 * */
	public List findByExample(Object entity)throws DataAccessException;
	
	/**
	 * 查询记录总数
	 * @param pojoClass 实体类
	 * @param strWhere 不包含from前的HQL语句，其中的from xxx.xxx的POJO类型从spring的多语言实现配置XML注入指定，可以包含as、join等后续关键字
	 * @param params 参数
	 * */
	public abstract Integer getCount(Class pojoClass, String hql, Object[] params)throws DataAccessException;
	
	/**
	 * 分页查询
	 * @param pojoClass 查询的实体类
	 * @param hqlWhere hql语句，只需要写where 语句即可，比如where id=?
	 * @param params hql语句中的参数，如没有可以new Object[]{}
	 * @param iPage 当前页数，如果不需要分页与翻页
	 * @param iPageSize 每页显示总数，每页返回记录条数，如果不需要分页与翻页，可以为输入0
	 * @return 返回查询list结果
	 * */
	public List findPage(Class pojoClass, String hqlWhere, Object[] params, int iPage, int iPageSize)throws DataAccessException;
	
	/**
	 * 自定义HQL分页查询
	 * 可以使用带？参数与in函数的别名参数，如 
	 * strHQL = "select id from Test where unitid = ? and id in(:idList)"
	 * params = new Object[]{ new Long(1), new ArrayList().add(new Long(1)).add(new Long(2)) };
	 * 但必须严格对应参数前后依次循序！！
	 * @param iPage 当前页数，如果不需要分页与翻页
	 * @param iPageSize 每页显示总数，每页返回记录条数，如果不需要分页与翻页，可以为输入0
	 * @return
	 */
	public List<T> findPage( String strHQL, Object[] params, int iPage, int iPageSize)throws DataAccessException;
	
	public List findBySQL(String sql)throws RuntimeException;
}

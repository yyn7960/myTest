package com.yappam.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yappam.dao.IBaseDao;
import com.yappam.util.GenericsReflexUtils;
import com.yappam.util.HQLTool;
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T, PK extends Serializable> extends
		HibernateDaoSupport implements IBaseDao<T,PK> {

	protected final Log log = LogFactory.getLog(this.getClass());
	private int batchSize = 20;//
	protected Class<T> entityClass;
	
	public BaseDaoImpl() {
		entityClass = GenericsReflexUtils.getSuperClassGenricType(this.getClass());
	}

	public Integer getCount(Class pojoClass, String hql, Object[] params) throws DataAccessException{
		hql = (hql == null) ? "" : hql;
		StringBuilder builderTemp = new StringBuilder("select count(*) from ")
				.append(pojoClass.getName()).append(hql);
		while (builderTemp.indexOf(" fecth") > 0) {
			builderTemp.replace(builderTemp.indexOf(" fecth"), builderTemp
					.indexOf(" fecth") + 6, "");
		}
		
		 String strHQL = builderTemp.toString();
		 log.debug(strHQL);
		 Object[] fObj=params;
				Query query = this.getSession().createQuery(strHQL);
				if (HQLTool.notNull(fObj)) {
					HQLTool.setParams(query, fObj);
				}
				Object o= query.uniqueResult();
		
		Integer retInteger=0;
		if(o != null){
			retInteger = Integer.valueOf(o.toString());
		}else{
			retInteger = Integer.valueOf(0);
		}
		return retInteger;
	}

	public void delete(Object entity) throws  DataAccessException{
		this.getHibernateTemplate().delete(entity,null);
	}


	public int deleteAll(Class entityClass,String hql, Object[] params) throws DataAccessException{
		if (!HQLTool.notNull(hql)) {
			hql = "1=1";
		}
		 Object[] fObj=params;
		 String strHQL="delete " + entityClass.getName() + "  " + hql;
				Query query = this.getSession().createQuery(strHQL);
				if (HQLTool.notNull(fObj)) {
					HQLTool.setParams(query, fObj);
				}
				log.debug(query.getQueryString());
				int o=query.executeUpdate();
		//Object o=query.uniqueResult();
		log.info("共删除"+o+"条数据库");
		return o;
	}

	public List findByExample(Object entity)throws DataAccessException {
		return this.getHibernateTemplate().findByExample(entity);
	}

	public List<T> findByHQL(String hqlWhere) throws DataAccessException{
		return (List<T>)this.findByHQL(entityClass, hqlWhere);
	}

	public List findByHQL(Class entityClass, String hqlWhere) throws DataAccessException{
		if(entityClass==null)
			return null;
		StringBuilder builder=new StringBuilder(" from "+entityClass.getName());
		builder.append(" "+hqlWhere);
		hqlWhere=builder.toString().trim();
		Query query=this.getSession().createQuery(hqlWhere);
		return query.list();
	}
	public List<T> findByHQL( String hql, Object[] params)throws DataAccessException {
				StringBuilder builder=new StringBuilder(" from "+entityClass.getName());
				builder.append(hql);
				Query query = this.getSession().createQuery(builder.toString().trim());
				if (HQLTool.notNull(params)) {
					HQLTool.setParams(query, params);
				}
				return  (List<T>)query.list();
	}

	public List<T> findByHQL(String hql, Object param) throws DataAccessException{
		return (List<T>)this.findByHQL(hql, new Object[] { param });
	}

	public Object findById(Class entityClass, Serializable ID)throws DataAccessException {
		if (entityClass == null) {
			return this.getById(ID);
		} else {
			return this.getHibernateTemplate().get(entityClass, ID);
		}
	}

	public List findPage(Class pojoClass, String hqlWhere, Object[] params,
			 int iPage, int iPageSize)throws DataAccessException {
		hqlWhere=(hqlWhere==null)?"":hqlWhere;
		StringBuilder strBuilder=new StringBuilder(" from ").append(pojoClass.getName())
			.append(" ").append(HQLTool.getStringValue(hqlWhere));
			 String hql=strBuilder.toString().trim();
				Query query=this.getSession().createQuery(hql);
				if(HQLTool.notNull(params)){
					HQLTool.setParams(query, params);
				}
				if(iPage>0)
					query.setFirstResult((iPage-1)*iPageSize);
				else 
					query.setFirstResult(0);
				if(iPageSize>0)
					query.setMaxResults(iPageSize);
				log.debug(query.getQueryString());
				return query.list();
	}

	public List<T> findPage( String strHQL,  Object[] params, int iPage,
			 int iPageSize) throws DataAccessException{
				return this.findPage(entityClass, strHQL, params, iPage, iPageSize);
	}

	public Object getById(Serializable ID)throws DataAccessException {
		return this.getHibernateTemplate().get(entityClass, ID);
	}

	public Object loadById(Serializable ID)throws DataAccessException {
		return this.getHibernateTemplate().load(entityClass, ID);
	}

	public void save(Object entity) throws DataAccessException{
			this.getHibernateTemplate().save(entity);
	}

	public void saveAll(List entities)throws DataAccessException {

		if (entities == null || entities.size() == 0) {
			return;
		}
		Session session = this.getSession();
		int i=0;
		for (; i < entities.size(); i++) {
			//T t = (T) entities.get(i);
			session.save(entities.get(i));
			if (i % this.getBatchSize() == 0) {
				session.flush();
				session.clear();
			}
		}
		session.flush();
		session.clear();
		log.info("共删除"+i+"条数据库");
	}

	public void saveOrUpdate(Object entity) throws DataAccessException{
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	public void update(Object entity) throws DataAccessException{
		this.getHibernateTemplate().update(entity);
	}


	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public int updateAllBySql(String strHQL) throws DataAccessException{
		return this.deleteAllBySQL(strHQL);
	}
	public void deleteById(Class entityClass, Serializable id)throws DataAccessException {
		if(HQLTool.notNull(id)){
			Object obj=this.getHibernateTemplate().get(entityClass, id);
			this.getHibernateTemplate().delete(obj);
		}
	}

	public int deleteAllBySQL(String sql) throws RuntimeException{
		SQLQuery sqlQuery=this.getSession().createSQLQuery(sql);
		int count=sqlQuery.executeUpdate();
		log.debug(sql+" 执行deleteAllBySQL影响的数据条数："+count);
		return count;
	}
	public int update(String hql)throws DataAccessException{
		Query query=this.getSession().createQuery(hql);
		int count=query.executeUpdate();
		log.debug(hql+" 执行update(String hql)影响的数据条数："+count);
		return count;
	}
	public int updateHql( String hql, Object[] params)throws DataAccessException{
		Query query = this.getSession().createQuery(hql);
		HQLTool.setParams(query, params);
		int count=query.executeUpdate();
		log.debug(hql+" 执行updateHql影响的数据条数："+count);
		return count;
	}
	public void deleteById(PK id) throws DataAccessException{
		this.deleteById(entityClass, id);
	}
	public List findBySQL(String sql)throws RuntimeException{
		SQLQuery sqlQuery=this.getSession().createSQLQuery(sql);
		return sqlQuery.list();
	}
}

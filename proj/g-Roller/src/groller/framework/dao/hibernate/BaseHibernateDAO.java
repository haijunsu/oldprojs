/*
 * Created on 2003-10-15
 */
package groller.framework.dao.hibernate;

import groller.framework.common.StringUtil;
import groller.framework.dao.AbstractDAO;
import groller.framework.dao.CannotFoundException;
import groller.framework.exception.DAOException;
import groller.framework.session.SessionLocator;

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.ObjectNotFoundException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class BaseHibernateDAO extends AbstractDAO {
	public Object saveEntity(Object entity) throws DAOException {
		try {
			currentSession().save(entity);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException("Error in BaseHibernateDAO.saveEntity()", e);
		}
		return entity;
	}

	public Object loadEntityById(Long id) throws DAOException {
		try {
			return currentSession().load(_entityType, id);
		} catch (ObjectNotFoundException e) {
			throw new CannotFoundException("不存在此条记录, type=" + _entityType.getName() + ", ID=" + id); 
		} catch (HibernateException e) {
			throw new DAOException("Error in BaseHibernateDAO.loadEntityById()", e);
		}
	}
	
	/**
	 * 该方法可以同时持久化entity的子
	 * @param entity
	 * @return
	 * @throws DAOException
	 */
	public Object storeEntity(Object entity)throws DAOException{
		Session ses = currentSession();
		try{
			ses.saveOrUpdate(entity);
			ses.flush(); 			
		}catch(HibernateException e){
			throw new DAOException("Error in BaseHibernateDAO.storeEntity()",e);
		}
		return entity;
	}
	
	public Object updateEntity(Object entity) throws DAOException {
		try {
			currentSession().update(entity);
		} catch (HibernateException e) {
			throw new DAOException("Error in BaseHibernateDAO.updateEntity()", e);
		}
		return entity;
	}
	
	public void removeEntity(Class clazz,Long id)throws DAOException{
		try{
			Session session = currentSession();			
			Object obj = session.load(clazz,id);
			session.delete(obj);
		}catch(HibernateException e){
			e.printStackTrace();
			throw new DAOException("Error in BaseHibernateDAO.removeEntity()",e);
		}
	}
	
	public void removeEntity(Long id)throws DAOException{
		try{
			Session session = currentSession();			
			Object obj = session.load(this._entityType,id);
			session.delete(obj);
		}catch(HibernateException e){
			e.printStackTrace();
			throw new DAOException("Error in BaseHibernateDAO.removeEntity()",e);
		}
	}
	
	public void removeEntity(Object entity) throws DAOException {
		try {
			currentSession().delete(entity);
		} catch (HibernateException e) {
			throw new DAOException("Error in BaseHibernateDAO.removeEntity()", e);
		}
	}
	
	public void removeAllEntities(Class clazz) throws DAOException {
		try {
			currentSession().delete("FROM " + clazz.getName());
		} catch (HibernateException e) {
			throw new DAOException("Error in BaseHibernateDAO.removeAllEntities()", e);
		}
	}
	
    /**
     * (UIO)提供符合条件的查询集。
     * @param query 查询条件，
     *      条件中的且用'&'(转换为" AND entity.")或用'|'((转换为" OR entity."))，
     *      因此条件'&'与'|'后必需紧跟实例中属性名而不能有空格。
     */    
	public List find(String query, Class entityType) {
        StringBuffer hql = new StringBuffer("FROM entity in class " + entityType.getName());
        //StringBuffer hql = new StringBuffer("FROM " + entityType.getName() + " as entity");       
        String conditions = "";
        if(query != null){            
            if((query.indexOf('=')>0)){
                if((query.indexOf('&')!=1))           
                    conditions = " &" + query;
            }
            else
                conditions = " " + query;
            if((conditions.indexOf('&')>0) || (conditions.indexOf('|')>0)){ 
                conditions = " WHERE (entity.id is not null) " + conditions;                
                conditions = replaceTags(conditions,"&"," AND entity.");
                conditions = replaceTags(conditions,"|"," OR entity.");                
            }
        }
        else
            conditions = " order by entity.id";
        hql.append(conditions);        
        

		List entities = null;
		try {
			Query querys = currentSession().createQuery(hql.toString());
            entities = querys.list();
		} catch (HibernateException e) {
			throw new DAOException("Error in query :\n" + hql, e);
		}
		return entities;
	}
    /*
    public List find(String query, Class entityType) {
		String hql = populateHQL(query, entityType);
        
        List entities;
        try {
            entities = currentSession().find(hql);
        } catch (HibernateException e) {
            throw new DAOException("Error in query :\n" + hql, e);
        }
        return entities;
    }
    */
        

	/* (non-Javadoc)
	 * @see groller.framework.dao.IDAO#remove(java.lang.String, java.lang.Class)
	 */
	public void remove(String condition, Class entityType) {
		String hql = populateHQL(condition, entityType);
		try {
			currentSession().delete(hql);
		} catch (HibernateException e) {
			throw new DAOException("Error in delet :\n" + hql, e);
		}
	}

	protected Session currentSession() {
		return SessionLocator.getSession();
	}
    
	private String populateHQL(String condition, Class entityType) {
//		String[] exps = condition.split("&");
		String[] exps = StringUtil.split(condition, "&");
		String hql = "FROM " + entityType.getName() + " entity WHERE ";
		for(int i = 0; i < exps.length; i++) {
			hql += ("(entity." + exps[i] + ") AND ");
		}
		hql += "(entity.id is not null)";
		return hql;
	}
    
    private String replaceTags(String source,String tag,String replacement){
        //change the method to fit jdk1.3
        return StringUtil.replace(source, tag, replacement);
//        return source.replaceAll(tag, replacement);
    }

}

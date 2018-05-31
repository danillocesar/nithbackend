package br.com.nith.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
 
@Transactional
public abstract class AbstractDao<PK extends Serializable, T> {
     
	@PersistenceContext
	private EntityManager entityManager;
    private final Class<T> persistentClass;
     
    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
    protected Criteria getCriteria(){
        return entityManager.unwrap(Session.class).createCriteria(persistentClass);
    }
 
    @SuppressWarnings("unchecked")
    public T getById(PK key) {
        return (T) getCriteria().add(Restrictions.eq("id", key)).uniqueResult();
    }
    @SuppressWarnings("unchecked")
    public List<T> listAll(){
    	return getCriteria().list();
    }
    
    public T insert(T entity) {
    	entityManager.persist(entity);
    	return entity;
    }
    public void update(T entity) {
    	entityManager.merge(entity);
    }
    public void delete(T entity) {
    	entityManager.remove(entity);
    }
    public Session getSession(){
    	return entityManager.unwrap(Session.class);
    }
    public SessionFactory getSessionFactory(){
    	return entityManager.unwrap(Session.class).getSessionFactory();
    }
}
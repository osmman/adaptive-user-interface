package cz.cvut.fel.aui.service;

import cz.cvut.fel.aui.model.Context;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 15.11.13
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDao<T> implements DataAccessObject<T>, Serializable {

    private Class<T> entityClass;

    protected AbstractDao(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    public AbstractDao(){
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity){
        EntityManager em = getEntityManager();
        em.persist(entity);
    }

    public void edit(T entity){
        EntityManager em = getEntityManager();
        em.merge(entity);
    }

    public void remove(T entity){
        EntityManager em = getEntityManager();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    public T find(Object id){
        EntityManager em = getEntityManager();
        return em.find(entityClass, id);
    }

    public List<T> findAll(){
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }

}

package com.iscbrazil.printerlog.DAO.hibernate;

import com.iscbrazil.printerlog.DAO.GenericDAO;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;

/**
 * @version 2011.APR.12.01
 * @author edilson.ales
 */
public class HibernateGenericDAO<T> implements GenericDAO<T> {

    private Class<T> persistentClass;
    private Session session;

    public HibernateGenericDAO(Session session) {
        this.session = session;
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public Session getSession() {
        return this.session;
    }

    @Override
    public T getById(Serializable id) {
        return (T) this.session.get(this.persistentClass, id);
    }

    @Override
    public List<T> getAll() {
        return this.session.createCriteria(this.persistentClass).list();
    }

    @Override
    public T save(T entity) {
        return (T) this.session.merge(entity);
    }

    @Override
    public void delete(T entity) {
        this.session.delete(entity);
    }
}

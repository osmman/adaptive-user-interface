package cz.cvut.fel.aui.service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Person: Tomáš
 * Date: 15.11.13
 * Time: 20:30
 * To change this template use File | Settings | File Templates.
 */
public interface DataAccessObject<T> {
    void create(T entity);
    void edit(T entity);
    void remove(T entity);
    T find(Object id);
    List<T> findAll();
}

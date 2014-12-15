package uip.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import cz.cvut.fel.aui.model.*;

@Stateless
public class EntityService {

    @Inject
    private EntityManager entityManager;

    public Object find(String clazz, Long id) {
        try {
            Class c = Context.class.getClassLoader().loadClass(clazz);
            return entityManager.find(c, id);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

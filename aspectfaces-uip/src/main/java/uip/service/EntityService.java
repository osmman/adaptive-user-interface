package uip.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class EntityService {

    @Inject
    private EntityManager entityManager;

    public Object find(String clazz, Long id) {
        try {
            Class c = ClassLoader.getSystemClassLoader().loadClass(clazz);
            return entityManager.find(c, id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

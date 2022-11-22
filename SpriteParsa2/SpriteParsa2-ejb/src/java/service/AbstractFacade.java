
package service;

import cst8218.dera0014.entity.*;
import java.util.List;
import javax.persistence.EntityManager;


/**
 * This is the class that extends the SpriteFacadeREST class which contains most of its implementations
 * @author tgk
 * @param <T> could be any type 
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;
    /**
     * This is the default constructor
     * @param entityClass of type class 
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    /**
     * Gets the entity manager which is associated with a persistence context
     * @return returns the entity manager
     */
    protected abstract EntityManager getEntityManager();
    /**
     * This method is responsible for creating(persisting might be more exact) the entity 
     * @param entity that is about to be persisted 
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }
    /**
     * This method is responsible for removing an entity 
     * @param entity that is about to be edited
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }
    /**
     * This method is responsible for removing an entity 
     * @param entity that is about to be removed
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    /**
     * Finds a specific entity by matching id...
     * 
     * @param id the id of the entity that you want to find(look for)
     * @return the entity with the specified ID (if it is matching)
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    /**
     * Queries the entity manager and finds all the entities 
     * 
     * @return all the entities
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    /**
     * Queries the entity manager and returns everything that is within range
     * 
     * @param range is basically an array that includes the indexes of the entities
     * @return any entity that is within a specified range
     */
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    /**
     * This class queries the entity manager and determines the count of entities that are in our DB
     * 
     * @return the count(number of entities available)
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}

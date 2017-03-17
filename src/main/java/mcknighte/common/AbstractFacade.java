package mcknighte.common;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Abstract facade
 * 
 * @author Edward McKnight (UP608985)
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;
    protected abstract EntityManager getEntityManager();

    /**
     * Constructor
     * 
     * @param entityClass Class
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Create
     * 
     * @param entity T
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    /**
     * Edit
     * 
     * @param entity T
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * Remove
     * 
     * @param entity T
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Find by ID
     * 
     * @param id Object
     * @return T
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Find all
     * 
     * @return List
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Find range
     * 
     * @param range int[]
     * @return List
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
     * Count
     * 
     * @return int
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}

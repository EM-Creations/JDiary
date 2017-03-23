package mcknighte.common;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Abstract facade class to act as the superclass to the Client and Appointment facades
 *
 * @author Edward McKnight (UP608985)
 * @see AbstractController
 * @see AbstractConverter
 * @see ClientFacade
 * @see AppointmentFacade
 * @since 2017
 * @version 1.0
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    protected abstract EntityManager getEntityManager();

    /**
     * Constructor
     *
     * @param entityClass the corresponding entity class of this facade
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Create
     *
     * @param entity the object to persist
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    /**
     * Edit
     *
     * @param entity the object to edit
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * Remove
     *
     * @param entity the object to remove
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Find by ID
     *
     * @param id the object to find
     * @return the found object; otherwise null
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Find all objects
     *
     * @return a list of all objects held by the system
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Find range
     *
     * @param range integer array of the range to find
     * @return a list of all objects within the specified range
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
     * Count of the number of objects held by the system
     *
     * @return the number of objects held by the system
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}

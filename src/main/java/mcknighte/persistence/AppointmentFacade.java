package mcknighte.persistence;

import mcknighte.entity.Appointment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Appointment facade
 * 
 * @author Edward McKnight (UP608985)
 */
@Stateless
public class AppointmentFacade extends AbstractFacade<Appointment> {
    @PersistenceContext(unitName = "mcknighte_war_1.0PU")
    private EntityManager em;

    /**
     * Get entity manager
     * 
     * @return EntityManager
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Constructor
     */
    public AppointmentFacade() {
        super(Appointment.class);
    }
    
}

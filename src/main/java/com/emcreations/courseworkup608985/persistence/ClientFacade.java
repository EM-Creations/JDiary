package com.emcreations.courseworkup608985.persistence;

import com.emcreations.courseworkup608985.entity.Client;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Edward McKnight (UP608985)
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceContext(unitName = "com.emcreations_CourseworkUP608985_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Client> search(String userName) {
        List<Client> clients;
        
        clients = this.getEntityManager().createQuery(
                "SELECT c FROM Client c WHERE LOWER(c.username) LIKE :userName")
                .setParameter("userName", "%" + userName.toLowerCase() + "%")
                .getResultList();
        
        if (clients.isEmpty()) // If the list is empty
            return null; // Return null - no clients found
        return clients; // Otherwise return the list of clients
    }
    
    /**
     * Find a client by username
     * 
     * @param userName String
     * @return Client
     */
    public Client find(String userName) {
        List<Client> clients = this.getEntityManager().createQuery(
                "SELECT c FROM Client c WHERE c.username = :userName")
                .setParameter("userName", userName)
                .setMaxResults(1)
                .getResultList();
        
        if (clients.isEmpty()) // If the list is empty
            return null; // Return null - no client found
        return clients.get(0); // Otherwise return the found client
    }
    
    /**
     * Find a client by username and password
     * 
     * @param userName String
     * @param password String
     * @return Client
     */
    public Client find(String userName, String password) {
        List<Client> clients = this.getEntityManager().createQuery(
                "SELECT c FROM Client c WHERE c.username = :userName AND c.password = :password")
                .setParameter("userName", userName)
                .setParameter("password", password)
                .setMaxResults(1)
                .getResultList();
        
        if (clients.isEmpty()) // If the list is empty
            return null; // Return null - no client found
        return clients.get(0); // Otherwise return the found client
    }

    public ClientFacade() {
        super(Client.class);
    }
    
}

package com.emcreations.courseworkup608985.persistence;

import com.emcreations.courseworkup608985.entity.Client;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Client facade
 * 
 * @author Edward McKnight (UP608985)
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> {
    @PersistenceContext(unitName = "com.emcreations_CourseworkUP608985_war_1.0PU")
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
     * Search for clients based on search type and search text
     * 
     * @param searchType String
     * @param searchText String
     * @return List
     */
    public List<Client> search(String searchType, String searchText) {
        List<Client> clients;
        
        switch (searchType) {
            case "username":
                clients = this.getEntityManager().createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.username) LIKE :userName")
                        .setParameter("userName", "%" + searchText.toLowerCase() + "%")
                        .getResultList();
                break;
                
            case "firstName":
                clients = this.getEntityManager().createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.firstName) LIKE :firstName")
                        .setParameter("firstName", "%" + searchText.toLowerCase() + "%")
                        .getResultList();
                break;
                
            case "lastName":
                clients = this.getEntityManager().createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.lastName) LIKE :lastName")
                        .setParameter("lastName", "%" + searchText.toLowerCase() + "%")
                        .getResultList();
                break;
                
            case "address":
                clients = this.getEntityManager().createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.address) LIKE :address")
                        .setParameter("address", "%" + searchText.toLowerCase() + "%")
                        .getResultList();
                break;
                
            case "postcode":
                clients = this.getEntityManager().createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.postcode) LIKE :postcode")
                        .setParameter("postcode", "%" + searchText.toLowerCase() + "%")
                        .getResultList();
                break;
                
            case "phone":
                clients = this.getEntityManager().createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.phone) LIKE :phone")
                        .setParameter("phone", "%" + searchText.toLowerCase() + "%")
                        .getResultList();
                break;
                
            case "email":
                clients = this.getEntityManager().createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.email) LIKE :email")
                        .setParameter("email", "%" + searchText.toLowerCase() + "%")
                        .getResultList();
                break;
            
            default: // If no valid search type is provided
                return null; // Return null - TODO create exception
        }
        
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

    /**
     * Constructor
     */
    public ClientFacade() {
        super(Client.class);
    }
    
}

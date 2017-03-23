package mcknighte.persistence;

import mcknighte.common.AbstractFacade;
import mcknighte.business.ClientService.SearchType;
import mcknighte.entity.Client;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mcknighte.common.Security;

/**
 * Client facade, to manage the persistence of Client objects
 *
 * @author Edward McKnight (UP608985)
 * @see Client
 * @see AbstractFacade
 * @since 2017
 * @version 1.0
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> {
    @PersistenceContext(unitName = "mcknighte_war_1.0PU")
    private EntityManager em;

    /**
     * Get the entity manager for the facade
     *
     * @return the entity manager for the facade
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Search for clients based on the specified search type and search text
     *
     * @param searchType the type of search being performed
     * @param searchText the text to search for
     * @return a list of clients which meet the search criteria; if none found null
     */
    public List<Client> search(SearchType searchType, String searchText) {
        List<Client> clients;

        switch (searchType) {
            default:
            case all: // Search by all parameters
                clients = this.getEntityManager().createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.username) LIKE :userName OR LOWER(c.firstName) LIKE :firstName OR LOWER(c.lastName) LIKE :lastName OR LOWER(c.address) LIKE :address OR LOWER(c.postcode) LIKE :postcode OR LOWER(c.phone) LIKE :phone OR LOWER(c.email) LIKE :email")
                        .setParameter("userName", "%" + searchText.toLowerCase() + "%")
                        .setParameter("firstName", "%" + searchText.toLowerCase() + "%")
                        .setParameter("lastName", "%" + searchText.toLowerCase() + "%")
                        .setParameter("address", "%" + searchText.toLowerCase() + "%")
                        .setParameter("postcode", "%" + searchText.toLowerCase() + "%")
                        .setParameter("phone", "%" + searchText.toLowerCase() + "%")
                        .setParameter("email", "%" + searchText.toLowerCase() + "%")
                        .getResultList();
                break;

            case username: // Search by username
                clients = this.getEntityManager().createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.username) LIKE :userName")
                        .setParameter("userName", "%" + searchText.toLowerCase() + "%")
                        .getResultList();
                break;

            case firstName: // Search by first name
                clients = this.getEntityManager().createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.firstName) LIKE :firstName")
                        .setParameter("firstName", "%" + searchText.toLowerCase() + "%")
                        .getResultList();
                break;

            case lastName: // Search by last name
                clients = this.getEntityManager().createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.lastName) LIKE :lastName")
                        .setParameter("lastName", "%" + searchText.toLowerCase() + "%")
                        .getResultList();
                break;

            case address: // Search by address
                clients = this.getEntityManager().createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.address) LIKE :address")
                        .setParameter("address", "%" + searchText.toLowerCase() + "%")
                        .getResultList();
                break;

            case postcode: // Search by postcode
                clients = this.getEntityManager().createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.postcode) LIKE :postcode")
                        .setParameter("postcode", "%" + searchText.toLowerCase() + "%")
                        .getResultList();
                break;

            case phone: // Search by phone number
                clients = this.getEntityManager().createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.phone) LIKE :phone")
                        .setParameter("phone", "%" + searchText.toLowerCase() + "%")
                        .getResultList();
                break;

            case email: // Search by email
                clients = this.getEntityManager().createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.email) LIKE :email")
                        .setParameter("email", "%" + searchText.toLowerCase() + "%")
                        .getResultList();
                break;
        }

        if (clients.isEmpty()) // If the list is empty
            return null; // Return null - no clients found
        return clients; // Otherwise return the list of clients
    }

    /**
     * Find a client by username
     *
     * @param userName the username to search by
     * @return the client found; if not found null
     */
    public Client find(String userName) {
        List<Client> clients = this.getEntityManager().createQuery(
                "SELECT c FROM Client c WHERE LOWER(c.username) = LOWER(:userName)")
                .setParameter("userName", userName)
                .setMaxResults(1)
                .getResultList();

        if (clients.isEmpty()) // If the list is empty
            return null; // Return null - no client found
        return clients.get(0); // Otherwise return the found client
    }

    /**
     * Find a client by username and password, 
     * used to check that a user can login
     *
     * @param userName the username to search by
     * @param password the password to search by
     * @param salt the salt used on the client's password
     * @return the client found; if not found null
     */
    public Client find(String userName, String password, String salt) {
        List<Client> clients = this.getEntityManager().createQuery(
                "SELECT c FROM Client c WHERE c.username = :userName AND c.password = :password")
                .setParameter("userName", userName)
                .setParameter("password", Security.sha512(password, salt))
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

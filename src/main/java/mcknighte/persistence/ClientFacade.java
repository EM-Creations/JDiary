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
 * Client facade
 *
 * @author Edward McKnight (UP608985)
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> {

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
     * Search for clients based on search type and search text
     *
     * @param searchType SearchType
     * @param searchText String
     * @return List
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
     * @param userName String
     * @return Client
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
     * Find a client by username and password
     *
     * @param userName String
     * @param password String
     * @param salt String
     * @return Client
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

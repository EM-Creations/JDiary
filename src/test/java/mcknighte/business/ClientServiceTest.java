package mcknighte.business;

import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import mcknighte.entity.Client;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Edward McKnight (UP608985)
 */
public class ClientServiceTest {
    
    public ClientServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCountries method, of class ClientService.
     */
    @Ignore
    @Test
    public void testGetCountries() throws Exception {
        System.out.println("getCountries");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientService instance = (ClientService)container.getContext().lookup("java:global/classes/ClientService");
        List<String> expResult = null;
        List<String> result = instance.getCountries();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchClient method, of class ClientService.
     */
    @Ignore
    @Test
    public void testSearchClient() throws Exception {
        System.out.println("searchClient");
        ClientService.SearchType searchType = null;
        String searchText = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientService instance = (ClientService)container.getContext().lookup("java:global/classes/ClientService");
        List<Client> expResult = null;
        List<Client> result = instance.searchClient(searchType, searchText);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editClient method, of class ClientService.
     */
    @Ignore
    @Test
    public void testEditClient_Client() throws Exception {
        System.out.println("editClient");
        Client client = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientService instance = (ClientService)container.getContext().lookup("java:global/classes/ClientService");
        Client expResult = null;
        Client result = instance.editClient(client);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editClient method, of class ClientService.
     */
    @Ignore
    @Test
    public void testEditClient_Client_String() throws Exception {
        System.out.println("editClient");
        Client client = null;
        String providedPassword = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientService instance = (ClientService)container.getContext().lookup("java:global/classes/ClientService");
        Client expResult = null;
        Client result = instance.editClient(client, providedPassword);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClient method, of class ClientService.
     */
    @Ignore
    @Test
    public void testGetClient_Client() throws Exception {
        System.out.println("getClient");
        Client client = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientService instance = (ClientService)container.getContext().lookup("java:global/classes/ClientService");
        Client expResult = null;
        Client result = instance.getClient(client);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClient method, of class ClientService.
     */
    @Ignore
    @Test
    public void testGetClient_String() throws Exception {
        System.out.println("getClient");
        String userName = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientService instance = (ClientService)container.getContext().lookup("java:global/classes/ClientService");
        Client expResult = null;
        Client result = instance.getClient(userName);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createClient method, of class ClientService.
     */
    @Ignore
    @Test
    public void testCreateClient() throws Exception {
        System.out.println("createClient");
        Client client = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientService instance = (ClientService)container.getContext().lookup("java:global/classes/ClientService");
        Client expResult = null;
        Client result = instance.createClient(client);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeClient method, of class ClientService.
     */
    @Ignore
    @Test
    public void testRemoveClient() throws Exception {
        System.out.println("removeClient");
        Client client = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientService instance = (ClientService)container.getContext().lookup("java:global/classes/ClientService");
        Client expResult = null;
        Client result = instance.removeClient(client);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clientExists method, of class ClientService.
     */
    @Ignore
    @Test
    public void testClientExists() throws Exception {
        System.out.println("clientExists");
        String userName = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientService instance = (ClientService)container.getContext().lookup("java:global/classes/ClientService");
        boolean expResult = false;
        boolean result = instance.clientExists(userName);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkLogin method, of class ClientService.
     */
    @Ignore
    @Test
    public void testCheckLogin() throws Exception {
        System.out.println("checkLogin");
        String userName = "";
        String password = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientService instance = (ClientService)container.getContext().lookup("java:global/classes/ClientService");
        Client expResult = null;
        Client result = instance.checkLogin(userName, password);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class ClientService.
     */
    @Ignore
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientService instance = (ClientService)container.getContext().lookup("java:global/classes/ClientService");
        List<Client> expResult = null;
        List<Client> result = instance.getAll();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

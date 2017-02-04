package com.emcreations.courseworkup608985.business;

import com.emcreations.courseworkup608985.entity.Appointment;
import com.emcreations.courseworkup608985.persistence.AppointmentFacade;
import com.emcreations.courseworkup608985.entity.Client;
import com.emcreations.courseworkup608985.persistence.ClientFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Edward McKnight (UP608985)
 */
@Stateless
public class ClientService {
    @EJB
    private ClientFacade cF;
    @EJB
    private AppointmentFacade aF;
    
    public Client editClient(Client client) {
        cF.edit(client);
        return client;
    }

    public Client getClient(Client client) {
        return cF.find(client.getId());
    }

    public Client createClient(Client client) {
        cF.create(client);
        return client;
    }

    public Client removeClient(Client client) {
        cF.remove(client);
        return client;
    }

    public List<Client> getAll() {
        return cF.findAll();
    }
}

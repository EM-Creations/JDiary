package mcknighte.controller;

import javax.faces.convert.FacesConverter;
import mcknighte.common.AbstractConverter;
import mcknighte.entity.Client;
import mcknighte.persistence.ClientFacade;

/**
 * ClientConverter
 *
 * @author Edward McKnight (UP608985)
 */
@FacesConverter(forClass = Client.class)
public class ClientConverter extends AbstractConverter<Client, ClientFacade, ClientController> {

    /**
     * Constructor
     *
     * @param entityClass Class
     * @param facade Class
     * @param controller Class
     */
    public ClientConverter(Class<Client> entityClass, Class<ClientFacade> facade, Class<ClientController> controller) {
        super(entityClass, facade, controller);
    }
}

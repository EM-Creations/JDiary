package mcknighte.controller;

import javax.faces.convert.FacesConverter;
import mcknighte.common.AbstractConverter;
import mcknighte.entity.Client;
import mcknighte.persistence.ClientFacade;

/**
 * ClientConverter class to handle conversion to/from Client objects
 *
 * @author Edward McKnight (UP608985)
 * @see ClientController
 * @see AbstractConverter
 * @since 2017
 * @version 1.0
 */
@FacesConverter(forClass = Client.class)
public class ClientConverter extends AbstractConverter<Client, ClientFacade, ClientController> {

    /**
     * Constructor
     *
     * @param entityClass the corresponding entity class
     * @param facade the corresponding facade class
     * @param controller the corresponding controller class
     */
    public ClientConverter(Class<Client> entityClass, Class<ClientFacade> facade, Class<ClientController> controller) {
        super(entityClass, facade, controller);
    }
}

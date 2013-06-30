package de.vawi.factoryCanteen;

import de.vawi.factoryCanteen.web.FactoryCanteenWebApplication;
import org.restlet.*;
import org.restlet.data.Protocol;
import org.restlet.resource.*;

public class FactoryCanteenMain extends ServerResource {

    public static void main(String[] args) throws Exception {
        Restlet webApplication = new FactoryCanteenWebApplication();

        Component component = setUpComponent();
        component.getDefaultHost().attach(webApplication);

        component.start();
    }

    private static Component setUpComponent() {
        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8182);
        component.getClients().add(Protocol.FILE);
        component.getClients().add(Protocol.HTTP);
        return component;
    }
}

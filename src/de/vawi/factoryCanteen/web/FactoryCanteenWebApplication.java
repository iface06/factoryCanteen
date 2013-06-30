package de.vawi.factoryCanteen.web;

import de.vawi.factoryCanteen.app.FactoryCanteenApplication;
import de.vawi.factoryCanteen.app.daoFactory.DaoFactory;
import de.vawi.factoryCanteen.persistence.FileSystem;
import de.vawi.factoryCanteen.persistence.interactorDaos.FileDaoFactory;
import de.vawi.factoryCanteen.web.menus.MenuResource;
import org.restlet.*;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

public class FactoryCanteenWebApplication extends Application {

    private static final String ROOT_DIRECTORY = System.getProperty("user.dir");
    private Directory htmlDirectory;
    private Router router;
    private FileSystem persistence;

    @Override
    public Restlet createInboundRoot() {
        setUpFactoryCanteenApplication();
        setUpFactoryCanteenPersistence();
        setUpHtmlDirectory();
        setUpRouterForServerResources();

        return router;
    }

    private void setUpFactoryCanteenApplication() {
        FactoryCanteenApplication application = new FactoryCanteenApplication();
        application.setUp();
    }

    private void setUpHtmlDirectory() {
        htmlDirectory = new Directory(getContext(), "file:///" + ROOT_DIRECTORY + "/view/app");
        htmlDirectory.setListingAllowed(true);
    }

    private void setUpRouterForServerResources() {
        router = new Router(getContext());
        router.attach("/web/", htmlDirectory);
        router.attach("/menus/{calendarWeek}/{year}", MenuResource.class);
        router.attach("/menus", MenuResource.class);
    }

    private void setUpFactoryCanteenPersistence() {
        persistence = new FileSystem();
        persistence.setUp();

        DaoFactory.INSTANCE = new FileDaoFactory();
    }
}

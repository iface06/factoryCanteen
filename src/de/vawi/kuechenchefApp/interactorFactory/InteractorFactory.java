package de.vawi.kuechenchefApp.interactorFactory;

import de.vawi.kuechenchefApp.createMenu.CreateMenusInteractor;
import de.vawi.kuechenchefApp.createMenu.CreateMenusRequest;
import de.vawi.kuechenchefApp.currentMenu.*;
import de.vawi.kuechenchefApp.daoFactory.DaoFactory;
import de.vawi.kuechenchefApp.interactorspec.Interactor;
import de.vawi.kuechenchefApp.interactorspec.RequestBoundary;

/**
 *
 * @author Tobias
 */
public class InteractorFactory {

    public static InteractorFactory INSTANCE;

    public Interactor makeCreateMenuInteractor(RequestBoundary<CreateMenusRequest> boundary) {
        CreateMenusInteractor interactor = new CreateMenusInteractor(boundary);
        interactor.setDao(DaoFactory.INSTANCE.makeCreateMenuDao());
        return interactor;

    }

    public Interactor makeCurrentMenuInteractor(RequestBoundary<FindMenuRequest> boundary) {
        FindMenuInteractor interactor = new FindMenuInteractor(boundary);
        interactor.setDao(DaoFactory.INSTANCE.makeCurrentMenuDao());
        return interactor;
    }
}

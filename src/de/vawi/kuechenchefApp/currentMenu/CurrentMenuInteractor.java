package de.vawi.kuechenchefApp.currentMenu;

import de.vawi.kuechenchefApp.ResponseBoundary;
import de.vawi.kuechenchefApp.RequestBoundary;
import de.vawi.kuechenchefApp.Interactor;
import de.vawi.kuechenchefApp.entities.*;

public class CurrentMenuInteractor implements ResponseBoundary<CurrentMenuResponse>, Interactor {

    private final RequestBoundary<CurrentMenuRequest> requestBoundary;
    private CurrentMenuResponse response;
    private CurrentMenuDao dao;

    public CurrentMenuInteractor(RequestBoundary<CurrentMenuRequest> cmr) {
        this.requestBoundary = cmr;
    }

    @Override
    public void execute() {
        Canteen c = requestBoundary.passRequest().getCanteen();
        Menu menu = dao.findCurrentMenuFor(c);
        response = new CurrentMenuResponse();
        response.setMenu(menu);
    }

    @Override
    public CurrentMenuResponse getResponse() {
        return response;
    }

    public void setDao(CurrentMenuDao dao) {
        this.dao = dao;
    }
}

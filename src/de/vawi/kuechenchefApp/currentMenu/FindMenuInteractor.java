package de.vawi.kuechenchefApp.currentMenu;

import de.vawi.kuechenchefApp.interactors.ResponseBoundary;
import de.vawi.kuechenchefApp.interactors.RequestBoundary;
import de.vawi.kuechenchefApp.interactors.Interactor;
import de.vawi.kuechenchefApp.entities.*;

public class FindMenuInteractor implements ResponseBoundary<FindMenuResponse>, Interactor {

    private final RequestBoundary<FindMenuRequest> requestBoundary;
    private FindMenuResponse response;
    private FindMenuDao dao;

    public FindMenuInteractor(RequestBoundary<FindMenuRequest> cmr) {
        this.requestBoundary = cmr;
    }

    @Override
    public void execute() {
        Canteen c = requestBoundary.passRequest().getCanteen();
        Menu menu = dao.findCurrentMenuFor(c);
        response = new FindMenuResponse();
        response.setMenu(menu);
    }

    @Override
    public FindMenuResponse getResponse() {
        return response;
    }

    public void setDao(FindMenuDao dao) {
        this.dao = dao;
    }
}

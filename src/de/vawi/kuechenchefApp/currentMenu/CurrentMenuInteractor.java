

package de.vawi.kuechenchefApp.currentMenu;

import de.vawi.kuechenchefApp.ResponseBoundary;
import de.vawi.kuechenchefApp.RequestBoundary;
import de.vawi.kuechenchefApp.Interactor;
import de.vawi.kuechenchefApp.speiseplan.Menu;


public class CurrentMenuInteractor implements ResponseBoundary<CurrentMenuResponse>, Interactor {
    
    private final RequestBoundary<CurrentMenuRequest> request;
    private CurrentMenuResponse response;
    private CurrentMenuDao dao;

    public CurrentMenuInteractor(RequestBoundary<CurrentMenuRequest> cmr) {
        this.request = cmr;
    }

    @Override
    public void execute() {
        Menu menu = dao.findCurrentMenu();
        response = new CurrentMenuResponse();
        response.setMenu(menu);
    }

    @Override
    public CurrentMenuResponse getResponse() {
        return response;
    }

    void setDao(CurrentMenuDao dao) {
        this.dao = dao;
    }
}

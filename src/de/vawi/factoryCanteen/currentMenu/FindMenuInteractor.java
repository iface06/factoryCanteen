package de.vawi.factoryCanteen.currentMenu;

import de.vawi.factoryCanteen.entities.Canteen;
import de.vawi.factoryCanteen.entities.Menu;
import de.vawi.factoryCanteen.interactors.ResponseBoundary;
import de.vawi.factoryCanteen.interactors.RequestBoundary;
import de.vawi.factoryCanteen.interactors.Interactor;

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

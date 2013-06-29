package de.vawi.factoryCanteen.currentMenu;

import de.vawi.factoryCanteen.entities.*;
import de.vawi.factoryCanteen.interactors.ResponseBoundary;
import de.vawi.factoryCanteen.interactors.RequestBoundary;
import de.vawi.factoryCanteen.interactors.Interactor;
import java.util.List;

public class FindMenuInteractor implements ResponseBoundary<FindMenuResponse>, Interactor {

    private final RequestBoundary<FindMenuRequest> requestBoundary;
    private FindMenuResponse response;
    private FindMenuDao dao;

    public FindMenuInteractor(RequestBoundary<FindMenuRequest> cmr) {
        this.requestBoundary = cmr;
    }

    @Override
    public void execute() {
        CalendarWeek week = requestBoundary.passRequest().getCalendarWeek();
        List<Offer> menu = dao.findMenuForWeek(week);
        response = new FindMenuResponse();
        response.setOffers(menu);

    }

    @Override
    public FindMenuResponse getResponse() {
        return response;
    }

    public void setDao(FindMenuDao dao) {
        this.dao = dao;
    }
}

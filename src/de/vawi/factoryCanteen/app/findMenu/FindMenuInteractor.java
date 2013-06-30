package de.vawi.factoryCanteen.app.findMenu;

import de.vawi.factoryCanteen.app.entities.*;
import de.vawi.factoryCanteen.interactors.ResponseBoundary;
import de.vawi.factoryCanteen.interactors.RequestBoundary;
import de.vawi.factoryCanteen.interactors.Interactor;
import java.util.List;

public class FindMenuInteractor implements ResponseBoundary<List<Offer>>, Interactor {

    private final RequestBoundary<FindMenuRequest> requestBoundary;
    private FindMenuResponse response;
    private FindMenuDao dao;
    private List<Offer> offers;

    public FindMenuInteractor(RequestBoundary<FindMenuRequest> cmr) {
        this.requestBoundary = cmr;
    }

    @Override
    public void execute() {
        CalendarWeek week = requestBoundary.passRequest().getCalendarWeek();
        offers = dao.findMenuForWeek(week);
    }

    @Override
    public List<Offer> getResponse() {
        return offers;
    }

    public void setDao(FindMenuDao dao) {
        this.dao = dao;
    }
}

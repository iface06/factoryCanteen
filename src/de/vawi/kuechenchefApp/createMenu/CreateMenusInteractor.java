package de.vawi.kuechenchefApp.createMenu;

import de.vawi.kuechenchefApp.entities.Periode;
import de.vawi.kuechenchefApp.interactors.Interactor;
import de.vawi.kuechenchefApp.interactors.ResponseBoundary;
import de.vawi.kuechenchefApp.interactors.RequestBoundary;
import de.vawi.kuechenchefApp.entities.Menu;
import de.vawi.kuechenchefApp.*;
import java.util.List;

public class CreateMenusInteractor implements Interactor, ResponseBoundary<List<Menu>> {

    private final RequestBoundary<CreateMenusRequest> requestBoundary;
    private CreateMenuDao dao;
    private List<Menu> menues;

    public CreateMenusInteractor(RequestBoundary<CreateMenusRequest> requestBoundary) {
        this.requestBoundary = requestBoundary;
    }

    @Override
    public void execute() {
        createMenues();
        storeMenues();
    }

    @Override
    public List<Menu> getResponse() {
        return menues;
    }

    public void setDao(CreateMenuDao dao) {
        this.dao = dao;
    }

    private Periode getPeriodeFromRequest() {
        return requestBoundary.passRequest().getPeriode();
    }

    private void createMenues() {
        MenusCreator creator = new MenusCreator();
        creator.setPlanungsperiode(getPeriodeFromRequest());
        creator.setDao(dao);
        menues = creator.erzeuge();
    }

    private void storeMenues() {
        dao.storeMenues(menues);
    }
}

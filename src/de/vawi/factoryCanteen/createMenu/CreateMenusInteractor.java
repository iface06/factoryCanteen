package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.*;
import de.vawi.factoryCanteen.interactors.Interactor;
import de.vawi.factoryCanteen.interactors.ResponseBoundary;
import de.vawi.factoryCanteen.interactors.RequestBoundary;
import java.util.List;

public class CreateMenusInteractor implements Interactor, ResponseBoundary<List<Offer>> {

    private final RequestBoundary<CreateMenusRequest> requestBoundary;
    private CreateMenuDao dao;
    private List<Offer> offers;

    public CreateMenusInteractor(RequestBoundary<CreateMenusRequest> requestBoundary) {
        this.requestBoundary = requestBoundary;
    }

    @Override
    public void execute() {
        createMenues();
        storeOffers();
    }

    @Override
    public List<Offer> getResponse() {
        return offers;
    }

    public void setDao(CreateMenuDao dao) {
        this.dao = dao;
    }

    private void createMenues() {
        OffersCreator creator = new OffersCreator();
        creator.setDao(dao);
        creator.setPeriode(getPeriodeFromRequest());

        creator.addRule(new EveryDayDishMenuRule(DishCategory.MEAT));
        creator.addRule(new EveryDayDishMenuRule(DishCategory.VEGETARIAN));
        creator.addRule(new ThereIsFishOnFridayRule());
        creator.addRule(new AlternativeDihesRule());

        offers = creator.create();

    }

    private PeriodeConfiguration getPeriodeFromRequest() {
        return requestBoundary.passRequest().getPeriode();
    }

    private void storeOffers() {
        dao.storeOffers(offers);
    }
}

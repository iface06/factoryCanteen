package de.vawi.factoryCanteen.app.createMenu;

import de.vawi.factoryCanteen.app.entities.*;
import de.vawi.factoryCanteen.interactors.Interactor;
import de.vawi.factoryCanteen.interactors.ResponseBoundary;
import de.vawi.factoryCanteen.interactors.RequestBoundary;
import java.util.List;

public class CreateMenusInteractor implements Interactor, ResponseBoundary<List<Offer>> {

    private final RequestBoundary<CreateMenusRequest> requestBoundary;
    private CreateMenuDao dao;
    private List<Offer> offers;
    private List<Offer> wholeOffers;
    protected MenuCreator creator;

    public CreateMenusInteractor(RequestBoundary<CreateMenusRequest> requestBoundary) {
        this.requestBoundary = requestBoundary;
    }

    @Override
    public void execute() {
        setupOffersCreator();
        createMenu();
        storeMenu();
    }

    @Override
    public List<Offer> getResponse() {
        return offers;
    }

    public void setDao(CreateMenuDao dao) {
        this.dao = dao;
    }

    public void setMenuCreator(MenuCreator creator) {
        this.creator = creator;
    }

    private PeriodeConfiguration getPeriodeFromRequest() {
        return requestBoundary.passRequest().getPeriode();
    }

    private void storeMenu() {
        dao.storeOffers(offers);
    }

    protected void setupOffersCreator() {
        creator.setDao(dao);
        creator.setPeriode(getPeriodeFromRequest());

        creator.addRule(new EveryDayDishMenuRule(DishCategory.MEAT));
        creator.addRule(new EveryDayDishMenuRule(DishCategory.VEGETARIAN));
        creator.addRule(new FishOnFridayRule());
        creator.addRule(new AlternativeDihesRule(getPeriodeFromRequest().getNumberOfDishesPerDay()));
    }

    public void createMenu() {
        offers = creator.create();
    }
}

package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.*;
import java.util.*;
import org.joda.time.DateTime;

/**
 *
 * @author Tobias
 */
class EveryDayDishMenuRule {

    private List<Offer> offers;
    private List<Dish> dishesByCategory;
    private Periode periode;
    private Date startDate;
    private CreateMenuDao dao;
    private DishCategory dishCategory;

    void setDishCategory(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
    }

    void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    void setPeriode(Periode periode) {
        this.periode = periode;
    }

    void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    void setDao(CreateMenuDao dao) {
        this.dao = dao;
    }

    void execute() {
        dishesByCategory = dao.findDishesByCategory(dishCategory);
        for (int dishNumber = 0; dishNumber < periode.calculateNumberOfRequiredMeatDishes(); dishNumber++) {
            Offer offer = createOffer(dishNumber);
            offers.add(offer);
        }
    }

    private Offer createOffer(int dishNumber) {
        Offer offer = new Offer();
        Date offerDate = calculateOfferDate(dishNumber);
        offer.setDate(offerDate);
        offer.setDish(dishesByCategory.get(dishNumber));
        return offer;
    }

    private Date calculateOfferDate(int dishNumber) {
        Date offerDate = new DateTime(startDate).plusDays(dishNumber).toDate();
        return offerDate;
    }
}

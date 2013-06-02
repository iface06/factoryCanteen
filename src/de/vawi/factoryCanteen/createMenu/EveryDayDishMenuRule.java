package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.*;
import java.util.*;
import org.joda.time.DateTime;

/**
 *
 * @author Tobias
 */
class EveryDayDishMenuRule implements MenuCreationRule {

    private List<Dish> dishesByCategory;
    private PeriodeConfiguration periode;
    private Date startDate;
    private CreateMenuDao dao;
    private DishCategory dishCategory;

    public EveryDayDishMenuRule(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
    }

    public void execute(List<Offer> offers) {
        dishesByCategory = dao.findDishesByCategory(dishCategory);
        for (int dishNumber = 0; dishNumber < periode.calculateNumberOfRequiredMeatDishes(); dishNumber++) {
            Offer offer = createOffer(dishNumber);
            offers.add(offer);
        }
    }

    public void setPeriode(PeriodeConfiguration periode) {
        this.periode = periode;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setDao(CreateMenuDao dao) {
        this.dao = dao;
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

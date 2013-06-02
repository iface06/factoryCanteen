package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.createMenu.CreateMenuDao;
import de.vawi.factoryCanteen.entities.*;
import java.util.*;
import org.joda.time.DateTime;

/**
 *
 * @author Tobias
 */
class AlternativeDihesRule implements MenuCreationRule {

    private List<Offer> offers;
    private PeriodeConfiguration periode;
    private Date startDate;
    private List<Dish> favorDishes;
    private CreateMenuDao dao;

    @Override
    public void execute(List<Offer> offers) {
        this.offers = offers;
        favorDishes = dao.findFavorDishesForPeriode();
        for (int days = 0; days < periode.getNumberOfDays(); days++) {
            Date today = new DateTime(startDate).plus(days).toDate();
            if (isAlternativeMealStillRequired(today)) {
                Offer offer = new Offer();
                offer.setDate(today);
                offer.setDish(loadDish(days));
                offers.add(offer);
            }
        }
    }

    private boolean isAlternativeMealStillRequired(Date today) {
        List<Offer> offersForToday = findOffersForToday(today);
        return offersForToday.size() < periode.getNumberOfMealsPerDay();
    }

    private List<Offer> findOffersForToday(Date today) {
        List<Offer> offersForToday = new ArrayList<>();
        for (Offer offer : offers) {
            if (offer.getDate().equals(today)) {
                offersForToday.add(offer);
            }
        }
        return offersForToday;
    }

    private Dish loadDish(int days) {
        for (Dish dish : favorDishes) {
            if (dishIsNotAreadyOffered(dish)) {
                return dish;
            }
        }
        throw new NotEnoughDishesForMenuCreationAvailable();
    }

    private boolean dishIsNotAreadyOffered(Dish dish) {
        for (Offer offer : offers) {
            if (offer.getDish().equals(dish)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setPeriode(PeriodeConfiguration periode) {
        this.periode = periode;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public void setDao(CreateMenuDao dao) {
        this.dao = dao;
    }
}

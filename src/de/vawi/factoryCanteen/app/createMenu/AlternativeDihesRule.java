package de.vawi.factoryCanteen.app.createMenu;

import de.vawi.factoryCanteen.app.createMenu.CreateMenuDao;
import de.vawi.factoryCanteen.app.entities.*;
import java.util.*;
import org.joda.time.DateTime;

/**
 *
 * @author Tobias
 */
class AlternativeDihesRule implements MenuCreationRule {

    private List<Offer> offers;
    private List<Dish> favorDishes;
    private CreateMenuDao dao;
    private int requiredDishesPerDay;

    public AlternativeDihesRule(int requiredDishesPerDay) {
        this.requiredDishesPerDay = requiredDishesPerDay;
    }

    @Override
    public void execute(List<Offer> offers, Date offerDate) {
        this.offers = offers;
        favorDishes = dao.findFavorDishesForPeriode();

        if (isAlternativeMealStillRequired(offerDate)) {
            Offer offer = new Offer();
            offer.setDate(offerDate);
            offer.setDish(loadDish());
            offers.add(offer);
        }

    }

    private boolean isAlternativeMealStillRequired(Date today) {
        return offers.size() < requiredDishesPerDay;
    }

    private Dish loadDish() {
        for (Dish dish : favorDishes) {
            if (dishIsNotAlreadyOffered(dish)) {
                return dish;
            }
        }
        throw new NotEnoughDishesForMenuCreationAvailable();
    }

    private boolean dishIsNotAlreadyOffered(Dish dish) {
        for (Offer offer : offers) {
            if (offer.getDish().equals(dish)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setDao(CreateMenuDao dao) {
        this.dao = dao;
    }
}

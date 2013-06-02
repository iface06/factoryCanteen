package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.*;
import java.util.*;
import org.joda.time.DateTime;

/**
 *
 * @author Tobias
 */
public class OffersCreator {

    private List<Offer> offers = new ArrayList<>();
    private Periode periode;
    private CreateMenuDao dao;
    private List<Dish> orderedFavoriteDishes;
    private DateTime startDateOfPeriode;

    public List<Offer> create() {
        orderedFavoriteDishes = dao.findFavorDishesForPeriode(periode);
        if (enoughtDishesAvailable()) {
            startDateOfPeriode = new DateTime(periode.nextStartDate());
            for (int dishNumber = 0; dishNumber < periode.quantityOfRequiredDishes(); dishNumber++) {
                Offer offer = createOffer(dishNumber);
                offers.add(offer);
            }
        }
        sortOffersByDate();

        return offers;
    }

    private boolean enoughtDishesAvailable() {
        return dao.areEnoughtDishesAvailable();
    }

    private Offer createOffer(int dishNumber) {
        Offer offer = new Offer();
        Date offerDate = calculateOfferDate(dishNumber);
        offer.setDate(offerDate);
        offer.setDish(orderedFavoriteDishes.get(dishNumber));
        return offer;
    }

    private Date calculateOfferDate(int dishNumber) {
        int days = dishNumber % periode.calculateNumberOfDishesPerWeek();
        Date offerDate = startDateOfPeriode.plusDays(days).toDate();
        return offerDate;
    }

    private void sortOffersByDate() {
        Collections.sort(offers, new Comparator<Offer>() {
            @Override
            public int compare(Offer o1, Offer o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

    public void setDao(CreateMenuDao dao) {
        this.dao = dao;
    }
}

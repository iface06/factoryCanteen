package de.vawi.factoryCanteen.app.createMenu;

import de.vawi.factoryCanteen.app.entities.*;
import java.util.*;

/**
 *
 * @author Tobias
 */
public abstract class NoReplicationRule implements MenuCreationRule {

    private List<Offer> wholeOffers;

    public void setAlreadySelectedOffers(List<Offer> wholeOffers) {
        this.wholeOffers = wholeOffers;
    }

    @Override
    public void execute(List<Offer> offers, Date offerDate) {
        int dishNumber = 0;
        Offer nextOffer = createOfferForOfferDate(offerDate);
        if (isOfferRequiredForOfferDate(offerDate, offers)) {
            do {
                Dish dish = selectDishForOffer(dishNumber);
                nextOffer.setDish(dish);
                dishNumber++;
            } while (isDishAlreadyOffered(offers, nextOffer));
            offers.add(nextOffer);
        }
    }

    public boolean isDishAlreadyOffered(List<Offer> offers, Offer dailyOffer) {
        return offers.contains(dailyOffer) || wholeOffers.contains(dailyOffer);
    }

    @Override
    public abstract void setDao(CreateMenuDao dao);

    protected abstract Dish selectDishForOffer(int dishNumber);

    private Offer createOfferForOfferDate(Date offerDate) {
        Offer nextOffer = new Offer();
        nextOffer.setDate(offerDate);
        return nextOffer;
    }

    protected boolean isOfferRequiredForOfferDate(Date offerDate, List<Offer> offers) {
        return true;
    }
}

package de.vawi.factoryCanteen.web.menus;

import de.vawi.factoryCanteen.app.entities.Offer;
import java.util.*;

/**
 *
 * @author Tobias
 */
class MenuRow {

    private List<Offer> offers = new ArrayList<>();

    public void add(Offer offer) {
        offers.add(offer);
    }

    public int size() {
        return offers.size();
    }

    public List<Offer> getOffers() {
        return offers;
    }
}

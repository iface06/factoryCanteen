package de.vawi.factoryCanteen.findMenu;

import de.vawi.factoryCanteen.entities.*;
import java.util.List;

public class FindMenuResponse {

    private List<Offer> offers;

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}

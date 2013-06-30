package de.vawi.factoryCanteen.app.findMenu;

import de.vawi.factoryCanteen.app.entities.Offer;
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

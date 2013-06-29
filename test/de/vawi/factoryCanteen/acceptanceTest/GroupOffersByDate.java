package de.vawi.factoryCanteen.acceptanceTest;

import de.vawi.factoryCanteen.entities.Offer;
import java.util.*;

/**
 *
 * @author Tobias
 */
public class GroupOffersByDate {

    public Map<Date, List<Offer>> apply(List<Offer> offers) {
        Map<Date, List<Offer>> groupdOffers = new HashMap<>();
        for (Offer offer : offers) {
            List<Offer> offersPerDay = groupdOffers.get(offer.getDate());
            if (offersPerDay != null) {
                offersPerDay.add(offer);
            } else {
                offersPerDay = new ArrayList<>();
                offersPerDay.add(offer);
                groupdOffers.put(offer.getDate(), offersPerDay);
            }
        }
        return groupdOffers;
    }
}

package de.vawi.factoryCanteen.app.createMenu;

import de.vawi.factoryCanteen.app.entities.Offer;
import java.util.*;

/**
 *
 * @author Tobias
 */
public interface MenuCreationRule {

    public void execute(List<Offer> offers, Date offerDate);

    public void setDao(CreateMenuDao dao);

    public void setAlreadySelectedOffers(List<Offer> wholeOffers);
}

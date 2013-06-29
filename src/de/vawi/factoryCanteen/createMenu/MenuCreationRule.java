package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.*;
import java.util.*;

/**
 *
 * @author Tobias
 */
public interface MenuCreationRule {

    public void execute(List<Offer> offers, Date offerDate);

    public void setDao(CreateMenuDao dao);
}

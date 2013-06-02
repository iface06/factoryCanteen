package de.vawi.factoryCanteen.persistence.interactorDaos;

import de.vawi.factoryCanteen.currentMenu.FindMenuDao;
import de.vawi.factoryCanteen.entities.*;
import de.vawi.factoryCanteen.persistence.offers.OffersDB;
import java.util.List;

public class CurrentMenuFileDao implements FindMenuDao {

    @Override
    public List<Offer> findCurrentMenuFor() {
        OffersDB offers = OffersDB.getInstance();
        return offers.findCurrentMenu(null);
    }
}

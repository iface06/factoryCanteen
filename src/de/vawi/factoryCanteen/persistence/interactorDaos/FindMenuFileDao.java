package de.vawi.factoryCanteen.persistence.interactorDaos;

import de.vawi.factoryCanteen.app.entities.CalendarWeek;
import de.vawi.factoryCanteen.app.entities.Offer;
import de.vawi.factoryCanteen.app.findMenu.FindMenuDao;
import de.vawi.factoryCanteen.persistence.offers.OffersDB;
import java.util.List;

public class FindMenuFileDao implements FindMenuDao {

    @Override
    public List<Offer> findMenuForWeek(CalendarWeek week) {
        OffersDB offers = OffersDB.getInstance();
        return offers.findOffersForCalendarWeek(week);
    }
}

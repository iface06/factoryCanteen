package de.vawi.factoryCanteen.app.findMenu;

import de.vawi.factoryCanteen.app.entities.*;
import java.util.List;

public interface FindMenuDao {

    public List<Offer> findMenuForWeek(CalendarWeek week);
}

package de.vawi.factoryCanteen.findMenu;

import de.vawi.factoryCanteen.entities.*;
import java.util.List;

public interface FindMenuDao {

    public List<Offer> findMenuForWeek(CalendarWeek week);
}

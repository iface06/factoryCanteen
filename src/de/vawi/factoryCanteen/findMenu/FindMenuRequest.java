package de.vawi.factoryCanteen.findMenu;

import de.vawi.factoryCanteen.entities.Canteen;
import de.vawi.factoryCanteen.entities.CalendarWeek;

public class FindMenuRequest {

    private CalendarWeek calendarWeek;

    public CalendarWeek getCalendarWeek() {
        return calendarWeek;
    }

    public void setCalendarWeek(CalendarWeek calendarWeek) {
        this.calendarWeek = calendarWeek;
    }
}

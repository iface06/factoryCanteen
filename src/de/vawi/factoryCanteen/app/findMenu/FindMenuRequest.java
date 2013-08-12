package de.vawi.factoryCanteen.app.findMenu;

import de.vawi.factoryCanteen.app.entities.CalendarWeek;

public class FindMenuRequest {

    private CalendarWeek calendarWeek;

    public CalendarWeek getCalendarWeek() {
        return calendarWeek;
    }

    public void setCalendarWeek(CalendarWeek calendarWeek) {
        this.calendarWeek = calendarWeek;
    }
}

package de.vawi.factoryCanteen.web.menus;

import de.vawi.factoryCanteen.app.entities.CalendarWeek;
import java.util.*;

/**
 *
 * @author Tobias
 */
public class MenuPresenter {

    private List<MenuRow> menuRows;
    private CalendarWeek calendarWeek;
    private List<Date> weekdays;

    public List<MenuRow> getMenuRows() {
        return menuRows;
    }

    public void setMenuRows(List<MenuRow> rows) {
        this.menuRows = rows;
    }

    public CalendarWeek getCalendarWeek() {
        return calendarWeek;
    }

    public void setCalendarWeek(CalendarWeek calendarWeek) {
        this.calendarWeek = calendarWeek;
    }

    public List<Date> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(List<Date> weekdays) {
        this.weekdays = weekdays;
    }
}

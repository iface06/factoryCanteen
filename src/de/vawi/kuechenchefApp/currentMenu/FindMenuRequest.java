package de.vawi.kuechenchefApp.currentMenu;

import de.vawi.kuechenchefApp.entities.*;

public class FindMenuRequest {

    private Canteen canteen;
    private CalendarWeek calendarWeek;

    public Canteen getCanteen() {
        return canteen;
    }

    public void setCanteen(Canteen canteen) {
        this.canteen = canteen;
    }

    public CalendarWeek getCalendarWeek() {
        return calendarWeek;
    }

    public void setCalendarWeek(CalendarWeek calendarWeek) {
        this.calendarWeek = calendarWeek;
    }
}

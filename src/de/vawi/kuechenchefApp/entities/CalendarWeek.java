package de.vawi.kuechenchefApp.entities;

import java.io.Serializable;
import java.util.Date;
import org.joda.time.DateTime;

public class CalendarWeek implements Comparable<CalendarWeek>, Serializable {

    private int week;
    private int year;

    public static CalendarWeek current() {
        return fromDate(new Date());
    }

    public static CalendarWeek fromWeekAndYear(int week, int year) {
        CalendarWeek cw = new CalendarWeek();
        cw.setWeek(week);
        cw.setYear(year);
        return cw;
    }

    public static CalendarWeek fromDate(Date d) {
        CalendarWeek week = new CalendarWeek();
        DateTime dt = new DateTime(d);
        week.setWeek(dt.getWeekOfWeekyear());
        week.setYear(dt.getYear());
        return week;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(CalendarWeek.class)) {
            CalendarWeek cw = (CalendarWeek) obj;
            return this.year == cw.year && this.week == cw.week;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.week;
        hash = 71 * hash + this.year;
        return hash;
    }

    @Override
    public int compareTo(CalendarWeek o) {
        if (this.year < o.year) {
            return -1;
        } else if (this.year > o.year) {
            return 1;
        } else {
            return compareWeeks(o);
        }
    }

    private int compareWeeks(CalendarWeek o) {
        if (this.week < o.week) {
            return -1;
        } else if (this.week > o.week) {
            return 1;
        } else {
            return 0;
        }
    }
}

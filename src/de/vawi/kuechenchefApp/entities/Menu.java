package de.vawi.kuechenchefApp.entities;

import java.util.*;

/**
 * Ein Speiseplan für eine Planungsperiode. Solch ein Plan ist einer Kantine
 * zugeordnet und haelt eine Liste von Tagen entsprechend der Anzahl von Tagen,
 * die der Plan umfassen soll.
 *
 * @author Beer
 * @version 30.01.2013
 */
public class Menu implements Iterable<Day> {

    public static Menu emptyFor(Canteen c) {
        Menu emptyMenu = new Menu();
        emptyMenu.setCalendarWeek(CalendarWeek.current());
        emptyMenu.setKantine(c);
        return emptyMenu;
    }

    private List<Day> tageMitGerichten = new ArrayList<>();
    private Canteen kantine;
    private CalendarWeek calendarWeek;

    /**
     * Konstruktor
     */
    public Menu() {
    }

    /**
     * Konstruktor der die noetigen Parameter für einen Speiseplan aufnimmt.
     *
     * @param kantine gibt die Kantine an zu dem der Plan erzeugt werden.
     * @param tageMitGerichten eine Liste mit Tagen, entsprechend der Anzahl an
     * Tagen, die der Plan beinhalten soll
     */
    public Menu(Canteen kantine, List<Day> tageMitGerichten) {
        this.kantine = kantine;
        this.tageMitGerichten = tageMitGerichten;
    }

    /**
     * @return Gibt die die Liste mit den Tagen der Planungsperiode zurueck.
     */
    public List<Day> getTageMitGerichten() {
        return this.tageMitGerichten;
    }

    /**
     * @return Gibt die zugeordnete Kantine zurueck
     */
    public Canteen getKantine() {
        return this.kantine;
    }

    /**
     * Setzt die zugehoerige Kantine
     *
     * @param kantine
     */
    public void setKantine(Canteen kantine) {
        this.kantine = kantine;
    }

    /**
     * @return Kalenderwoch für welche die Speisekarte gilt
     */
    public CalendarWeek getCalendarWeek() {
        return calendarWeek;
    }

    /**
     *
     * @param calendarWeek Kalenderwoch für welche die Speisekarte gilt
     */
    public void setCalendarWeek(CalendarWeek calendarWeek) {
        this.calendarWeek = calendarWeek;
    }

    /**
     * Fuegt dem Plan einen Tag hinzu
     *
     * @param tag
     */
    public void addDay(Day tag) {
        this.tageMitGerichten.add(tag);
    }
    
    /**
     * Fuegt dem Plan mehrere Tag hinzu
     *
     * @param tag
     */
    public void addDays(List<Day> days) {
        this.tageMitGerichten.addAll(days);
    }

    /**
     * Macht einen Plan über die Tage iterierbar
     *
     * @return
     */
    @Override
    public Iterator<Day> iterator() {
        return tageMitGerichten.iterator();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.kantine != null ? this.kantine.hashCode() : 0);
        hash = 37 * hash + Objects.hashCode(this.calendarWeek);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Menu other = (Menu) obj;
        if (this.kantine != other.kantine) {
            return false;
        }
        if (!Objects.equals(this.calendarWeek, other.calendarWeek)) {
            return false;
        }
        return true;
    }
}

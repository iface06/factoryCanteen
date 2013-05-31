package de.vawi.factoryCanteen.entities;

import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * Diese Klasse beinhaltet die Daten für die Planungsperiode. Die Anzahl der
 * Wochen, die Tage pro Woche und die Anzahl an Gerichten kann so zentral
 * gepflegt werden.
 *
 * Monostate Pattern!
 *
 * @author Lepping
 * @version 29.01.2013
 */
public class Periode {

    private static int numberOfWeeks = 3;
    private static int numberOfDaysPerWeek = 5;
    private static int anzahlGerichteProTag = 3;
    private static Date nextStartDate = new DateTime().withDate(2012, 12, 31).withTime(0, 0, 0, 0).toDate();
    private static int numberOfDishes = 3;

    /**
     *
     * @return Gibt die Anzahl an Wochen zurück
     */
    public int getAnzahlWochen() {
        return numberOfWeeks;
    }

    /**
     *
     * @return Gibt die Anzahl an Tagen pro Woche zurück
     */
    public int getAnzahlTageProWoche() {
        return numberOfDaysPerWeek;
    }

    /**
     *
     * @return Gibt die Anzahl an Gerichten pro Tag zurück
     */
    public int getAnzahlGerichteProTag() {
        return anzahlGerichteProTag;
    }

    /**
     *
     * @return Gibt die Anzahl an benötigter Speisen für Planungsperiode zurück
     */
    public int quantityOfRequiredDishes() {
        return anzahlGerichteProTag * numberOfDaysPerWeek * numberOfWeeks;
    }

    public int numberOfDays() {
        return numberOfDaysPerWeek * numberOfWeeks;
    }

    /**
     * Setzt Anzahl der Wochen für eine Planungsperiode (Standart 3)
     *
     * @param anzahlWochen
     */
    public void setNumberOfWeek(int anzahlWochen) {
        this.numberOfWeeks = anzahlWochen;
    }

    /**
     * Setzt Anzahl der Tage die in eine Woche berücksichtigt werden sollen
     * (Standart 5)
     *
     * @param anzahlTageProWoche
     */
    public void setNumberOfDaysPerWeek(int anzahlTageProWoche) {
        this.numberOfDaysPerWeek = anzahlTageProWoche;
    }

    /**
     * Setzt Anzahl der angeboteten Gerichte pro Tag (Standart 3)
     *
     * @param anzahlGerichteProTag
     */
    public void setNumberOfOfferedDishesPerDay(int anzahlGerichteProTag) {
        this.anzahlGerichteProTag = anzahlGerichteProTag;
    }

    public void setNumberOfMealsPerDay(int numberOfMealsPerDay) {
        this.numberOfDishes = numberOfMealsPerDay;
    }

    public int getNumberOfMealsPerDay() {
        return numberOfDishes;
    }

    /**
     *
     * Gibt Anzahl von benötigten Fischspeisen zurück
     *
     * @return Anzahl an benötigten Fischpeisen
     */
    public int berechneAnzahlBenoetigterFischSpeisen() {
        return numberOfWeeks;
    }

    /**
     *
     * Gibt Anzahl an benötigten vegetarischen Speisen zurück
     *
     * @return Anzahl an benötigten vegetarischen Speisen
     */
    public int berechneAnzahlBenoetigteVegetarischeSpeisen() {
        return numberOfWeeks * numberOfDaysPerWeek;
    }

    /**
     *
     * Gibt Anzahl an benötigten Fleischspeisen zurück
     *
     * @return Anzahl an benötigten Fleischspeisen
     */
    public int calculateNumberOfRequiredMeatDishes() {
        return numberOfWeeks * numberOfDaysPerWeek;
    }

    public int calculateNumberOfMealsForPeriode() {
        return numberOfWeeks * numberOfDaysPerWeek * numberOfDishes;
    }

    public Date nextStartDate() {
        return nextStartDate;
    }

    public void setNextStartDate(Date date) {
        if (date.compareTo(nextStartDate) > 1) {
            nextStartDate = date;
        }
    }

    public int calculateNumberOfDishesPerWeek() {
        return numberOfDaysPerWeek * numberOfDishes;
    }
}

package de.vawi.kuechenchefApp.lieferanten;

import java.util.ArrayList;

/**
 * Der Bauer verrechnet die Lieferung nach der Enterfernung in km. Pro Kilometer
 * 2,00€
 *
 * @author Struebe
 * @version 30.12.2012
 */
public class Bauer extends Lieferant {

    public static double PAUSCHALE_PRO_KILOMETER = 2.0;

    /**
     * Diese Methode berechnet die Lieferkosten für einen Bauern.
     *
     * @param einkaufsWert Der Einkaufswert, der bei dem Lieferanten gekauft
     * wird - für den Bauern nicht relevant.
     * @return Gibt die Lieferkosten eines Bauern wieder.
     */
    @Override
    public double berechneLieferkosten(double einkaufsWert) {
        return (PAUSCHALE_PRO_KILOMETER * getLieferKostenFaktor());
    }
}

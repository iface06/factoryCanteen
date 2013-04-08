package de.vawi.kuechenchefApp.lieferanten;

/**
 * Der Großhändler verrechnet vom gesamten Einkaufswert einen Kostensatz für die
 * Lieferung.
 *
 * Z. B.: 4 * 25.00 € * 1.2 = 120.00€ --> 4 Stk. à 25.00 € Kosten 120.00€
 * inklusive Lieferung, wobei der Lieferkosten-Anteil 20€ beträgt.
 *
 * @author Struebe
 * @version 30.12.2012
 */
public class Grosshaendler extends Lieferant {

    /**
     * Diese Methode berechnet die Lieferkosten eines Großhändlers.
     *
     * @param einkaufsWert Der Einkaufswert, der insgesamt beim Großhändler
     * bestellt wird.
     * @return Gibt die Lieferkosten eines Großhändlers wieder.
     */
    @Override
    public double berechneLieferkosten(double einkaufsWert) {
        return (einkaufsWert * (getLieferKostenFaktor() - 1.0));
    }
}

package de.vawi.kuechenchefApp.entities;

import java.util.*;

/**
 * Diese abstrakte Klasse bereitet für ihre zwei Unterklassen, Bauer und
 * Großhaendler, den Namen und den Lieferkostensatz für die spätere
 * Lieferkostenberechnung vor. Sie hält außerdem eine Liste mit Lieferanten und
 * ihren entsprechenden Preislisten-Positionen.
 *
 *
 * @author Struebe
 * @version 30.12.2012
 */
public abstract class Supplier {

    private String name;
    private double lieferKostenFaktor;

    /**
     * @param name Name des Lieferanten
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param lieferKostenFaktor Wird der Lieferant als Bauer klassifiziert,
     * steht hier die Entfernung in km (z.B. 5 km), wird der Lieferant als
     * Großhändler klassifiziert, steht hier der Lieferkostenfaktor, mit dem der
     * Gesamtpreis der anzuliefernden Ware miltipliziert wird, um die
     * Lieferkosten abzudecken.
     * @return macht den lieferKostenFaktor öffentlich zugänglich.
     */
    public double getLieferKostenFaktor() {
        return this.lieferKostenFaktor;
    }

    /**
     *
     * @param lieferKostenFaktor Im Falle des Bauern die Entfernung in KM, im
     * Falle des Großhändlers der Lieferkostenfaktor zum Berechnen der
     * Lieferkosten.
     */
    public void setLieferKostenFaktor(double lieferKostenFaktor) {
        this.lieferKostenFaktor = lieferKostenFaktor;
    }

    /**
     * @return Name repräsentiert den Namen des Lieferanten
     */
    public String getName() {
        return this.name;
    }

    /**
     * Diese Methode berechnet in den Unterklassen die Lieferkosten, wobei der
     * parameter nur beim Großhaendler benötigt wird.
     *
     * @param einkaufsWert Stellt den Gesamtpreis der bei einem Großhändler
     * bestellten Ware dar.
     * @return Lieferkosten, die für die Bestellung anfallen.
     */
    public abstract double berechneLieferkosten(double einkaufsWert);

    /**
     *
     * @return Gibt die Unterklasse, in die der Lieferant einsortiert wurde
     * (Bauer oder Großhändler), und den Lieferantennamen aus. Beispiel: Bauer
     * 'Anglerkollektiv Nord-West', oder Grosshaendler 'Metri AG'.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName().toString() + " '" + this.name + "'";
    }

    @Override
    public boolean equals(Object obj) {
        Supplier lieferant = (Supplier) obj;
        return this.name.equals(lieferant.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}

package de.vawi.kuechenchefApp.lieferanten;

import java.util.Comparator;

/**
 * Diese Klasse vergeleicht zwei Preislistenpositionen nach Preis pro Einheit.
 *
 * @author Tatsch
 * @version 30.01.2013
 */
class VergleichePreisListenPositionNachPreisProEinheit implements Comparator<PreisListenPosition> {

    /**
     * Diese Methode vergeleicht zwei Preislistenpositionen nach Preis pro
     * Einheit.
     *
     * @param t die erste Preislistenposition.
     * @param t1 die Preislistenposition, mit der verglichen wird.
     * @return Kommt ein Wert kleine als 0 heraus, so ist der t größer t1, ist
     * der Wert größer als 0, ist t1 größer als t. Bei 0 sind beide Werte gleich
     * groß.
     */
    @Override
    public int compare(PreisListenPosition t, PreisListenPosition t1) {
        return new Double(t.berechnePreisProEinheit()).compareTo(new Double(t1.berechnePreisProEinheit()));
    }
}

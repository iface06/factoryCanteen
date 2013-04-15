package de.vawi.kuechenchefApp.entities;

import de.vawi.kuechenchefApp.entities.Supplier;
import de.vawi.kuechenchefApp.entities.Food;

/**
 * Diese Klasse stellt eine Position in der Preisliste des Lieferanten dar;
 * Eine Preislistenposition entspricht einer Zeile auf der eingelesenen Preisliste.
 *
 * @author Struebe
 * @version 25.01.2013
 */
public class PriceListPosition {

    private double gebindeGroesse;
    private Food nahrungsmittel;
    private double preis;
    private int vorratsBestand;
    private Supplier lieferant;

    /**
     * @return Lieferant der Preislisten-Position
     */
    public Supplier getLieferant() {
        return lieferant;
    }

    /**
     * @param lieferant Lieferant der Preislisten-Position
     */
    public void setLieferant(Supplier lieferant) {
        this.lieferant = lieferant;
    }

    /**
     * @return Gebindegrösse in der das Nahrungsmittel verkauft wird
     */
    public double getGebindeGroesse() {
        return gebindeGroesse;
    }

    /**
     * @param gebindeGroesse Gebindegrösse in der das Nahrungsmittel verkauft
     * wird
     */
    public void setGebindeGroesse(double gebindeGroesse) {
        this.gebindeGroesse = gebindeGroesse;
    }

    /**
     * @return Angebotenes Nahrungsmittel
     */
    public Food getNahrungsmittel() {
        return nahrungsmittel;
    }

    /**
     * @param nahrungsmittel Angebotenes Nahrungsmittel
     */
    public void setNahrungsmittel(Food nahrungsmittel) {
        this.nahrungsmittel = nahrungsmittel;
    }

    /**
     * @return Preis pro Gebindegrösse eines Nahrungsmittels
     */
    public double getPreis() {
        return preis;
    }

    /**
     * @param preis Preis pro Gebindegrösse eines Nahrungsmittels
     */
    public void setPreis(double preis) {
        this.preis = preis;
    }

    /**
     * @return Vorratsbestand je Gebindegrösse
     */
    public int getVorratsBestand() {
        return vorratsBestand;
    }

    /**
     * @param vorratsBestand Vorratsbestand je Gebindegrösse
     */
    public void setVorratsBestand(int vorratsBestand) {
        this.vorratsBestand = vorratsBestand;
    }

    /**
     *
     * @return Gibt einen Text aus, der besagt welches Nahrungsmittel zu welchem
     * Preis zur Verfügung steht. Beispiel: Salami à 1000.0 GRAMM zu 4.98 €.
     */
    public String toString() {
        return nahrungsmittel + " à " + gebindeGroesse + " " + nahrungsmittel.getEinheit() + " zu " + preis + " €";
    }

    /**
     * Diese Methode errechnet aus dem Preis eines Gebindes den Preis einer
     * Einzel-Einheit des Nahrungsmittels.
     *
     * @return Einzel-Preis
     */
    public double berechnePreisProEinheit() {
        return preis / gebindeGroesse;
    }
    
    
    /**
     *Berechne benötigte Gebinde für die übergebene Menge
     *
     * @return Anzahl Gebinde für Menge
     */
    public double berechneAnzahlGebindeFuerMenge(double menge){
        return Math.ceil(menge / gebindeGroesse);
    }
    
    /**
     * Berechne Preis indem Anzahl benötigter Gebinde * Preis pro Gebinde
     * Berechnung der benötigten Gebinde durch Methode berechneAnzahlGebindeFuerMenge()
     *
     * @return Berechneter Preis für die Menge
     */
    public double berechnePreisFuerMenge(double menge){
        return berechneAnzahlGebindeFuerMenge(menge) * preis;
    }
    
    /**
     * Gesamtmenge = Voratsbestand in Gebinde * Gebindegröße
     * @return Voratsbestand in Gebinde * Gebindegröße
     */
    public double getGesamtMenge(){
        return vorratsBestand * gebindeGroesse;
    }
}

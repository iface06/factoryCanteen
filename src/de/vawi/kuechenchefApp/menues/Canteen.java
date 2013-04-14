package de.vawi.kuechenchefApp.menues;


/**
 * Diese Klasse repraesentiert eine Kantine.
 * 
 * @author Beer
 * @version 30.01.2013
 */
public enum Canteen
{
    MUELHEIM_AN_DER_RUHR(300), ESSEN(500);
    
    private int mitarbeiterAnzahl;
    private double anteilBeliebtestesGericht = 0.5;
    private double anteilZweitbeliebtestesGerichte = 0.25;
    private double anteilDrittbeliebtestesGerichte = 0.25;
    private double faktorSicherheitsbstand = 1.5;
    
    Canteen(int mitarbeiterAnzahl){
        this.mitarbeiterAnzahl = mitarbeiterAnzahl;
    }
    
    /**
     * @return     Anzahl der Mitarbeiter der Kantine
     */
    public int getMitarbeiterAnzahl(){
        return this.mitarbeiterAnzahl;
    }

    /**
     *
     * Gibt Anzahl an Gerichten für beliebtestes Gericht zurueck
     * 
     * @ return Anzahl für beliebtestes Gericht
     */
    public int berechneAnzahlFuerBeliebtestesGericht() {
        double anzahl = rundeAnzahlAufGanzeZahl(berechneSicherheitsbestand() * anteilBeliebtestesGericht);
        return parseAnzahlZuInteger(anzahl);
    }
    /**
     *
     * Gibt Anzahl an Gerichten für zweitbeliebtestes Gericht zurueck
     * 
     * @return Anzahl für zweitbeliebtestes Gericht
     * 
     */
    public int berechneAnzahlFuerZweitBeliebtestesGericht() {
        double anzahl = rundeAnzahlAufGanzeZahl(berechneSicherheitsbestand() * anteilZweitbeliebtestesGerichte);
        return parseAnzahlZuInteger(anzahl);
    }
    /**
     *
     * Gibt Anzahl der Gerichte für drittbeliebtestes Gericht zurueck
     * 
     * @return Anzahö für drittbeliebtestes Gericht
     */
    public int berechneAnzahlFuerDrittBeliebtestesGericht() {
        double anzahl = rundeAnzahlAufGanzeZahl(berechneSicherheitsbestand() * anteilDrittbeliebtestesGerichte);
        return parseAnzahlZuInteger(anzahl);
    }

    private double berechneSicherheitsbestand() {
        return mitarbeiterAnzahl * faktorSicherheitsbstand;
    }
    
    private int parseAnzahlZuInteger(double ergebnis){
        return Double.valueOf(ergebnis).intValue();
    }

    private double rundeAnzahlAufGanzeZahl(double anzahl) {
        return Math.ceil(anzahl);
    }
}

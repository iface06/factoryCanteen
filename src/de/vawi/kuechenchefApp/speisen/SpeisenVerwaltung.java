package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.PlanungsPeriode;
import de.vawi.kuechenchefApp.entities.Speise;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;
import static de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie.*;
import java.util.*;

/**
 * Enthält alle Funktionen die für die Verwaltung der Speisen benötigt.
 *
 * @author Tatsch
 * @version 29.01.2013
 */
public class SpeisenVerwaltung implements Iterable<Speise> {

    static SpeisenVerwaltung INSTANZ;
    private Set<Speise> speisen = new HashSet<>();
    private PlanungsPeriode planungsperiode = new PlanungsPeriode();

    SpeisenVerwaltung() {
    }

    /**
     * Diese Methode fügt eine Speise dem HashSet speise hinzu.
     *
     * @param speise eine Speise.
     */
    public void addSpeise(Speise speise) {
        speisen.add(speise);
    }

    /**
     * Diese Methode zählt die Anzahl der verfügbaren Speisen.
     *
     * @return Anzahl der verfügbaren Speisen.
     */
    public int size() {
        return speisen.size();
    }

    /**
     * Mit dieser Methode kann eine Speise nach Name gesucht werden.
     *
     * @param speisenName Der Name der Speise.
     * @return gibt die Speise wider, wenn sie gefunden wurde.
     * @throws
     * de.vawi.kuechenchefApp.speisen.SpeisenVerwaltung.SpeiseNichtGefunden Wird
     * geworfen, wenn die Speise nicht gefunden wurde.
     */
    public Speise findeSpeise(String speisenName) throws SpeiseNichtGefunden {
        for (Speise speise : speisen) {
            if (speise.getName().equals(speisenName)) {
                return speise;
            }
        }
        throw new SpeiseNichtGefunden();
    }

    /**
     * Stellt sicher, dass es immer exakt eine Instanz der SpeisenVerwaltung
     * gibt.
     *
     * @return Gibt die Instanz wider.
     */
    public static SpeisenVerwaltung getInstanz() {
        if (INSTANZ == null) {
            INSTANZ = new SpeisenVerwaltung();
        }
        return INSTANZ;
    }

    /**
     * Diese Methode hilft die beliebtestens Speisen zu finden.
     *
     * @param periode Eine Planungsperiode.
     * @return Gibt eine bestimmte Anzahl Speisen wider, die unter den
     * verfügbaren Speisen am beliebtesten sind.
     */
    public List<Speise> findeBeliebtesteSpeisenFuerPlanungsPeriode(PlanungsPeriode periode) {

        List<Speise> beliebtesteSpeisen = new ArrayList<Speise>();

        int anzahlBenötigteSpeisen = periode.berechneAnzahlBenoetigterSpeisen();

        for (Speise speise : speisen) {
            if (speise.getBeliebtheit() <= anzahlBenötigteSpeisen) {
                beliebtesteSpeisen.add(speise);
            }
        }
        return beliebtesteSpeisen;
    }

    void entferne(Speise speise) {
        speisen.remove(speise);
    }

    /**
     * Diese Methode hilft die unbeliebtestens Speisen zu finden.
     *
     * @param periode Eine Planungsperiode.
     * @return Gibt eine bestimmte Anzahl Speisen wider, die unter den
     * verfügbaren Speisen am unbeliebtesten sind.
     */
    public List<Speise> findeUnbeliebtesteSpeisen(PlanungsPeriode periode) {

        List<Speise> unbeliebtesteSpeisen = new ArrayList<Speise>();

        int anzahlBenötigteSpeisen = periode.berechneAnzahlBenoetigterSpeisen();

        for (Speise speise : speisen) {
            if (speise.getBeliebtheit() > anzahlBenötigteSpeisen) {
                unbeliebtesteSpeisen.add(speise);
            }
        }
        return unbeliebtesteSpeisen;
    }

    
    /**
     * Prüft ob genügend Speisen für die Erstellung eines Speiseplans vorhanden ist.
     * Basis dafür ist die Werte aus der PlanungsPeriode-Klasse
     * @return
     */
    public boolean sindAusreichendSpeisenFuerSpeiseplanErstellungVorhanden() {
        if(sindAusreichendSpeisenVorhanden() && sindAusreichendSpeisenFuerKategorie(FISCH, planungsperiode.berechneAnzahlBenoetigterFischSpeisen()) 
                && sindAusreichendSpeisenFuerKategorie(FLEISCH, planungsperiode.berechneAnzahlBenoetigteFleischSpeisen())
                && sindAusreichendSpeisenFuerKategorie(VEGETARISCH, planungsperiode.berechneAnzahlBenoetigteVegetarischeSpeisen())){
            return true;
        } 
        return false;
    }

    private boolean sindAusreichendSpeisenVorhanden() {
        return speisen.size() >= planungsperiode.berechneAnzahlBenoetigterSpeisen();
    }

    private boolean sindAusreichendSpeisenFuerKategorie(SpeisenUndNahrungsmittelKategorie kategorie, int benoetigeAnzahlSpeisen) {
        int anzahlSpeisen = zaehleSpeisenNachKategorie(kategorie);
        return anzahlSpeisen >= benoetigeAnzahlSpeisen;
    }
    
    
    private int zaehleSpeisenNachKategorie(SpeisenUndNahrungsmittelKategorie kategorie){
        int zaehler = 0;
        for (Speise speise : speisen) {
            if(speise.getKategorie().equals(kategorie)){
                zaehler++;
            }
        }
        return zaehler;
    }


    class SpeiseNichtGefunden extends RuntimeException {
    }

    /**
     *
     * @return Gibt ein Iterator-Objekt wider, mit dem die Speisen iteriert
     * werden können.
     */
    @Override
    public Iterator<Speise> iterator() {
        return speisen.iterator();
    }
}

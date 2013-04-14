package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.entities.*;
import de.vawi.kuechenchefApp.speiseplan.Speiseplan;
import de.vawi.kuechenchefApp.speiseplan.Tag;
import java.util.*;


/**
 * Diese Klasse ist eine Hilfsklasse, die anhand eines Speisplans berechnet, wie Nahrungsmittel insgesamt benoetigt werden
 * unter Beruecksichtigung der zugehoerigen Kantine.
 * @author Tatsch
 * @version 29.01.2013
 */
public class ZutatenKalkulator {

    private Map<Nahrungsmittel, Double> mengen = new HashMap<>();

    /**
     * Auf Basis eines Speiseplans werden in dieser Methode für jede angebotene
     * Speise die Anzahl benötigter Gerichte berechnet. Diese Berechnung erfolgt
     * auf Grundlage der Beliebtheit und der jeweiligen Anzahl der Mitarbeiter
     * einer Kantine.
     *
     * @param plan (erstellter Speiseplan)
     * @return benötigte Mengen
     */
    public Map<Nahrungsmittel, Double> berechneGesamtMengen(Speiseplan plan) {
        for (Tag tag : plan) {
            berechneSpeise(tag.getBeliebtesteSpeise(), plan.getKantine().berechneAnzahlFuerBeliebtestesGericht());
            berechneSpeise(tag.getZweitbeliebtesteSpeise(), plan.getKantine().berechneAnzahlFuerZweitBeliebtestesGericht());
            berechneSpeise(tag.getDrittbeliebtesteSpeise(), plan.getKantine().berechneAnzahlFuerDrittBeliebtestesGericht());
        }
        return mengen;
    }

    private void berechneSpeise(Speise speise, int anzahlGerichte) {
        List<Zutat> zutaten = speise.getZutaten();
        for (Zutat zutat : zutaten) {
            Double gesamtMenge = getGesamtMengeFuer(zutat.getNahrungsmittel());
            gesamtMenge += zutat.getMenge() * anzahlGerichte;
            mengen.put(zutat.getNahrungsmittel(), Math.ceil(gesamtMenge));
        }
    }

    private Double getGesamtMengeFuer(Nahrungsmittel nahrungsmittel) {
        // double berechneteMenge = zutat.getMenge();
        // Menge muss noch mit Sicherheitsfaktor multipliziert werden und anschließend gerundet werden
        Double gesamtMenge = mengen.get(nahrungsmittel);
        if (gesamtMenge == null) {
            gesamtMenge = 0.0;
        }
        return gesamtMenge;
    }
}

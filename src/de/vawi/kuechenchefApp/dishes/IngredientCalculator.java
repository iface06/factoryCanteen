package de.vawi.kuechenchefApp.dishes;

import de.vawi.kuechenchefApp.entities.*;
import de.vawi.kuechenchefApp.entities.Menu;
import de.vawi.kuechenchefApp.entities.Day;
import java.util.*;


/**
 * Diese Klasse ist eine Hilfsklasse, die anhand eines Speisplans berechnet, wie Nahrungsmittel insgesamt benoetigt werden
 * unter Beruecksichtigung der zugehoerigen Kantine.
 * @author Tatsch
 * @version 29.01.2013
 */
public class IngredientCalculator {

    private Map<Food, Double> mengen = new HashMap<>();

    /**
     * Auf Basis eines Speiseplans werden in dieser Methode für jede angebotene
     * Speise die Anzahl benötigter Gerichte berechnet. Diese Berechnung erfolgt
     * auf Grundlage der Beliebtheit und der jeweiligen Anzahl der Mitarbeiter
     * einer Kantine.
     *
     * @param plan (erstellter Speiseplan)
     * @return benötigte Mengen
     */
    public Map<Food, Double> berechneGesamtMengen(Menu plan) {
        for (Day tag : plan) {
            berechneSpeise(tag.getBeliebtesteSpeise(), plan.getKantine().berechneAnzahlFuerBeliebtestesGericht());
            berechneSpeise(tag.getZweitbeliebtesteSpeise(), plan.getKantine().berechneAnzahlFuerZweitBeliebtestesGericht());
            berechneSpeise(tag.getDrittbeliebtesteSpeise(), plan.getKantine().berechneAnzahlFuerDrittBeliebtestesGericht());
        }
        return mengen;
    }

    private void berechneSpeise(Dish speise, int anzahlGerichte) {
        List<Ingredient> zutaten = speise.getZutaten();
        for (Ingredient zutat : zutaten) {
            Double gesamtMenge = getGesamtMengeFuer(zutat.getFood());
            gesamtMenge += zutat.getAmount() * anzahlGerichte;
            mengen.put(zutat.getFood(), Math.ceil(gesamtMenge));
        }
    }

    private Double getGesamtMengeFuer(Food nahrungsmittel) {
        // double berechneteMenge = zutat.getMenge();
        // Menge muss noch mit Sicherheitsfaktor multipliziert werden und anschließend gerundet werden
        Double gesamtMenge = mengen.get(nahrungsmittel);
        if (gesamtMenge == null) {
            gesamtMenge = 0.0;
        }
        return gesamtMenge;
    }
}

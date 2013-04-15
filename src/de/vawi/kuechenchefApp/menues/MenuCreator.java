package de.vawi.kuechenchefApp.menues;

import de.vawi.kuechenchefApp.entities.Ingredient;
import de.vawi.kuechenchefApp.Periode;
import de.vawi.kuechenchefApp.entities.*;
import de.vawi.kuechenchefApp.dishes.IngredientCalculator;
import de.vawi.kuechenchefApp.entities.FoodCategory;
import java.util.*;

/**
 * Diese Klasse ist für das Erstellen eines Speiseplans verantwortlich.
 * Hier werden die beliebtesten Speisen auf die Harten Kriterien geprueft und ggf. angepasst.
 * Im Anschluss daran werden diese auf die auf dem Markt vorhandene Verfuegbarkeit geprueft und ggf angepasst.
 * Sollten diese Schritte erfolgreich sein, werden die finalen Plaene erzeugt.
 *
 * @author Beer
 * @version (a version number or a date)
 */
public class MenuCreator {

    private CreateMenuDao speisen;
    private List<Dish> beliebtesteSpeisen;
    private List<Dish> uebrigenSpeisen;
    private List<Dish> speisenFuerEssen;
    private List<Dish> uebrigeSpeiesenEssen;
    private List<Dish> speisenFuerMuehlheim;
    private List<Dish> uebrigeSpeisenMuehlheim;
    private Menu speiseplanEssen;
    private Menu speiseplanMuehlheim;
    private Periode planungsperiode = new Periode();

    /**
     * Stoesst den Pruefungs und Anpassungsprozess an.
     * @return liefert eine Liste mit den erzeugten Plaene 
     */
    public List<Menu> erzeuge() {
        validiereSpeisenAnzahl();
        ladeBeliebtesteSpeisen();
        ladeUnbeliebtesteSpeisen();
        beliebtesteSpeisenPruefenUndAnpassen();
        erstelleSpeiseplaene();
        pruefeVerfuegbarkeit();
        sortiereTageSpeisseplaene();
        return Arrays.asList(speiseplanEssen, speiseplanMuehlheim);
    }

    private void validiereSpeisenAnzahl() {
        if (!sindAusreichendSpeisenInSpeisenVerwaltungVorhanden()) {
            throw new KeineAusreichendeAnzahlAnSpeisen();
        }
    }
    
    private void ladeBeliebtesteSpeisen() {
        beliebtesteSpeisen = findeBeliebtesteSpeisenFuerPlanungsperiode();
    }
    
    /**
     * Findet die beliebtesten Speisen fuer die Planungsperiode
     * @return liefert eine Liste mit den gefundenen Speisen
     */
    protected List<Dish> findeBeliebtesteSpeisenFuerPlanungsperiode() {
        return speisen.findeBeliebtesteSpeisenFuerPlanungsPeriode(planungsperiode);
    }

    private void ladeUnbeliebtesteSpeisen() {
        uebrigenSpeisen = findeUnbeliebtesteSpeisen();
    }
    
    /**
     * Findet die ubeliebtesten Speisen fuer die Planungsperiode
     * @return liefert eine Liste mit den gefundenen Speisen
     */
    protected List<Dish> findeUnbeliebtesteSpeisen() {
        return speisen.findeUnbeliebtesteSpeisen(this.planungsperiode);
    }
    
    /**
     * Diese Methode prueft auf die Kriterien wie viele Fleisch-, Fisch- und vegetarischen Gerichte
     * midestens in einem Plan vorkommen muessen. Sollten diese Kriterien nicht eingehalten sein leitet diese 
     * Methode noetige Anpassungen ein.
     */
    protected void beliebtesteSpeisenPruefenUndAnpassen() {
        if (beliebtesteSpeisenBeinhaltenGenugFischgerichte()) {
            if (beliebtesteSpeisenBeinhaltenGenugVegGerichte()) {
                if (beliebtesteSpeisenBeinhaltenGenugFleischgerichte()) {
                } else {
                    fuegeNeuesGerichtHinzu(FoodCategory.FLEISCH);
                    beliebtesteSpeisenPruefenUndAnpassen();
                }

                //else Zweig für "zu wenig Vegetarische Gerichte in den beliebtesten Speisen"
            } else {
                fuegeNeuesGerichtHinzu(FoodCategory.VEGETARISCH);
                beliebtesteSpeisenPruefenUndAnpassen();
            }
            //else Zweig für "zu wenig Fischgerichte in den beliebtesten Speisen"
        } else {
            fuegeNeuesGerichtHinzu(FoodCategory.FISCH);
            beliebtesteSpeisenPruefenUndAnpassen();
        }
    }

    private void fuegeNeuesGerichtHinzu(FoodCategory speiseKategorie) {
        if (speiseKategorie.equals(FoodCategory.FLEISCH)) {
            Dish unbeliebtestesVegGericht = ermittleUnbeliebtestesGericht(FoodCategory.VEGETARISCH);
            Dish neuesFleischGericht = ermittlebeliebtestesGericht(speiseKategorie);
            beliebtesteSpeisen.add(neuesFleischGericht);
            uebrigenSpeisen.add(unbeliebtestesVegGericht);
            beliebtesteSpeisen.remove(unbeliebtestesVegGericht);
            uebrigenSpeisen.remove(neuesFleischGericht);

        } else {
            Dish unbeliebtestesFleischGericht = ermittleUnbeliebtestesGericht(FoodCategory.FLEISCH);
            Dish neuesGericht = ermittlebeliebtestesGericht(speiseKategorie);
            beliebtesteSpeisen.add(neuesGericht);
            beliebtesteSpeisen.remove(unbeliebtestesFleischGericht);
            uebrigenSpeisen.add(unbeliebtestesFleischGericht);
            uebrigenSpeisen.remove(neuesGericht);
        }

    }

    private int mindestAnzahlBenoetigterFleischgerichte() {
        return planungsperiode.getAnzahlWochen() * planungsperiode.getAnzahlTageProWoche();
    }

    /**
     * Prueft ob die beliebtesten Speisen schon genug Fleischgerichte
     * beinhaltet.
     *
     * @return true falls genuegend Gerichte vorhanden sind, andernfalls false
     */
    private boolean beliebtesteSpeisenBeinhaltenGenugFleischgerichte() {
        int counter = 0;
        for (Dish speise : beliebtesteSpeisen) {
            if (speise.getKategorie().equals(FoodCategory.FLEISCH)) {
                counter++;
            }
        }
        if (counter >= mindestAnzahlBenoetigterFleischgerichte()) {
            return true;
        }
        return false;
    }

    /**
     * Prueft ob die beliebtesten Speisen schon genug Fischgerichte beinhaltet.
     *
     * @return true falls genug vorhanden sind, sonst false
     */
    private boolean beliebtesteSpeisenBeinhaltenGenugFischgerichte() {
        int gezaehlteSpeisen = 0;
        for (Dish speise : beliebtesteSpeisen) {
            if (speise.getKategorie().equals(FoodCategory.FISCH)) {
                gezaehlteSpeisen++;
            }
        }
        if (gezaehlteSpeisen >= mindestAnzahlBenoetigterFischgerichte()) {
            return true;
        }
        return false;
    }

    /**
     * Prueft ob die beliebtesten Speisen schon genug Vegetarischen Gerichte
     * beinhaltet.
     *
     * @return true falls genug vorhanden sind, sonst false
     */
    private boolean beliebtesteSpeisenBeinhaltenGenugVegGerichte() {
        int counter = 0;
        for (Dish speise : beliebtesteSpeisen) {
            if (speise.getKategorie().equals(FoodCategory.VEGETARISCH)) {
                counter++;
            }
        }
        if (counter >= mindestAnzahlBenoetigterVegGerichte()) {
            return true;
        }
        return false;
    }

    private int mindestAnzahlBenoetigterFischgerichte() {
        return planungsperiode.berechneAnzahlBenoetigterFischSpeisen();
    }

    private int mindestAnzahlBenoetigterVegGerichte() {
        return planungsperiode.berechneAnzahlBenoetigteVegetarischeSpeisen();
    }

    private Dish ermittleUnbeliebtestesGericht(FoodCategory kategorie) {
        List<Dish> nachKategorie = extrahiereSpeisenNachKategorie(beliebtesteSpeisen, kategorie);
        sortiereSpeisen(nachKategorie);
        return nachKategorie.get(nachKategorie.size() - 1);
    }

    private Dish ermittlebeliebtestesGericht(FoodCategory speiseKategorie) {
        List<Dish> nachKategorie = extrahiereSpeisenNachKategorie(uebrigenSpeisen, speiseKategorie);
        sortiereSpeisen(nachKategorie);
        return nachKategorie.get(0);
    }

    private void pruefeVerfuegbarkeit() {
        IngredientCalculator kalkulator = new IngredientCalculator();
        Map<Food, Double> mengenEssen = kalkulator.berechneGesamtMengen(speiseplanEssen);
        Map<Food, Double> mengenMuehlheim = kalkulator.berechneGesamtMengen(speiseplanMuehlheim);

        List<Food> problematischeNahrungsmittelEssen = new ArrayList<Food>();
        List<Food> problematischeNahrungsmittelMuehl = new ArrayList<Food>();

        for (Food nahrungsmittel : mengenEssen.keySet()) {

            Double gesamtMenge = mengenEssen.get(nahrungsmittel) + (mengenMuehlheim.containsKey(nahrungsmittel) ? mengenMuehlheim.get(nahrungsmittel) : 0.0);
            if (gesamtMenge > nahrungsmittel.getVerfuegbareGesamtMenge()) {
                if (mengenEssen.get(nahrungsmittel) > nahrungsmittel.getVerfuegbareGesamtMenge()) {
                    problematischeNahrungsmittelEssen.add(nahrungsmittel);
                }
                if ((mengenMuehlheim.containsKey(nahrungsmittel) ? mengenMuehlheim.get(nahrungsmittel) : 0.0) > nahrungsmittel.getVerfuegbareGesamtMenge()) {
                    problematischeNahrungsmittelMuehl.add(nahrungsmittel);
                }
            }
        }

        if (problematischeNahrungsmittelEssen.size() != 0 || problematischeNahrungsmittelMuehl.size() != 0) {
            passeSpeisenDerVerfügbarkeitAn(problematischeNahrungsmittelEssen, problematischeNahrungsmittelMuehl);
        }

    }

    private void passeSpeisenDerVerfügbarkeitAn(List<Food> problematischeNahrungsmittelEssen, List<Food> problematischeNahrungsmittelMuehl) {
        if (!problematischeNahrungsmittelEssen.isEmpty()) {
            passeSpeisenFuerEssenAn(problematischeNahrungsmittelEssen);
        }
        if (!problematischeNahrungsmittelMuehl.isEmpty()) {
            passeSpeisenFuerMuehlaheimAn(problematischeNahrungsmittelMuehl);
        }

        erstelleSpeiseplaene();
        pruefeVerfuegbarkeit();

    }

    private void passeSpeisenFuerEssenAn(List<Food> problematischeNahrungsmittelEssen) {
        List<Dish> problematischeSpeisen = findeSpeisenMitNahrungsmittel(problematischeNahrungsmittelEssen, speisenFuerEssen);
        List<Dish> moeglicheErsatzSpeisen = findeSpeisenOhneNahrungsmittel(problematischeNahrungsmittelEssen, uebrigeSpeiesenEssen);

        ersetzeSpeisen(problematischeSpeisen, moeglicheErsatzSpeisen, speisenFuerEssen, uebrigeSpeiesenEssen);

    }

    private void passeSpeisenFuerMuehlaheimAn(List<Food> problematischeNahrungsmittelMuehl) {
        List<Dish> problematischeSpeisen = findeSpeisenMitNahrungsmittel(problematischeNahrungsmittelMuehl, speisenFuerMuehlheim);
        List<Dish> moeglicheErsatzSpeisen = findeSpeisenOhneNahrungsmittel(problematischeNahrungsmittelMuehl, uebrigeSpeisenMuehlheim);

        ersetzeSpeisen(problematischeSpeisen, moeglicheErsatzSpeisen, speisenFuerMuehlheim, uebrigeSpeisenMuehlheim);
    }

    private List<Dish> findeSpeisenMitNahrungsmittel(List<Food> problematischeNahrungsmittelEssen, List<Dish> speisenFuerEssen) {
        List<Dish> gefundeneSpeisen = new ArrayList<Dish>();

        for (Food nahrungsmittel : problematischeNahrungsmittelEssen) {
            List<Dish> potentielleSpeisen = new ArrayList<Dish>();
            for (Dish speise : speisenFuerEssen) {
                for (Ingredient zutat : speise.getZutaten()) {
                    if (zutat.getNahrungsmittel().equals(nahrungsmittel)) {
                        potentielleSpeisen.add(speise);
                    }
                }
            }
            if (!potentielleSpeisen.isEmpty()) {
                gefundeneSpeisen.add(findeUnbeliebtesteSpeise(potentielleSpeisen));
            }
        }
        return gefundeneSpeisen;
    }

    private List<Dish> findeSpeisenOhneNahrungsmittel(List<Food> problematischeNahrungsmittelEssen, List<Dish> uebrigeSpeiesenEssen) {
        List<Dish> gefundeneSpeisen = new ArrayList<>(uebrigeSpeiesenEssen);

        for (Food nahrungsmittel : problematischeNahrungsmittelEssen) {
            List<Dish> potentielleSpeisen = new ArrayList<>();
            for (Dish speise : uebrigeSpeiesenEssen) {
                for (Ingredient zutat : speise.getZutaten()) {
                    if (zutat.getNahrungsmittel().equals(nahrungsmittel)) {
                        potentielleSpeisen.add(speise);
                    }
                }
            }
            gefundeneSpeisen.removeAll(potentielleSpeisen);
        }
        return gefundeneSpeisen;
    }

    private void ersetzeSpeisen(List<Dish> problematischeSpeisen, List<Dish> moeglicheErsatzSpeisen, List<Dish> speisenFuerEssen, List<Dish> uebrigeSpeiesenEssen) {
        int i = 0;
        sortiereSpeisen(moeglicheErsatzSpeisen);
        for (Dish probSpeise : problematischeSpeisen) {
            speisenFuerEssen.remove(probSpeise);
            uebrigeSpeiesenEssen.add(probSpeise);
            speisenFuerEssen.add(moeglicheErsatzSpeisen.get(i));
            i++;
        }
    }

    private void erstelleSpeiseplaene() {
        if (speisenFuerEssen == null || speisenFuerEssen.isEmpty()) {
            speisenFuerEssen = new ArrayList<>(beliebtesteSpeisen);
            uebrigeSpeiesenEssen = new ArrayList<>(uebrigenSpeisen);
        }

        if (speisenFuerMuehlheim == null || speisenFuerMuehlheim.isEmpty()) {
            speisenFuerMuehlheim = new ArrayList<>(beliebtesteSpeisen);
            uebrigeSpeisenMuehlheim = new ArrayList<>(uebrigenSpeisen);
        }

        speiseplanEssen = erstelleSpeiseplan(Canteen.ESSEN);
        speiseplanMuehlheim = erstelleSpeiseplan(Canteen.MUELHEIM_AN_DER_RUHR);
    }

    private Menu erstelleSpeiseplan(Canteen kantine) {
        List<Dish> speisenFuerPlan;
        if (kantine.equals(Canteen.ESSEN)) {
            speisenFuerPlan = new ArrayList<Dish>(speisenFuerEssen);
        } else {
            speisenFuerPlan = new ArrayList<Dish>(speisenFuerMuehlheim);
        }
        List<Day> tage = new ArrayList<Day>();

        //zuerst müssen die Fischtage erstellt werden!!
        for (int i = 1; i <= planungsperiode.getAnzahlWochen(); i++) {
            tage.add(erstelleFischTag(speisenFuerPlan, i * 5));
        }

        //Dann die "normalen" Tage
        for (int i = 1; i <= planungsperiode.getAnzahlWochen() * planungsperiode.getAnzahlTageProWoche(); i++) {
            if ((i % 5) != 0) {
                tage.add(erstelleNormalenTag(speisenFuerPlan, i));
            }
        }
        return new Menu(kantine, tage);
    }

    private Day erstelleNormalenTag(List<Dish> speisenFuerPlan, int nummer) {
        Day tag = new Day(nummer);
        List<Dish> tagesSpeisen = new ArrayList<Dish>();
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, FoodCategory.VEGETARISCH));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, FoodCategory.FLEISCH));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, FoodCategory.FLEISCH));

        return verteileSpeisenAufTag(tag, tagesSpeisen);
    }

    private Day erstelleFischTag(List<Dish> speisenFuerPlan, int nummer) {
        Day tag = new Day(nummer);
        List<Dish> tagesSpeisen = new ArrayList<Dish>();
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, FoodCategory.FISCH));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, FoodCategory.VEGETARISCH));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, FoodCategory.FLEISCH));

        return verteileSpeisenAufTag(tag, tagesSpeisen);
    }

    private Day verteileSpeisenAufTag(Day tag, List<Dish> tagesSpeisen) {
        Dish speise1 = findeBeliebtesteSpeise(tagesSpeisen);
        if (speise1.getName() == null) {
            int test = 1;
        }
        tag.setBeliebtesteSpeise(speise1);
        tagesSpeisen.remove(speise1);
        tag.setZweitbeliebtesteSpeise(findeBeliebtesteSpeise(tagesSpeisen));
        tagesSpeisen.remove(findeBeliebtesteSpeise(tagesSpeisen));
        tag.setDrittbeliebtesteSpeise(findeBeliebtesteSpeise(tagesSpeisen));

        return tag;
    }

    private Dish findeBeliebtesteSpeise(List<Dish> speisen) {
        sortiereSpeisen(speisen);
        return speisen.get(0);
    }

    private Dish findeUnbeliebtesteSpeise(List<Dish> speisen) {
        sortiereSpeisen(speisen);
        return speisen.get(speisen.size() - 1);
    }

    private Dish nimmEinGerichtAusListe(List<Dish> speisen, FoodCategory kategorie) {
        List<Dish> gefundenesGericht = extrahiereSpeisenNachKategorie(speisen, kategorie);
        if (!gefundenesGericht.isEmpty()) {
            speisen.remove(gefundenesGericht.get(0));
            return gefundenesGericht.get(0);
        } else {
            List<Dish> listeMitMehrSpeisen = new ArrayList<>();
            List<FoodCategory> andereKategorien = FoodCategory.holeAndereKategorien(kategorie);
            for (FoodCategory andereKategorie : andereKategorien) {
                List<Dish> andereSpeisen = extrahiereSpeisenNachKategorie(speisen, andereKategorie);
                if (listeMitMehrSpeisen.size() <= andereSpeisen.size()) {
                    listeMitMehrSpeisen = andereSpeisen;
                }
            }
            speisen.remove(listeMitMehrSpeisen.get(0));
            return listeMitMehrSpeisen.get(0);
        }

    }

    private void sortiereSpeisen(List<Dish> speisen) {
        Collections.sort(speisen, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.valueOf(o1.getBeliebtheit()).compareTo(Integer.valueOf(o2.getBeliebtheit()));
            }
        });
    }

    private List<Dish> extrahiereSpeisenNachKategorie(List<Dish> speisen, FoodCategory kategorie) {
        List<Dish> nachKategorie = new ArrayList<>();
        for (Dish speise : speisen) {
            if (speise.getKategorie().equals(kategorie)) {
                nachKategorie.add(speise);
            }
        }
        return nachKategorie;
    }
    
    /**
     * Setzt die PlaungsPeriode
     * @param planungsperiode die entsprechende PlanungsPeriode, die gesetzt werden soll
     */
    protected void setPlanungsperiode(Periode planungsperiode) {
        this.planungsperiode = planungsperiode;
    }
    
    /**
     * Prueft ob grundsaetzlich genug Speisen vorhanden sind um einen Plan zu erstellen
     * @return liefert true wenn genug Speisen vorhanden sind, andernfalls false
     */
    protected boolean sindAusreichendSpeisenInSpeisenVerwaltungVorhanden() {
        return speisen.sindAusreichendSpeisenFuerSpeiseplanErstellungVorhanden();
    }

    private void sortiereTageSpeisseplaene() {
        Comparator<Day> c = new Comparator<Day>() {
            @Override
            public int compare(Day o1, Day o2) {
                return Integer.valueOf(o1.getNummer()).compareTo(Integer.valueOf(o2.getNummer()));
            }
        };
        Collections.sort(speiseplanEssen.getTageMitGerichten(), c);
        Collections.sort(speiseplanMuehlheim.getTageMitGerichten(), c);
    }
    
    /**
     * Die Exception die geworfen wird, sollten zu wenig Speisen zum Erstellen eines Planes vorhanden sind.s
     */
    public static class KeineAusreichendeAnzahlAnSpeisen extends RuntimeException {
    }
}

package de.vawi.kuechenchefApp.createMenues;

import de.vawi.kuechenchefApp.entities.Menu;
import de.vawi.kuechenchefApp.entities.Day;
import de.vawi.kuechenchefApp.entities.Canteen;
import de.vawi.kuechenchefApp.Periode;
import de.vawi.kuechenchefApp.entities.*;
import java.util.*;

/**
 * Diese Klasse ist für das Erstellen eines Speiseplans verantwortlich.
 * Hier werden die beliebtesten Speisen auf die Harten Kriterien geprueft und ggf. angepasst.
 *
 * @author Beer
 * @version (a version number or a date)
 */
public class MenusCreator {

    private CreateMenuDao dao;
    private List<Dish> beliebtesteSpeisen;
    private List<Dish> uebrigenSpeisen;
    private List<Dish> speisenFuerEssen;
    private List<Dish> speisenFuerMuehlheim;
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
        sortiereTageSpeisseplaene();
        return Arrays.asList(speiseplanEssen, speiseplanMuehlheim);
    }
    
    /**
     * setzt das benötigte Data Access Object das für die erzeugen der Speisepläne benötigt wird.
     */
    protected void setDao(CreateMenuDao dao) {
        this.dao = dao;
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
        return dao.findeBeliebtesteSpeisenFuerPlanungsPeriode(planungsperiode);
    }

    private void ladeUnbeliebtesteSpeisen() {
        uebrigenSpeisen = findeUnbeliebtesteSpeisen();
    }
    
    /**
     * Findet die ubeliebtesten Speisen fuer die Planungsperiode
     * @return liefert eine Liste mit den gefundenen Speisen
     */
    protected List<Dish> findeUnbeliebtesteSpeisen() {
        return dao.findeUnbeliebtesteSpeisen(this.planungsperiode);
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
                    fuegeNeuesGerichtHinzu(FoodCategory.MEAT);
                    beliebtesteSpeisenPruefenUndAnpassen();
                }

                //else Zweig für "zu wenig Vegetarische Gerichte in den beliebtesten Speisen"
            } else {
                fuegeNeuesGerichtHinzu(FoodCategory.VEGETARIAN);
                beliebtesteSpeisenPruefenUndAnpassen();
            }
            //else Zweig für "zu wenig Fischgerichte in den beliebtesten Speisen"
        } else {
            fuegeNeuesGerichtHinzu(FoodCategory.FISH);
            beliebtesteSpeisenPruefenUndAnpassen();
        }
    }

    private void fuegeNeuesGerichtHinzu(FoodCategory speiseKategorie) {
        if (speiseKategorie.equals(FoodCategory.MEAT)) {
            Dish unbeliebtestesVegGericht = ermittleUnbeliebtestesGericht(FoodCategory.VEGETARIAN);
            Dish neuesFleischGericht = ermittlebeliebtestesGericht(speiseKategorie);
            beliebtesteSpeisen.add(neuesFleischGericht);
            uebrigenSpeisen.add(unbeliebtestesVegGericht);
            beliebtesteSpeisen.remove(unbeliebtestesVegGericht);
            uebrigenSpeisen.remove(neuesFleischGericht);

        } else {
            Dish unbeliebtestesFleischGericht = ermittleUnbeliebtestesGericht(FoodCategory.MEAT);
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
            if (speise.getKategorie().equals(FoodCategory.MEAT)) {
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
            if (speise.getKategorie().equals(FoodCategory.FISH)) {
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
            if (speise.getKategorie().equals(FoodCategory.VEGETARIAN)) {
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

    private void erstelleSpeiseplaene() {
        if (speisenFuerEssen == null || speisenFuerEssen.isEmpty()) {
            speisenFuerEssen = new ArrayList<>(beliebtesteSpeisen);
        }

        if (speisenFuerMuehlheim == null || speisenFuerMuehlheim.isEmpty()) {
            speisenFuerMuehlheim = new ArrayList<>(beliebtesteSpeisen);
        }

        speiseplanEssen = erstelleSpeiseplan(Canteen.ESSEN);
        speiseplanMuehlheim = erstelleSpeiseplan(Canteen.MUELHEIM_AN_DER_RUHR);
    }

    private Menu erstelleSpeiseplan(Canteen kantine) {
        Menu menu = new Menu();
        menu.setKantine(kantine);
        menu.setCalendarWeek(CalendarWeek.current());
        List<Dish> speisenFuerPlan;
        if (kantine.equals(Canteen.ESSEN)) {
            speisenFuerPlan = new ArrayList<>(speisenFuerEssen);
        } else {
            speisenFuerPlan = new ArrayList<>(speisenFuerMuehlheim);
        }
        

        //zuerst müssen die Fischtage erstellt werden!!
        for (int i = 1; i <= planungsperiode.getAnzahlWochen(); i++) {
            menu.addDay(erstelleFischTag(speisenFuerPlan, i * 5));
        }

        //Dann die "normalen" Tage
        for (int i = 1; i <= planungsperiode.getAnzahlWochen() * planungsperiode.getAnzahlTageProWoche(); i++) {
            if ((i % 5) != 0) {
                menu.addDay(erstelleNormalenTag(speisenFuerPlan, i));
            }
        }
        
        return menu;
    }

    private Day erstelleNormalenTag(List<Dish> speisenFuerPlan, int nummer) {
        Day tag = new Day(nummer);
        List<Dish> tagesSpeisen = new ArrayList<>();
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, FoodCategory.VEGETARIAN));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, FoodCategory.MEAT));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, FoodCategory.MEAT));

        return verteileSpeisenAufTag(tag, tagesSpeisen);
    }

    private Day erstelleFischTag(List<Dish> speisenFuerPlan, int nummer) {
        Day tag = new Day(nummer);
        List<Dish> tagesSpeisen = new ArrayList<>();
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, FoodCategory.FISH));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, FoodCategory.VEGETARIAN));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, FoodCategory.MEAT));

        return verteileSpeisenAufTag(tag, tagesSpeisen);
    }

    private Day verteileSpeisenAufTag(Day tag, List<Dish> tagesSpeisen) {
        Dish speise1 = findeBeliebtesteSpeise(tagesSpeisen);
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
            List<FoodCategory> andereKategorien = FoodCategory.getOtherOwnTo(kategorie);
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
        return dao.sindAusreichendSpeisenFuerSpeiseplanErstellungVorhanden();
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

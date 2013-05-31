package de.vawi.kuechenchefApp.createMenu;

import de.vawi.kuechenchefApp.entities.Menu;
import de.vawi.kuechenchefApp.entities.Day;
import de.vawi.kuechenchefApp.entities.Canteen;
import de.vawi.kuechenchefApp.entities.Periode;
import de.vawi.kuechenchefApp.entities.*;
import java.util.*;

/**
 * Diese Klasse ist für das Erstellen eines Speiseplans verantwortlich. Hier
 * werden die beliebtesten Speisen auf die Harten Kriterien geprueft und ggf.
 * angepasst.
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
     *
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
     * setzt das benötigte Data Access Object das für die erzeugen der
     * Speisepläne benötigt wird.
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
     *
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
     *
     * @return liefert eine Liste mit den gefundenen Speisen
     */
    protected List<Dish> findeUnbeliebtesteSpeisen() {
        return dao.findeUnbeliebtesteSpeisen(this.planungsperiode);
    }

    /**
     * Diese Methode prueft auf die Kriterien wie viele Fleisch-, Fisch- und
     * vegetarischen Gerichte midestens in einem Plan vorkommen muessen. Sollten
     * diese Kriterien nicht eingehalten sein leitet diese Methode noetige
     * Anpassungen ein.
     */
    protected void beliebtesteSpeisenPruefenUndAnpassen() {
        if (beliebtesteSpeisenBeinhaltenGenugFischgerichte()) {
            if (beliebtesteSpeisenBeinhaltenGenugVegGerichte()) {
                if (beliebtesteSpeisenBeinhaltenGenugFleischgerichte()) {
                } else {
                    fuegeNeuesGerichtHinzu(DishCategory.MEAT);
                    beliebtesteSpeisenPruefenUndAnpassen();
                }

                //else Zweig für "zu wenig Vegetarische Gerichte in den beliebtesten Speisen"
            } else {
                fuegeNeuesGerichtHinzu(DishCategory.VEGETARIAN);
                beliebtesteSpeisenPruefenUndAnpassen();
            }
            //else Zweig für "zu wenig Fischgerichte in den beliebtesten Speisen"
        } else {
            fuegeNeuesGerichtHinzu(DishCategory.FISH);
            beliebtesteSpeisenPruefenUndAnpassen();
        }
    }

    private void fuegeNeuesGerichtHinzu(DishCategory speiseKategorie) {
        if (speiseKategorie.equals(DishCategory.MEAT)) {
            Dish unbeliebtestesVegGericht = ermittleUnbeliebtestesGericht(DishCategory.VEGETARIAN);
            Dish neuesFleischGericht = ermittlebeliebtestesGericht(speiseKategorie);
            beliebtesteSpeisen.add(neuesFleischGericht);
            uebrigenSpeisen.add(unbeliebtestesVegGericht);
            beliebtesteSpeisen.remove(unbeliebtestesVegGericht);
            uebrigenSpeisen.remove(neuesFleischGericht);

        } else {
            Dish unbeliebtestesFleischGericht = ermittleUnbeliebtestesGericht(DishCategory.MEAT);
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
            if (speise.getCategory().equals(DishCategory.MEAT)) {
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
            if (speise.getCategory().equals(DishCategory.FISH)) {
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
            if (speise.getCategory().equals(DishCategory.VEGETARIAN)) {
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

    private Dish ermittleUnbeliebtestesGericht(DishCategory kategorie) {
        List<Dish> nachKategorie = extrahiereSpeisenNachKategorie(beliebtesteSpeisen, kategorie);
        sortiereSpeisen(nachKategorie);
        return nachKategorie.get(nachKategorie.size() - 1);
    }

    private Dish ermittlebeliebtestesGericht(DishCategory speiseKategorie) {
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
        Day tag = new Day(new Date());
        List<Dish> tagesSpeisen = new ArrayList<>();
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, DishCategory.VEGETARIAN));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, DishCategory.MEAT));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, DishCategory.MEAT));

        return verteileSpeisenAufTag(tag, tagesSpeisen);
    }

    private Day erstelleFischTag(List<Dish> speisenFuerPlan, int nummer) {
        Day tag = new Day(new Date());
        List<Dish> tagesSpeisen = new ArrayList<>();
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, DishCategory.FISH));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, DishCategory.VEGETARIAN));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, DishCategory.MEAT));

        return verteileSpeisenAufTag(tag, tagesSpeisen);
    }

    private Day verteileSpeisenAufTag(Day tag, List<Dish> tagesSpeisen) {
        Dish speise1 = findeBeliebtesteSpeise(tagesSpeisen);
        tag.insert(speise1);
        tagesSpeisen.remove(speise1);
        tag.insert(findeBeliebtesteSpeise(tagesSpeisen));
        tagesSpeisen.remove(findeBeliebtesteSpeise(tagesSpeisen));
        tag.insert(findeBeliebtesteSpeise(tagesSpeisen));

        return tag;
    }

    private Dish findeBeliebtesteSpeise(List<Dish> speisen) {
        sortiereSpeisen(speisen);
        return speisen.get(0);
    }

    private Dish nimmEinGerichtAusListe(List<Dish> speisen, DishCategory kategorie) {
        List<Dish> gefundenesGericht = extrahiereSpeisenNachKategorie(speisen, kategorie);
        if (!gefundenesGericht.isEmpty()) {
            speisen.remove(gefundenesGericht.get(0));
            return gefundenesGericht.get(0);
        } else {
            List<Dish> listeMitMehrSpeisen = new ArrayList<>();
            List<DishCategory> andereKategorien = DishCategory.getOtherOwnTo(kategorie);
            for (DishCategory andereKategorie : andereKategorien) {
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
                return Integer.valueOf(o1.getPopularity()).compareTo(Integer.valueOf(o2.getPopularity()));
            }
        });
    }

    private List<Dish> extrahiereSpeisenNachKategorie(List<Dish> speisen, DishCategory kategorie) {
        List<Dish> nachKategorie = new ArrayList<>();
        for (Dish speise : speisen) {
            if (speise.getCategory().equals(kategorie)) {
                nachKategorie.add(speise);
            }
        }
        return nachKategorie;
    }

    /**
     * Setzt die PlaungsPeriode
     *
     * @param planungsperiode die entsprechende PlanungsPeriode, die gesetzt
     * werden soll
     */
    protected void setPlanungsperiode(Periode planungsperiode) {
        this.planungsperiode = planungsperiode;
    }

    /**
     * Prueft ob grundsaetzlich genug Speisen vorhanden sind um einen Plan zu
     * erstellen
     *
     * @return liefert true wenn genug Speisen vorhanden sind, andernfalls false
     */
    protected boolean sindAusreichendSpeisenInSpeisenVerwaltungVorhanden() {
        return dao.areEnoughtDishesAvailable();
    }

    private void sortiereTageSpeisseplaene() {
        Collections.sort(speiseplanEssen.getTageMitGerichten());
        Collections.sort(speiseplanMuehlheim.getTageMitGerichten());
    }

    /**
     * Die Exception die geworfen wird, sollten zu wenig Speisen zum Erstellen
     * eines Planes vorhanden sind.s
     */
    public static class KeineAusreichendeAnzahlAnSpeisen extends RuntimeException {
    }
}

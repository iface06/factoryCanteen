package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.entities.Zutat;
import de.vawi.kuechenchefApp.PlanungsPeriode;
import de.vawi.kuechenchefApp.entities.*;
import de.vawi.kuechenchefApp.speisen.ZutatenKalkulator;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;
import de.vawi.kuechenchefApp.speisen.*;
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
public class SpeiseplanErsteller {

    private SpeisenVerwaltung speisen = SpeisenVerwaltung.getInstanz();
    private List<Speise> beliebtesteSpeisen;
    private List<Speise> uebrigenSpeisen;
    private List<Speise> speisenFuerEssen;
    private List<Speise> uebrigeSpeiesenEssen;
    private List<Speise> speisenFuerMuehlheim;
    private List<Speise> uebrigeSpeisenMuehlheim;
    private Menu speiseplanEssen;
    private Menu speiseplanMuehlheim;
    private PlanungsPeriode planungsperiode = new PlanungsPeriode();

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
    protected List<Speise> findeBeliebtesteSpeisenFuerPlanungsperiode() {
        return speisen.findeBeliebtesteSpeisenFuerPlanungsPeriode(planungsperiode);
    }

    private void ladeUnbeliebtesteSpeisen() {
        uebrigenSpeisen = findeUnbeliebtesteSpeisen();
    }
    
    /**
     * Findet die ubeliebtesten Speisen fuer die Planungsperiode
     * @return liefert eine Liste mit den gefundenen Speisen
     */
    protected List<Speise> findeUnbeliebtesteSpeisen() {
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
                    fuegeNeuesGerichtHinzu(SpeisenUndNahrungsmittelKategorie.FLEISCH);
                    beliebtesteSpeisenPruefenUndAnpassen();
                }

                //else Zweig für "zu wenig Vegetarische Gerichte in den beliebtesten Speisen"
            } else {
                fuegeNeuesGerichtHinzu(SpeisenUndNahrungsmittelKategorie.VEGETARISCH);
                beliebtesteSpeisenPruefenUndAnpassen();
            }
            //else Zweig für "zu wenig Fischgerichte in den beliebtesten Speisen"
        } else {
            fuegeNeuesGerichtHinzu(SpeisenUndNahrungsmittelKategorie.FISCH);
            beliebtesteSpeisenPruefenUndAnpassen();
        }
    }

    private void fuegeNeuesGerichtHinzu(SpeisenUndNahrungsmittelKategorie speiseKategorie) {
        if (speiseKategorie.equals(SpeisenUndNahrungsmittelKategorie.FLEISCH)) {
            Speise unbeliebtestesVegGericht = ermittleUnbeliebtestesGericht(SpeisenUndNahrungsmittelKategorie.VEGETARISCH);
            Speise neuesFleischGericht = ermittlebeliebtestesGericht(speiseKategorie);
            beliebtesteSpeisen.add(neuesFleischGericht);
            uebrigenSpeisen.add(unbeliebtestesVegGericht);
            beliebtesteSpeisen.remove(unbeliebtestesVegGericht);
            uebrigenSpeisen.remove(neuesFleischGericht);

        } else {
            Speise unbeliebtestesFleischGericht = ermittleUnbeliebtestesGericht(SpeisenUndNahrungsmittelKategorie.FLEISCH);
            Speise neuesGericht = ermittlebeliebtestesGericht(speiseKategorie);
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
        for (Speise speise : beliebtesteSpeisen) {
            if (speise.getKategorie().equals(SpeisenUndNahrungsmittelKategorie.FLEISCH)) {
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
        for (Speise speise : beliebtesteSpeisen) {
            if (speise.getKategorie().equals(SpeisenUndNahrungsmittelKategorie.FISCH)) {
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
        for (Speise speise : beliebtesteSpeisen) {
            if (speise.getKategorie().equals(SpeisenUndNahrungsmittelKategorie.VEGETARISCH)) {
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

    private Speise ermittleUnbeliebtestesGericht(SpeisenUndNahrungsmittelKategorie kategorie) {
        List<Speise> nachKategorie = extrahiereSpeisenNachKategorie(beliebtesteSpeisen, kategorie);
        sortiereSpeisen(nachKategorie);
        return nachKategorie.get(nachKategorie.size() - 1);
    }

    private Speise ermittlebeliebtestesGericht(SpeisenUndNahrungsmittelKategorie speiseKategorie) {
        List<Speise> nachKategorie = extrahiereSpeisenNachKategorie(uebrigenSpeisen, speiseKategorie);
        sortiereSpeisen(nachKategorie);
        return nachKategorie.get(0);
    }

    private void pruefeVerfuegbarkeit() {
        ZutatenKalkulator kalkulator = new ZutatenKalkulator();
        Map<Nahrungsmittel, Double> mengenEssen = kalkulator.berechneGesamtMengen(speiseplanEssen);
        Map<Nahrungsmittel, Double> mengenMuehlheim = kalkulator.berechneGesamtMengen(speiseplanMuehlheim);

        List<Nahrungsmittel> problematischeNahrungsmittelEssen = new ArrayList<Nahrungsmittel>();
        List<Nahrungsmittel> problematischeNahrungsmittelMuehl = new ArrayList<Nahrungsmittel>();

        for (Nahrungsmittel nahrungsmittel : mengenEssen.keySet()) {

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

    private void passeSpeisenDerVerfügbarkeitAn(List<Nahrungsmittel> problematischeNahrungsmittelEssen, List<Nahrungsmittel> problematischeNahrungsmittelMuehl) {
        if (!problematischeNahrungsmittelEssen.isEmpty()) {
            passeSpeisenFuerEssenAn(problematischeNahrungsmittelEssen);
        }
        if (!problematischeNahrungsmittelMuehl.isEmpty()) {
            passeSpeisenFuerMuehlaheimAn(problematischeNahrungsmittelMuehl);
        }

        erstelleSpeiseplaene();
        pruefeVerfuegbarkeit();

    }

    private void passeSpeisenFuerEssenAn(List<Nahrungsmittel> problematischeNahrungsmittelEssen) {
        List<Speise> problematischeSpeisen = findeSpeisenMitNahrungsmittel(problematischeNahrungsmittelEssen, speisenFuerEssen);
        List<Speise> moeglicheErsatzSpeisen = findeSpeisenOhneNahrungsmittel(problematischeNahrungsmittelEssen, uebrigeSpeiesenEssen);

        ersetzeSpeisen(problematischeSpeisen, moeglicheErsatzSpeisen, speisenFuerEssen, uebrigeSpeiesenEssen);

    }

    private void passeSpeisenFuerMuehlaheimAn(List<Nahrungsmittel> problematischeNahrungsmittelMuehl) {
        List<Speise> problematischeSpeisen = findeSpeisenMitNahrungsmittel(problematischeNahrungsmittelMuehl, speisenFuerMuehlheim);
        List<Speise> moeglicheErsatzSpeisen = findeSpeisenOhneNahrungsmittel(problematischeNahrungsmittelMuehl, uebrigeSpeisenMuehlheim);

        ersetzeSpeisen(problematischeSpeisen, moeglicheErsatzSpeisen, speisenFuerMuehlheim, uebrigeSpeisenMuehlheim);
    }

    private List<Speise> findeSpeisenMitNahrungsmittel(List<Nahrungsmittel> problematischeNahrungsmittelEssen, List<Speise> speisenFuerEssen) {
        List<Speise> gefundeneSpeisen = new ArrayList<Speise>();

        for (Nahrungsmittel nahrungsmittel : problematischeNahrungsmittelEssen) {
            List<Speise> potentielleSpeisen = new ArrayList<Speise>();
            for (Speise speise : speisenFuerEssen) {
                for (Zutat zutat : speise.getZutaten()) {
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

    private List<Speise> findeSpeisenOhneNahrungsmittel(List<Nahrungsmittel> problematischeNahrungsmittelEssen, List<Speise> uebrigeSpeiesenEssen) {
        List<Speise> gefundeneSpeisen = new ArrayList<>(uebrigeSpeiesenEssen);

        for (Nahrungsmittel nahrungsmittel : problematischeNahrungsmittelEssen) {
            List<Speise> potentielleSpeisen = new ArrayList<>();
            for (Speise speise : uebrigeSpeiesenEssen) {
                for (Zutat zutat : speise.getZutaten()) {
                    if (zutat.getNahrungsmittel().equals(nahrungsmittel)) {
                        potentielleSpeisen.add(speise);
                    }
                }
            }
            gefundeneSpeisen.removeAll(potentielleSpeisen);
        }
        return gefundeneSpeisen;
    }

    private void ersetzeSpeisen(List<Speise> problematischeSpeisen, List<Speise> moeglicheErsatzSpeisen, List<Speise> speisenFuerEssen, List<Speise> uebrigeSpeiesenEssen) {
        int i = 0;
        sortiereSpeisen(moeglicheErsatzSpeisen);
        for (Speise probSpeise : problematischeSpeisen) {
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

        speiseplanEssen = erstelleSpeiseplan(Kantine.ESSEN);
        speiseplanMuehlheim = erstelleSpeiseplan(Kantine.MUELHEIM_AN_DER_RUHR);
    }

    private Menu erstelleSpeiseplan(Kantine kantine) {
        List<Speise> speisenFuerPlan;
        if (kantine.equals(Kantine.ESSEN)) {
            speisenFuerPlan = new ArrayList<Speise>(speisenFuerEssen);
        } else {
            speisenFuerPlan = new ArrayList<Speise>(speisenFuerMuehlheim);
        }
        List<Tag> tage = new ArrayList<Tag>();

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

    private Tag erstelleNormalenTag(List<Speise> speisenFuerPlan, int nummer) {
        Tag tag = new Tag(nummer);
        List<Speise> tagesSpeisen = new ArrayList<Speise>();
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, SpeisenUndNahrungsmittelKategorie.FLEISCH));

        return verteileSpeisenAufTag(tag, tagesSpeisen);
    }

    private Tag erstelleFischTag(List<Speise> speisenFuerPlan, int nummer) {
        Tag tag = new Tag(nummer);
        List<Speise> tagesSpeisen = new ArrayList<Speise>();
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, SpeisenUndNahrungsmittelKategorie.FISCH));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, SpeisenUndNahrungsmittelKategorie.FLEISCH));

        return verteileSpeisenAufTag(tag, tagesSpeisen);
    }

    private Tag verteileSpeisenAufTag(Tag tag, List<Speise> tagesSpeisen) {
        Speise speise1 = findeBeliebtesteSpeise(tagesSpeisen);
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

    private Speise findeBeliebtesteSpeise(List<Speise> speisen) {
        sortiereSpeisen(speisen);
        return speisen.get(0);
    }

    private Speise findeUnbeliebtesteSpeise(List<Speise> speisen) {
        sortiereSpeisen(speisen);
        return speisen.get(speisen.size() - 1);
    }

    private Speise nimmEinGerichtAusListe(List<Speise> speisen, SpeisenUndNahrungsmittelKategorie kategorie) {
        List<Speise> gefundenesGericht = extrahiereSpeisenNachKategorie(speisen, kategorie);
        if (!gefundenesGericht.isEmpty()) {
            speisen.remove(gefundenesGericht.get(0));
            return gefundenesGericht.get(0);
        } else {
            List<Speise> listeMitMehrSpeisen = new ArrayList<>();
            List<SpeisenUndNahrungsmittelKategorie> andereKategorien = SpeisenUndNahrungsmittelKategorie.holeAndereKategorien(kategorie);
            for (SpeisenUndNahrungsmittelKategorie andereKategorie : andereKategorien) {
                List<Speise> andereSpeisen = extrahiereSpeisenNachKategorie(speisen, andereKategorie);
                if (listeMitMehrSpeisen.size() <= andereSpeisen.size()) {
                    listeMitMehrSpeisen = andereSpeisen;
                }
            }
            speisen.remove(listeMitMehrSpeisen.get(0));
            return listeMitMehrSpeisen.get(0);
        }

    }

    private void sortiereSpeisen(List<Speise> speisen) {
        Collections.sort(speisen, new Comparator<Speise>() {
            @Override
            public int compare(Speise o1, Speise o2) {
                return Integer.valueOf(o1.getBeliebtheit()).compareTo(Integer.valueOf(o2.getBeliebtheit()));
            }
        });
    }

    private List<Speise> extrahiereSpeisenNachKategorie(List<Speise> speisen, SpeisenUndNahrungsmittelKategorie kategorie) {
        List<Speise> nachKategorie = new ArrayList<>();
        for (Speise speise : speisen) {
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
    protected void setPlanungsperiode(PlanungsPeriode planungsperiode) {
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
        Comparator<Tag> c = new Comparator<Tag>() {
            @Override
            public int compare(Tag o1, Tag o2) {
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

package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.entities.*;
import de.vawi.kuechenchefApp.speisen.ZutatenKalkulator;
import de.vawi.kuechenchefApp.lieferanten.*;
import de.vawi.kuechenchefApp.speiseplan.Menu;
import java.util.*;

/**
 * Erstellt eine Einkaufsliste auf Basis der hinzugefügten Speisepläne.
 *
 * @author Lepping
 * @version 29.01.2013
 */
public class EinkaufslistenErsteller {

    private Einkaufsliste liste = new Einkaufsliste();
    private List<Menu> speiseplaene = new ArrayList<>();
    private LieferantenVerwaltung lieferanten = LieferantenVerwaltung.getInstanz();
    private List<EinkaufslistenPosition> zusaetzlichePositionen = new ArrayList<>();

    /**
     * Hinzufügen eines Speiseplans, der zur Erzeugung der Einkaufsliste
     * berücksichtigt werden soll.
     *
     * @param plan Speiseplan
     */
    public void add(Menu plan) {
        this.speiseplaene.add(plan);
    }

    /**
     * Erzeugt eine Einkaufsliste anhand der hinzugefügten Speisepläne, nach
     * folgdenden Regeln:
     *
     * 1. Erstellt Einkaufslistenpositionen für jedes Nahrungsmittel aus
     * Speiseplänen 2. Sucht günstigste Lieferanten für Einkaufslistenpositionen
     * 3. Optimiert Einkaufsliste hinsichtlich der Lieferkosten
     *
     *
     * @return Einkaufsliste für Speisepläne
     */
    public Einkaufsliste erzeuge() {
        erstelleEinkaufslistePosition();
        fuegeGünstigsteLieferantenInEinkaufslisteEin();
        optimiereEinkaufslisteHinsichtlichLieferkosten();

        return liste;
    }

    private void erstelleEinkaufslistePosition() {
        for (Menu speiseplan : speiseplaene) {
            Map<Nahrungsmittel, Double> mengen = new ZutatenKalkulator().berechneGesamtMengen(speiseplan);
            fuegeMengenInEinkaufslisteEin(mengen);
        }
    }

    private void fuegeGünstigsteLieferantenInEinkaufslisteEin() {
        for (EinkaufslistenPosition position : liste) {
            findeGuenstigestenLieferantFuer(position);
        }

        for (EinkaufslistenPosition einkaufslistenPosition : zusaetzlichePositionen) {
            liste.hinzufügenEinkaufslistenPosition(einkaufslistenPosition);
        }

    }

    private void findeGuenstigestenLieferantFuer(EinkaufslistenPosition position) {
        List<PreisListenPosition> angebote = findeAngeboteZuNahrungsmittel(position);
        // benoetigte Menge wird zwischengepeichert und auf 0 gesetzt, sobald die benoetigte Menge bestellt ist
        double benoetigteMenge = position.getMenge();
        double bestellteAnzahlGebinde;
        //Positionsnummer in der Angebotsliste, wird hochgezählt
        int positionsNummer = 0;
        // laufe bis benotigteMenge gleich 0 ist
        while (benoetigteMenge != 0.0) {
            PreisListenPosition guenstigstesAngebot = angebote.get(positionsNummer);
            // Berechne Anzahl an benoetigten Gebinden    
            double benoetigteAnzahlAnGebinden = benoetigteMenge / guenstigstesAngebot.getGebindeGroesse();
            // Wenn mehr angeboten als benötigt wird, muss die Nachkommastelle beachtet werden
            if (benoetigteAnzahlAnGebinden < guenstigstesAngebot.getVorratsBestand()) {
                // Berechne Bestellmenge
                bestellteAnzahlGebinde = Math.ceil(benoetigteAnzahlAnGebinden);
                benoetigteMenge = 0.0;
            } // wenn weniger angeboten, als benoetigt wird, einfach alles bestellen, was benoetigt wird
            else {
                bestellteAnzahlGebinde = angebote.get(positionsNummer).getVorratsBestand();
                benoetigteMenge = benoetigteMenge - guenstigstesAngebot.getVorratsBestand() * guenstigstesAngebot.getGebindeGroesse();
            }
            fuegeLieferantInEinkaufsliste(angebote.get(positionsNummer).getLieferant(), angebote.get(positionsNummer).getNahrungsmittel(), guenstigstesAngebot.getGebindeGroesse() * bestellteAnzahlGebinde, angebote.get(positionsNummer).getPreis() * bestellteAnzahlGebinde, position);
            positionsNummer++;
        }
    }

    private void fuegeLieferantInEinkaufsliste(Lieferant lieferant, Nahrungsmittel nahrungsmittel, double anzahlGebinde, double preis, EinkaufslistenPosition position) {

        if (position.getLieferant() == null) {
            position.setLieferant(lieferant);
            position.setMenge(anzahlGebinde);
            position.setPreis(preis);
        } else {
            EinkaufslistenPosition neuePosition = erstelleNeueEinkaufslistenPostion(nahrungsmittel, lieferant, anzahlGebinde, preis);
            zusaetzlichePositionen.add(neuePosition);
        }

    }

    private void fuegeMengenInEinkaufslisteEin(Map<Nahrungsmittel, Double> mengen) {
        for (Nahrungsmittel nahrungsmittel : mengen.keySet()) {
            // double berechneteMenge = zutat.getMenge();
            // Menge muss noch mit Sicherheitsfaktor multipliziert werden und anschließend gerundet werden
            EinkaufslistenPosition position = liste.findePositionDurchNahrungsmittel(nahrungsmittel);
            Double gesamtMenge = position.getMenge() + mengen.get(nahrungsmittel);
            position.setMenge(gesamtMenge);
        }
    }

    private EinkaufslistenPosition erstelleNeueEinkaufslistenPostion(Nahrungsmittel nahrungsmittel, Lieferant lieferant, double bestellMenge, double preis) {
        EinkaufslistenPosition neuePosition = new EinkaufslistenPosition(nahrungsmittel);
        neuePosition.setLieferant(lieferant);
        neuePosition.setMenge(bestellMenge);
        neuePosition.setPreis(preis);
        return neuePosition;
    }

    private void optimiereEinkaufslisteHinsichtlichLieferkosten() {
        for (Lieferant alternativLieferant : liste.holeLieferanten()) {
            for (EinkaufslistenPosition aktuellesAngebot : liste) {
                Lieferant aktuellerLieferant = aktuellesAngebot.getLieferant();
                if (aktuellerLieferantHatNurEinePositionInDerEinkaufsliste(aktuellerLieferant)
                        && beideSindBauern(aktuellerLieferant, alternativLieferant)
                        && istNichtDerGleicheBauer(aktuellerLieferant, alternativLieferant)
                        && hatGuenstigereLieferkosten(aktuellerLieferant, alternativLieferant)) {

                    PreisListenPosition alternativAngebot = lieferanten.findeAngebotFuerNahrungsmittelVonLieferant(aktuellesAngebot.getNahrungsmittel(), alternativLieferant);
                    if (alternativAngebot != null
                            && neuesAngebotHatAusreichendMenge(alternativAngebot, aktuellesAngebot)
                            && istGuenstiger(alternativAngebot, aktuellesAngebot)) {

                        aktuellesAngebot.setLieferant(alternativAngebot.getLieferant());
                        aktuellesAngebot.setPreis(alternativAngebot.berechnePreisFuerMenge(aktuellesAngebot.getMenge()));
                    }
                }
            }
        }
    }

    private boolean istNichtDerGleicheBauer(Lieferant aktueller, Lieferant alternative) {
        return !aktueller.equals(alternative);
    }

    private boolean hatGuenstigereLieferkosten(Lieferant aktueller, Lieferant alternative) {
        return aktueller.getLieferKostenFaktor() > alternative.getLieferKostenFaktor();
    }

    private boolean beideSindBauern(Lieferant aktuellerLieferant, Lieferant alternativLieferant) {
        return aktuellerLieferant.getClass().isInstance(alternativLieferant);
    }

    private boolean neuesAngebotHatAusreichendMenge(PreisListenPosition ersatzPosition, EinkaufslistenPosition position) {
        return ersatzPosition.getGesamtMenge() >= position.getMenge();
    }

    private boolean istGuenstiger(PreisListenPosition alternativAngebot, EinkaufslistenPosition aktuellesAngebot) {
        double gesparteLieferkosten = aktuellesAngebot.getLieferant().berechneLieferkosten(0) - alternativAngebot.getLieferant().berechneLieferkosten(0);
        double neuerPreis = alternativAngebot.berechnePreisFuerMenge(aktuellesAngebot.getMenge());
        double alterPreis = aktuellesAngebot.getPreis();
        double mehrkosten = neuerPreis - alterPreis;
        return gesparteLieferkosten > mehrkosten;

    }

    private boolean aktuellerLieferantHatNurEinePositionInDerEinkaufsliste(Lieferant aktuellerLieferant) {
        return liste.holeEinkaufslistenpositionenVonLieferant(aktuellerLieferant).size() == 1;
    }

    private List<PreisListenPosition> findeAngeboteZuNahrungsmittel(EinkaufslistenPosition position) {
        List<PreisListenPosition> angebote = lieferanten.findeDurchNahrungsmittel(position.getNahrungsmittel());
        return angebote;
    }
}

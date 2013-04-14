package de.vawi.kuechenchefApp.purchaseList;

import de.vawi.kuechenchefApp.dishes.IngredientCalculator;
import de.vawi.kuechenchefApp.entities.*;
import de.vawi.kuechenchefApp.menues.Menu;
import java.util.*;

/**
 * Erstellt eine Einkaufsliste auf Basis der hinzugefügten Speisepläne.
 *
 * @author Lepping
 * @version 29.01.2013
 */
public class PurchaseListCreator {

    private PurchaseList liste = new PurchaseList();
    private List<Menu> speiseplaene = new ArrayList<>();
    private CreatePurchaseListDao lieferanten;
    private List<PurchaseListPosition> zusaetzlichePositionen = new ArrayList<>();

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
    public PurchaseList erzeuge() {
        erstelleEinkaufslistePosition();
        fuegeGünstigsteLieferantenInEinkaufslisteEin();
        optimiereEinkaufslisteHinsichtlichLieferkosten();

        return liste;
    }

    private void erstelleEinkaufslistePosition() {
        for (Menu speiseplan : speiseplaene) {
            Map<Food, Double> mengen = new IngredientCalculator().berechneGesamtMengen(speiseplan);
            fuegeMengenInEinkaufslisteEin(mengen);
        }
    }

    private void fuegeGünstigsteLieferantenInEinkaufslisteEin() {
        for (PurchaseListPosition position : liste) {
            findeGuenstigestenLieferantFuer(position);
        }

        for (PurchaseListPosition einkaufslistenPosition : zusaetzlichePositionen) {
            liste.hinzufügenEinkaufslistenPosition(einkaufslistenPosition);
        }

    }

    private void findeGuenstigestenLieferantFuer(PurchaseListPosition position) {
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

    private void fuegeLieferantInEinkaufsliste(Supplier lieferant, Food nahrungsmittel, double anzahlGebinde, double preis, PurchaseListPosition position) {

        if (position.getLieferant() == null) {
            position.setLieferant(lieferant);
            position.setMenge(anzahlGebinde);
            position.setPreis(preis);
        } else {
            PurchaseListPosition neuePosition = erstelleNeueEinkaufslistenPostion(nahrungsmittel, lieferant, anzahlGebinde, preis);
            zusaetzlichePositionen.add(neuePosition);
        }

    }

    private void fuegeMengenInEinkaufslisteEin(Map<Food, Double> mengen) {
        for (Food nahrungsmittel : mengen.keySet()) {
            // double berechneteMenge = zutat.getMenge();
            // Menge muss noch mit Sicherheitsfaktor multipliziert werden und anschließend gerundet werden
            PurchaseListPosition position = liste.findePositionDurchNahrungsmittel(nahrungsmittel);
            Double gesamtMenge = position.getMenge() + mengen.get(nahrungsmittel);
            position.setMenge(gesamtMenge);
        }
    }

    private PurchaseListPosition erstelleNeueEinkaufslistenPostion(Food nahrungsmittel, Supplier lieferant, double bestellMenge, double preis) {
        PurchaseListPosition neuePosition = new PurchaseListPosition(nahrungsmittel);
        neuePosition.setLieferant(lieferant);
        neuePosition.setMenge(bestellMenge);
        neuePosition.setPreis(preis);
        return neuePosition;
    }

    private void optimiereEinkaufslisteHinsichtlichLieferkosten() {
        for (Supplier alternativLieferant : liste.holeLieferanten()) {
            for (PurchaseListPosition aktuellesAngebot : liste) {
                Supplier aktuellerLieferant = aktuellesAngebot.getLieferant();
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

    private boolean istNichtDerGleicheBauer(Supplier aktueller, Supplier alternative) {
        return !aktueller.equals(alternative);
    }

    private boolean hatGuenstigereLieferkosten(Supplier aktueller, Supplier alternative) {
        return aktueller.getLieferKostenFaktor() > alternative.getLieferKostenFaktor();
    }

    private boolean beideSindBauern(Supplier aktuellerLieferant, Supplier alternativLieferant) {
        return aktuellerLieferant.getClass().isInstance(alternativLieferant);
    }

    private boolean neuesAngebotHatAusreichendMenge(PreisListenPosition ersatzPosition, PurchaseListPosition position) {
        return ersatzPosition.getGesamtMenge() >= position.getMenge();
    }

    private boolean istGuenstiger(PreisListenPosition alternativAngebot, PurchaseListPosition aktuellesAngebot) {
        double gesparteLieferkosten = aktuellesAngebot.getLieferant().berechneLieferkosten(0) - alternativAngebot.getLieferant().berechneLieferkosten(0);
        double neuerPreis = alternativAngebot.berechnePreisFuerMenge(aktuellesAngebot.getMenge());
        double alterPreis = aktuellesAngebot.getPreis();
        double mehrkosten = neuerPreis - alterPreis;
        return gesparteLieferkosten > mehrkosten;

    }

    private boolean aktuellerLieferantHatNurEinePositionInDerEinkaufsliste(Supplier aktuellerLieferant) {
        return liste.holeEinkaufslistenpositionenVonLieferant(aktuellerLieferant).size() == 1;
    }

    private List<PreisListenPosition> findeAngeboteZuNahrungsmittel(PurchaseListPosition position) {
        List<PreisListenPosition> angebote = lieferanten.findeDurchNahrungsmittel(position.getNahrungsmittel());
        return angebote;
    }
}

package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.dateien.*;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import de.vawi.kuechenchefApp.speisen.SpeisenVerwaltung.SpeiseNichtGefunden;
import java.util.*;

/**
 * Importiert die Dateien rezepte.csv und hitliste.csv aus einem vom Anwender
 * vorgegebenen Ordner.
 *
 * @author Tatsch
 * @version 28.01.2013
 */
public class SpeisenImport {

    public static final int SPEISEN_NAME = 0;
    private Datei rezepte;
    private Datei hitliste;
    private CsvZeileSeparator separator = new CsvZeileSeparator();
    private SpeisenVerwaltung speisen = SpeisenVerwaltung.getInstanz();
    private List<Speise> unvollstaendigeSpeisen = new ArrayList<>();
    private String dateiOrdner;

    public SpeisenImport(String dateiOrdner) {
        this.dateiOrdner = dateiOrdner;
    }

    /**
     * Importiert die Dateien rezepte.csv und hitliste.csv aus dem vom Anwender
     * angegebenen Ordner. Auf Basis dieser Dateien wird die SpeisenVerwaltung
     * für den SpeiseplanErsteller erstellt.
     *
     * Die Datei mit den Rezepten muss den namen "rezepte.csv" haben.
     *
     * Die Datei mit den beliebtesten Rezepten muss den Namen "hitliste.csv"
     * haben.
     *
     * Sind diese Dateien nicht vorhanden wird eine FileNotFoundException
     * geworfen. Anschließend wird das Programm mit der Fehlermeldung beendet.
     *
     */
    public void importFiles() {
        leseDateien();
        fuegeSpeisenVonHitlisteInSpeisenverwaltungEin();
        fuegeZutatenZuSpeisenAusRezepteDateiHinzu();
        loescheUnvollstaendigeSpeisen();
        loescheGerichteDieAufSchwarzerListeStehen();
        sortiereZutatenNachKategorie();
    }

    /**
     * Diese Methode liest die Speisen aus der Hitliste in die SpeisenVerwaltung
     * ein.
     *
     * @throws
     * de.vawi.kuechenchefApp.speisen.SpeisenImport.HitlisteDateiIstNichtValide
     * Wird geworfen, wenn die hitliste-Datei nicht gelsen werden kann.
     */
    private void fuegeSpeisenVonHitlisteInSpeisenverwaltungEin() throws HitlisteDateiIstNichtValide {
        for (String zeile : hitliste) {
            try {
                Speise speise = erstelleSpeise(zeile);
                speisen.addSpeise(speise);
            } catch (Exception ex) {
                throw new HitlisteDateiIstNichtValide(zeile);
            }
        }
    }

    /**
     * Erstellt eine Speise aus der Zeile der Hitlisten-Datei.
     *
     * @param zeile eine Zeile aus der Hitlisten-Datei.
     * @return Gibt eine Speise aus.
     */
    private Speise erstelleSpeise(String zeile) {
        SpeisenErsteller ersteller = new SpeisenErsteller();
        Speise speise = ersteller.erstelle(zeile);
        return speise;
    }

    /**
     * Diese Methode fängt den Versuch, Zutaten zu generieren auf: Eine
     * Exception wird geworfen, wenn - entweder keine Speise in der Zeile der
     * Rezept-Datei gefunden wird, oder - die Rezept-Datei nicht valide ist und
     * nicht gelesen werden kann.
     */
    private void fuegeZutatenZuSpeisenAusRezepteDateiHinzu() {
        for (String zeile : rezepte) {
            try {
                versucheZutatZuGenerieren(zeile);
            } catch (SpeiseNichtGefunden ex) {
                handhabeSpeiseNichtGefunden(ex, zeile);
            } catch (Exception ex) {
                handhabeRezepteDateiIstNichtValide(ex, zeile);
            }
        }
    }

    /**
     * Löscht alle Speisen, die zuvor gesammelt wurden, da ihre Zutaten nicht
     * vollständig als Nahrungsmittel im Programm verfügbar sind.
     */
    private void loescheUnvollstaendigeSpeisen() {
        for (Speise speise : unvollstaendigeSpeisen) {
            speisen.entferne(speise);
        }
    }

    /**
     * Diese Methode sortiert die Zutaten, die zu einer Speise gehören, nach
     * Kategorie. Es gibt derzeit 3 Kategorien, wobei im Enum
     * SpeisenUndNahrungsmittelKategorie Fleisch prioritär angelegt ist, gefolgt
     * von Fisch, gefolgt von vegetarisch. Dies hilft später, um festzustellen,
     * ob eine Speise als Fleisch-Gericht, Fisch-Gericht oder vegetarisches
     * Gericht eingestuft und ausgewählt wird.
     */
    private void sortiereZutatenNachKategorie() {
        for (Speise speise : speisen) {
            Collections.sort(speise.getZutaten(), new Comparator<Zutat>() {
                @Override
                public int compare(Zutat o1, Zutat o2) {
                    SpeisenUndNahrungsmittelKategorie kategorie1 = o1.getNahrungsmittel().getKategorie();
                    SpeisenUndNahrungsmittelKategorie kategorie2 = o2.getNahrungsmittel().getKategorie();
                    return kategorie1.compareTo(kategorie2);
                }
            });
        }
    }

    /**
     * Diese Methode liest aus einer Zeile der rezepte-Datei den Speisennamen
     * heraus, und überprüft, ob die daneben stehende Zutat als Nahrungsmittel
     * angelegt wurde und vom Programm weiter verwendet werden kann.
     *
     * @param zeile eine Zeile der rezepte-Datei.
     * @throws
     * de.vawi.kuechenchefApp.speisen.SpeisenVerwaltung.SpeiseNichtGefunden wird
     * geworfen, wenn die Speise nicht gefunden wurde, sprich nicht aus der
     * hitliste generiert wurde.
     */
    private void versucheZutatZuGenerieren(String zeile) throws SpeiseNichtGefunden {
        List<String> zellen = separator.separiere(zeile);
        Speise speise = speisen.findeSpeise(zellen.get(SPEISEN_NAME));
        try {
            Zutat zutat = versucheNahrungsmittelZuErstellen(zeile);
            speise.addZutat(zutat);
        } catch (NahrungsmittelVerwaltung.NahrungsmittelNichtGefunden ex) {
            handhabeNahrungsmittelZuZutatNichtVorhanden(speise);
        }
    }

    /**
     * Liest die Hitliste und die Rezepte aus dem vom Anwender angegebenen
     * Ordner ein. Hierbei müssen die Dateien hitliste.csv und rezepte.csv
     * heißen.
     */
    private void leseDateien() {
        hitliste = leseDatei(dateiOrdner + "/" + "hitliste.csv");
        rezepte = leseDatei(dateiOrdner + "/" + "rezepte.csv");
    }

    /**
     * Diese Methode kann Dateien aus einem Ordner einlesen.
     *
     * @param dateiPfad Der Pfad zu der Datei, die eingelesen werden soll.
     * @return Gibt die eingelesene Datei vom Typ Datei wider.
     */
    protected Datei leseDatei(String dateiPfad) {
        return new DateiLeser(dateiPfad).leseDatei();
    }

    /**
     * Diese Methode erstellt ein Nahrungsmittel aus einer Zeile der
     * rezepte-Datei.
     *
     * @param zeile Eine Zeile der Rezepte-Datei.
     * @return Gibt die Zutat wider, wenn sie erstellt werden konnte.
     */
    protected Zutat versucheNahrungsmittelZuErstellen(String zeile) {
        Zutat zutat;
        zutat = new ZutatErsteller().erstelle(zeile);
        return zutat;
    }

    /**
     * Falls eine Zutat keinem Nahrungsmittel zugeordnet werden kann, wird die
     * gesamte Speise in der unvollstaendigeSpeisen-Liste gesammelt.
     *
     * @param speise Die Speise, deren Zutat nicht unter Nahrungsmittel gefunden
     * wurde.
     */
    protected void handhabeNahrungsmittelZuZutatNichtVorhanden(Speise speise) {
        unvollstaendigeSpeisen.add(speise);
    }

    /**
     * Diese Methode greift, wenn in der Zeile der rezepte-Datei keine Speise
     * gefunden wird.
     *
     * @param ex
     * @param zeile Eine Zeile der rezepte-Datei.
     */
    protected void handhabeSpeiseNichtGefunden(SpeiseNichtGefunden ex, String zeile) {
        throw ex;
    }

    /**
     * Diese Methode greift, wenn die rezepte-Datei nicht valide, bzw. nicht
     * lesbar ist.
     *
     * @param ex Exception, die in dem Fall geworfen wird.
     * @param zeile Die Zeile in der rezepte-Datei, die nicht gelsesen werden
     * kann.
     * @throws
     * de.vawi.kuechenchefApp.speisen.SpeisenImport.RezepteDateiIstNichtValide
     * Wirft die Exception, dass die Rezepte-Datei in einer bestimmten Zeile
     * nicht lesbar ist.
     */
    protected void handhabeRezepteDateiIstNichtValide(Exception ex, String zeile) throws RezepteDateiIstNichtValide {
        throw new RezepteDateiIstNichtValide(zeile);
    }

    protected void loescheGerichteDieAufSchwarzerListeStehen() {
        
    }

    /**
     * Diese Methode greift, wenn die Hitlisten-Datei in einer bestimmten Zeile
     * nicht eingelsen werden kann.
     */
    public static class HitlisteDateiIstNichtValide extends RuntimeException {

        public HitlisteDateiIstNichtValide(String zeile) {
            super("Hitlistedatei enthaelt fehlerhafte Zeile: " + zeile);
        }
    }

    /**
     * Diese Methode greift, wenn die rezepte-Datei in einer bestimmten Zeile
     * nicht eingelesen werden kann.
     */
    public static class RezepteDateiIstNichtValide extends RuntimeException {

        public RezepteDateiIstNichtValide(String zeile) {
            super("Rezeptedatei enthaelt fehlerhafte Zeile: " + zeile);
        }
    }
}

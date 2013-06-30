package de.vawi.factoryCanteen.persistence.dishes;

import de.vawi.factoryCanteen.app.entities.Dish;
import de.vawi.fileManagement.VawiFileManager;
import de.vawi.factoryCanteen.persistence.files.CsvFileReader;
import de.vawi.factoryCanteen.persistence.files.CsvLineSeperator;
import de.vawi.factoryCanteen.persistence.files.CsvFile;

import java.util.*;

/**
 * Importiert die Dateien rezepte.csv und hitliste.csv aus einem vom Anwender
 * vorgegebenen Ordner.
 *
 * @author Tatsch
 * @version 28.01.2013
 */
public class DishesImport {

    public static final int SPEISEN_NAME = 0;
    private CsvFile hitlist;
    private CsvLineSeperator separator = new CsvLineSeperator();
    private DishesDB dishes = DishesDB.getInstance();
    private List<Dish> uncompletDishes = new ArrayList<>();
    private final static String IMPORT_DIRECTORY = "importFiles";

    /**
     * Importiert die Dateien rezepte.csv und hitliste.csv aus dem vom Anwender
     * angegebenen Ordner. Auf Basis dieser Dateien wird die SpeisenVerwaltung
     * für den SpeiseplanErsteller erstellt.
     *
     * Die CsvFile mit den Rezepten muss den namen "rezepte.csv" haben.
     *
     * Die CsvFile mit den beliebtesten Rezepten muss den Namen "hitliste.csv"
     * haben.
     *
     * Sind diese Dateien nicht vorhanden wird eine FileNotFoundException
     * geworfen. Anschließend wird das Programm mit der Fehlermeldung beendet.
     *
     */
    public void importFiles() {
        leseDateien();
        fuegeSpeisenVonHitlisteInSpeisenverwaltungEin();
    }

    /**
     * Diese Methode liest die Speisen aus der Hitliste in die SpeisenVerwaltung
     * ein.
     *
     * @throws
     * de.vawi.kuechenchefApp.speisen.SpeisenImport.HitlisteDateiIstNichtValide
     * Wird geworfen, wenn die hitliste-CsvFile nicht gelsen werden kann.
     */
    private void fuegeSpeisenVonHitlisteInSpeisenverwaltungEin() throws HitlisteDateiIstNichtValide {
        for (String line : hitlist) {
            try {
                Dish dish = erstelleSpeise(line);
                dishes.addDish(dish);
            } catch (Exception ex) {
                throw new HitlisteDateiIstNichtValide(line);
            }
        }
    }

    /**
     * Erstellt eine Speise aus der Zeile der Hitlisten-CsvFile.
     *
     * @param zeile eine Zeile aus der Hitlisten-CsvFile.
     * @return Gibt eine Speise aus.
     */
    private Dish erstelleSpeise(String zeile) {
        DishCreator ersteller = new DishCreator();
        Dish speise = ersteller.create(zeile);
        return speise;
    }

    /**
     * Liest die Hitliste und die Rezepte aus dem vom Anwender angegebenen
     * Ordner ein. Hierbei müssen die Dateien hitliste.csv und rezepte.csv
     * heißen.
     */
    private void leseDateien() {
        hitlist = leseDatei(IMPORT_DIRECTORY + "/" + "hitliste.csv");
    }

    /**
     * Diese Methode kann Dateien aus einem Ordner einlesen.
     *
     * @param dateiPfad Der Pfad zu der CsvFile, die eingelesen werden soll.
     * @return Gibt die eingelesene CsvFile vom Typ CsvFile wider.
     */
    protected CsvFile leseDatei(String dateiPfad) {
        CsvFileReader reader = new CsvFileReader();
        reader.setFileReader(new VawiFileManager(dateiPfad));
        return reader.readFile();
    }

    /**
     * Diese Methode greift, wenn die Hitlisten-CsvFile in einer bestimmten
     * Zeile nicht eingelsen werden kann.
     */
    public static class HitlisteDateiIstNichtValide extends RuntimeException {

        public HitlisteDateiIstNichtValide(String zeile) {
            super("Hitlistedatei enthaelt fehlerhafte Zeile: " + zeile);
        }
    }
}

package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.dateien.Datei;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.experimental.theories.suppliers.TestedOn;

public class SpeisenImportTest {

    private SpeisenImport importer;
    private SpeisenVerwaltung speisen;
    private List<String> hitlisteZeilen;
    private List<Nahrungsmittel> nahrungsmittel;
    private List<String> rezepteZeilen;
    

    /**
     * Speisen aus Hitliste importieren -> OK Zutaten aus Rezpete importieren ->
     * OK Zutaten zu Speisen zuordnen -> OK
     */
    private void initRezepteZeilen() {
        rezepteZeilen = new ArrayList<>();
        rezepteZeilen.add("\"Bohneneintopf Mexiko\",150,\"g\",\"Kartoffeln\"");
        rezepteZeilen.add("\"Bohneneintopf Mexiko\",10,\"g\",\"Chilipulver\"");
        rezepteZeilen.add("\"Falscher Hase\",10,\"g\",\"Hase\"");
        rezepteZeilen.add("\"Falscher Hase\",10,\"g\",\"Chilipulver\"");
    }

    private void initHitlisteZeilen() {
        hitlisteZeilen = new ArrayList<>();
        hitlisteZeilen.add("1,\"Bohneneintopf Mexiko\"");
        hitlisteZeilen.add("2,\"Falscher Hase\"");

    }

    @Before
    public void before() {
        initRezepteZeilen();
        initHitlisteZeilen();
        initNahrungsmittel();
        fuegeKartoffelUndChilipulvernInNahrungsmittelVerwaltungEin();
        initializiereImporter();
        initizialisiereSpeisenVerwaltung();
    }

    @Test
    public void testSpeisenImportAusHitliste() {
        importer.importFiles();

        assertEquals(1, speisen.findeSpeise("Bohneneintopf Mexiko").getBeliebtheit());
        assertEquals(2, speisen.findeSpeise("Falscher Hase").getBeliebtheit());
    }

    @Test
    public void testKategorisierungDerVegetarischenSpeisen() {
        importer.importFiles();

        Speise speise = speisen.findeSpeise("Bohneneintopf Mexiko");
        assertEquals(SpeisenUndNahrungsmittelKategorie.VEGETARISCH, speise.getKategorie());
    }

    @Test
    public void testKategorisierungDerFleischSpeisen() {
        importer.importFiles();

        Speise speise = speisen.findeSpeise("Falscher Hase");
        assertEquals(SpeisenUndNahrungsmittelKategorie.FLEISCH, speise.getKategorie());
    }

    @Test(expected = SpeisenImport.HitlisteDateiIstNichtValide.class)
    public void testFehlerhafteHitliste() {
        hitlisteZeilen.add(",\"Falscher Hase\"");
        importer.importFiles();
        fail();
    }

    @Test(expected = SpeisenImport.RezepteDateiIstNichtValide.class)
    public void testFehlerhafteRezepteDatei() {
        rezepteZeilen.add("\"Falscher Hase\",,\"\",\"Eier\"");
        importer.importFiles();
        fail();
    }
    
    

    private void fuegeKartoffelUndChilipulvernInNahrungsmittelVerwaltungEin() {
        Nahrungsmittel kartoffeln = new Nahrungsmittel();
        kartoffeln.setName("Kartoffeln");
        kartoffeln.setEinheit(Einheit.GRAMM);
        kartoffeln.setKategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH);

        Nahrungsmittel chilipulver = new Nahrungsmittel();
        chilipulver.setName("Chilipulver");
        chilipulver.setEinheit(Einheit.GRAMM);
        chilipulver.setKategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH);

        Nahrungsmittel hase = new Nahrungsmittel();
        hase.setName("Hase");
        hase.setEinheit(Einheit.GRAMM);
        hase.setKategorie(SpeisenUndNahrungsmittelKategorie.FLEISCH);

        NahrungsmittelVerwaltung.getInstanz().fuegeHinzu(kartoffeln);
        NahrungsmittelVerwaltung.getInstanz().fuegeHinzu(chilipulver);
        NahrungsmittelVerwaltung.getInstanz().fuegeHinzu(hase);
    }

    private void initializiereImporter() {
        importer = new TestbarerImport("ordnerName");
    }

    private void initizialisiereSpeisenVerwaltung() {
        speisen = SpeisenVerwaltung.getInstanz();
    }

    private void initNahrungsmittel() {
        nahrungsmittel = new ArrayList<>();

        nahrungsmittel.add(erstelleNahrungsmittel("Hase", SpeisenUndNahrungsmittelKategorie.FLEISCH));
        nahrungsmittel.add(erstelleNahrungsmittel("Chilipulver", SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        nahrungsmittel.add(erstelleNahrungsmittel("Kartoffeln", SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        
        nahrungsmittel.add(erstelleNahrungsmittel("Eier", SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
    }

    private Nahrungsmittel erstelleNahrungsmittel(String name, SpeisenUndNahrungsmittelKategorie kategorie) {
        Nahrungsmittel mittel = new Nahrungsmittel();
        mittel.setName(name);
        mittel.setKategorie(kategorie);
        return mittel;
    }

    private class TestbarerImport extends SpeisenImport {

        public TestbarerImport(String dateiOrdner) {
            super(dateiOrdner);
        }

        @Override
        protected Datei leseDatei(String dateiPfad) {
            if (dateiPfad.endsWith("hitliste.csv")) {
                return new TestbareHitlisteDatei();
            } else {
                return new TestbareRezpeteDatei();
            }
        }
    }

    private class TestbareHitlisteDatei implements Datei {

        @Override
        public String getDateiname() {
            return "TestbareHitliste.csv";
        }

        @Override
        public Iterator<String> iterator() {
            return hitlisteZeilen.iterator();
        }
    }

    private class TestbareRezpeteDatei implements Datei {

        @Override
        public String getDateiname() {
            return "TestbareHitliste.csv";
        }

        @Override
        public Iterator<String> iterator() {
            return rezepteZeilen.iterator();
        }
    }
}
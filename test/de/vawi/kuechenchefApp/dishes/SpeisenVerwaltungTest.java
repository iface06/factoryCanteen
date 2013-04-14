

package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.entities.*;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;
import de.vawi.kuechenchefApp.speisen.SpeisenVerwaltung.SpeiseNichtGefunden;
import org.junit.*;
import static org.junit.Assert.*;


public class SpeisenVerwaltungTest {
    private SpeisenVerwaltung verwaltung;
    
    @Before
    public void before() {
        verwaltung = new SpeisenVerwaltung();
        
    }

    @Test
    public void testHinzufuegenEinerSpeise() {
        Speise speise = erstelleSpeise("Steaks");
        verwaltung.addSpeise(speise);
        assertEquals(1, verwaltung.size());
    }
    
    @Test
    public void testHinzufuegenEinerBereitsVorhandenSpeise() { 
        Speise speise = erstelleSpeise("Steaks");
        verwaltung.addSpeise(speise);
        
        assertEquals(1, verwaltung.size());
    }
    
    @Test
    public void testFindeSpeiseMitNamen(){
        Speise speise = erstelleSpeise("Steaks");
        verwaltung.addSpeise(speise);
        Speise steaks = verwaltung.findeSpeise("Steaks");
        
        assertEquals("Steaks", steaks.getName());
    }
 
    @Test(expected=SpeiseNichtGefunden.class)
    public void testFindeNichtDieSpeiseMitNamen(){
        Speise steaks = verwaltung.findeSpeise("Drei im Weckla");
        fail();
    }
    
    @Test
    public void testAusreichendSpeisenVorhandenFuerSpeiseplanErstellung(){
        fuegeSpeisenNachKategorieInVerwaltungEin(SpeisenUndNahrungsmittelKategorie.FISCH, 15);
        fuegeSpeisenNachKategorieInVerwaltungEin(SpeisenUndNahrungsmittelKategorie.FLEISCH, 15);
        fuegeSpeisenNachKategorieInVerwaltungEin(SpeisenUndNahrungsmittelKategorie.VEGETARISCH, 15);
        boolean result = verwaltung.sindAusreichendSpeisenFuerSpeiseplanErstellungVorhanden();
        assertTrue(result);
    }
    
    @Test
    public void testNichtAusreichendFischSpeisenVorhandenFuerSpeiseplanErstellung(){
        fuegeSpeisenNachKategorieInVerwaltungEin(SpeisenUndNahrungsmittelKategorie.FISCH, 2);
        fuegeSpeisenNachKategorieInVerwaltungEin(SpeisenUndNahrungsmittelKategorie.FLEISCH, 28);
        fuegeSpeisenNachKategorieInVerwaltungEin(SpeisenUndNahrungsmittelKategorie.VEGETARISCH, 15);
        boolean result = verwaltung.sindAusreichendSpeisenFuerSpeiseplanErstellungVorhanden();
        assertFalse(result);
    }
    
    @Test
    public void testNichtAusreichendFleischSpeisenVorhandenFuerSpeiseplanErstellung(){
        fuegeSpeisenNachKategorieInVerwaltungEin(SpeisenUndNahrungsmittelKategorie.FLEISCH, 14);
        fuegeSpeisenNachKategorieInVerwaltungEin(SpeisenUndNahrungsmittelKategorie.FISCH, 16);
        fuegeSpeisenNachKategorieInVerwaltungEin(SpeisenUndNahrungsmittelKategorie.VEGETARISCH, 15);
        boolean result = verwaltung.sindAusreichendSpeisenFuerSpeiseplanErstellungVorhanden();
        assertFalse(result);
    }
    
    @Test
    public void testNichtAusreichendVegetarischeSpeisenVorhandenFuerSpeiseplanErstellung(){
        fuegeSpeisenNachKategorieInVerwaltungEin(SpeisenUndNahrungsmittelKategorie.VEGETARISCH, 14);
        fuegeSpeisenNachKategorieInVerwaltungEin(SpeisenUndNahrungsmittelKategorie.FLEISCH, 15);
        fuegeSpeisenNachKategorieInVerwaltungEin(SpeisenUndNahrungsmittelKategorie.FISCH, 16);
        boolean result = verwaltung.sindAusreichendSpeisenFuerSpeiseplanErstellungVorhanden();
        assertFalse(result);
    }
    

    private Speise erstelleSpeise(String name) {
        Speise speise = new Speise();
        speise.setName(name);
        return speise;
    }

    private void fuegeSpeisenNachKategorieInVerwaltungEin(SpeisenUndNahrungsmittelKategorie kategorie, int anzahl) {
        Zutat salz = new DummyZutat().name("Salz").erstelle();
        for (int anzahlSpeisen = 0; anzahlSpeisen < anzahl; anzahlSpeisen++) {
            Zutat nachKategorie = new DummyZutat().name("Zutat-" + kategorie.getAbkuerzung()).kategorie(kategorie).erstelle();
            Speise speise = new DummySpeise().name("Speise-"+ kategorie.getAbkuerzung() + "-" + anzahlSpeisen).mitZutat(nachKategorie).mitZutat(salz).erstelle();
            verwaltung.addSpeise(speise);
        }
    }

    

}
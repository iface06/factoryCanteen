

package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.entities.Zutat;
import de.vawi.kuechenchefApp.speisen.ZutatErsteller;
import de.vawi.kuechenchefApp.entities.Einheit;
import de.vawi.kuechenchefApp.entities.Nahrungsmittel;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;
import de.vawi.kuechenchefApp.nahrungsmittel.NahrungsmittelVerwaltung;
import org.junit.*;
import static org.junit.Assert.*;


public class ZutatErstellerTest {
    private Nahrungsmittel erwatetesNahrungsmittel;
    private String rezpetZeile;
    private ZutatErsteller ersteller;

    /**
     * Separiere Zeile aus Rezepte-Datei
     * Erstelle Zutat aus separierter Zeile mit Menge
     * Erstelle Nahrungsmittel wenn noch nicht vorhanden, ansonsten verknüpfe Zutat mit bereits importierten Nahrungsmittel
     */
    
    @Before
    public void before() {
        fuegeKartoffelnInNahrungsmittelVerwaltungEin();
        erstelleErwartetesNahrungsmittel();
        ersteller = new ZutatErsteller();
    }
    
    @Test
    public void testErstelleZutatKartoffeln() {
        rezpetZeile = "\"Kartoffeln mit Senfsauce und Ei\",150,\"g\",\"Kartoffeln\"";
        Zutat zutat = ersteller.erstelle(rezpetZeile);
        assertEquals(150.0, zutat.getMenge(), 0.0001);
    }
    
    @Test
    public void testNahrungsmittelZurZutatKartoffeln(){
        rezpetZeile = "\"Kartoffeln mit Senfsauce und Ei\",150,\"g\",\"Kartoffeln\"";
        Zutat zutat = ersteller.erstelle(rezpetZeile);
        assertEquals(erwatetesNahrungsmittel, zutat.getNahrungsmittel());
    }
    
    @Test
    public void testNahrungsmittelZurZutatEier(){
        rezpetZeile = "Kartoffeln mit Senfsauce und Ei,\"1,5\",,\"Ei\"";
        Zutat zutat = ersteller.erstelle(rezpetZeile);
        assertEquals("Ei", zutat.getNahrungsmittel().getName());
        assertEquals(Einheit.STUECK, zutat.getNahrungsmittel().getEinheit());
    }

    private void fuegeKartoffelnInNahrungsmittelVerwaltungEin() {
        Nahrungsmittel kartoffeln = erstelleKartoffel();
        Nahrungsmittel eier = erstelleEier();
        
        NahrungsmittelVerwaltung.getInstanz().fuegeHinzu(kartoffeln);
        NahrungsmittelVerwaltung.getInstanz().fuegeHinzu(eier);
    }

    private void erstelleErwartetesNahrungsmittel() {
        erwatetesNahrungsmittel = new Nahrungsmittel();
        erwatetesNahrungsmittel.setName("Kartoffeln");
        erwatetesNahrungsmittel.setEinheit(Einheit.GRAMM);
        erwatetesNahrungsmittel.setKategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH);   
    }

    private Nahrungsmittel erstelleKartoffel() {
        Nahrungsmittel kartoffeln = new Nahrungsmittel();
        kartoffeln.setName("Kartoffeln");
        kartoffeln.setEinheit(Einheit.GRAMM);
        kartoffeln.setKategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH);
        return kartoffeln;
    }

    private Nahrungsmittel erstelleEier() {
        Nahrungsmittel eier = new Nahrungsmittel();
        eier.setName("Ei");
        eier.setEinheit(Einheit.STUECK);
        eier.setKategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH);
        return eier;
    }

    
}
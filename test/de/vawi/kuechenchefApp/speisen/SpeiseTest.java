

package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;
import de.vawi.kuechenchefApp.speisen.Speise;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;


public class SpeiseTest {

    @Test
    public void testNotEquals() {
        Speise a = new Speise();
        a.setName("Steaks");
        
        Speise b = new Speise();
        b.setName("Blumenkohl");
        
        assertFalse(a.equals(b));
    }
    
    @Test
    public void testEquals() {
        Speise a = new Speise();
        a.setName("Steaks");
        
        Speise b = new Speise();
        b.setName("Steaks");
        
        assertTrue(a.equals(b));
    }
    
    @Test
    public void testKategorieFleisch(){
        Zutat fleisch = new DummyZutat().name("Hüftsteak").kategorie(SpeisenUndNahrungsmittelKategorie.FLEISCH).menge(10.0).erstelle();
        Zutat kartoffeln = new DummyZutat().name("Kartoffeln").kategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH).menge(100.0).erstelle();
        Speise speise = new DummySpeise().name("Steaks").mitZutat(kartoffeln).mitZutat(fleisch).erstelle();
        
        sortiereZutatzenDerSpeise(speise);
        
        assertEquals(SpeisenUndNahrungsmittelKategorie.FLEISCH, speise.getKategorie());   
    }
    
    @Test
    public void testKategorieVegetarisch(){
        Zutat eier = new DummyZutat().name("Eier").kategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH).menge(10.0).erstelle();
        Zutat kartoffeln = new DummyZutat().name("Kartoffeln").kategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH).menge(100.0).erstelle();
        Speise speise = new DummySpeise().name("Steaks").mitZutat(kartoffeln).mitZutat(eier).erstelle();
        
        sortiereZutatzenDerSpeise(speise);
        
        assertEquals(SpeisenUndNahrungsmittelKategorie.VEGETARISCH, speise.getKategorie());   
    }
    
    @Test
    public void testKategorieFisch(){
        Zutat fisch = new DummyZutat().name("Seelachs").kategorie(SpeisenUndNahrungsmittelKategorie.FISCH).menge(10.0).erstelle();
        Zutat kartoffeln = new DummyZutat().name("Kartoffeln").kategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH).menge(100.0).erstelle();
        Speise speise = new DummySpeise().name("Steaks").mitZutat(kartoffeln).mitZutat(fisch).erstelle();
        
        sortiereZutatzenDerSpeise(speise);
        
        assertEquals(SpeisenUndNahrungsmittelKategorie.FISCH, speise.getKategorie());   
    }

    private void sortiereZutatzenDerSpeise(Speise speise) {
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
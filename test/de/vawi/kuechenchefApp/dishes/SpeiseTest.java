

package de.vawi.kuechenchefApp.dishes;

import de.vawi.kuechenchefApp.entities.*;
import de.vawi.kuechenchefApp.foods.SpeisenUndNahrungsmittelKategorie;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;


public class SpeiseTest {

    @Test
    public void testNotEquals() {
        Dish a = new Dish();
        a.setName("Steaks");
        
        Dish b = new Dish();
        b.setName("Blumenkohl");
        
        assertFalse(a.equals(b));
    }
    
    @Test
    public void testEquals() {
        Dish a = new Dish();
        a.setName("Steaks");
        
        Dish b = new Dish();
        b.setName("Steaks");
        
        assertTrue(a.equals(b));
    }
    
    @Test
    public void testKategorieFleisch(){
        Ingredient fleisch = new DummyZutat().name("Hüftsteak").kategorie(SpeisenUndNahrungsmittelKategorie.FLEISCH).menge(10.0).erstelle();
        Ingredient kartoffeln = new DummyZutat().name("Kartoffeln").kategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH).menge(100.0).erstelle();
        Dish speise = new DummySpeise().name("Steaks").mitZutat(kartoffeln).mitZutat(fleisch).erstelle();
        
        sortiereZutatzenDerSpeise(speise);
        
        assertEquals(SpeisenUndNahrungsmittelKategorie.FLEISCH, speise.getKategorie());   
    }
    
    @Test
    public void testKategorieVegetarisch(){
        Ingredient eier = new DummyZutat().name("Eier").kategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH).menge(10.0).erstelle();
        Ingredient kartoffeln = new DummyZutat().name("Kartoffeln").kategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH).menge(100.0).erstelle();
        Dish speise = new DummySpeise().name("Steaks").mitZutat(kartoffeln).mitZutat(eier).erstelle();
        
        sortiereZutatzenDerSpeise(speise);
        
        assertEquals(SpeisenUndNahrungsmittelKategorie.VEGETARISCH, speise.getKategorie());   
    }
    
    @Test
    public void testKategorieFisch(){
        Ingredient fisch = new DummyZutat().name("Seelachs").kategorie(SpeisenUndNahrungsmittelKategorie.FISCH).menge(10.0).erstelle();
        Ingredient kartoffeln = new DummyZutat().name("Kartoffeln").kategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH).menge(100.0).erstelle();
        Dish speise = new DummySpeise().name("Steaks").mitZutat(kartoffeln).mitZutat(fisch).erstelle();
        
        sortiereZutatzenDerSpeise(speise);
        
        assertEquals(SpeisenUndNahrungsmittelKategorie.FISCH, speise.getKategorie());   
    }

    private void sortiereZutatzenDerSpeise(Dish speise) {
        Collections.sort(speise.getZutaten(), new Comparator<Ingredient>() {
                @Override
                public int compare(Ingredient o1, Ingredient o2) {
                    SpeisenUndNahrungsmittelKategorie kategorie1 = o1.getNahrungsmittel().getKategorie();
                    SpeisenUndNahrungsmittelKategorie kategorie2 = o2.getNahrungsmittel().getKategorie();
                    return kategorie1.compareTo(kategorie2);
                }
            });
    }

}
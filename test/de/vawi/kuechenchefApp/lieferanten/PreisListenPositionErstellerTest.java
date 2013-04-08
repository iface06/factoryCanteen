package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.lieferanten.PreisListenPositionErsteller.FehlerBeimErstellenEinerPreislistenPosition;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;
import org.junit.*;
import static org.junit.Assert.*;

public class PreisListenPositionErstellerTest {

    Lieferant lieferant = erstelleLieferant();
    PreisListenPositionErsteller ersteller = new PreisListenPositionErsteller(lieferant);

    @Test
    public void testBurgerbroetchen() {
        String zeile = "10,,\"Burgerbroetchen\",,\"5,91\",1000";
        PreisListenPosition position = ersteller.erstelle(zeile);

        assertEquals("Burgerbroetchen", position.getNahrungsmittel().getName());
        assertEquals(10000, position.getNahrungsmittel().getVerfuegbareGesamtMenge());
        assertEquals(SpeisenUndNahrungsmittelKategorie.VEGETARISCH, position.getNahrungsmittel().getKategorie());
        assertEquals(lieferant, position.getLieferant());
    }

    @Test
    public void testRinderhack() {
        String zeile = "1000,\"g\",\"Rinderhack\",\"m\",\"7,96\",102";
        PreisListenPosition position = ersteller.erstelle(zeile);

        assertEquals("Rinderhack", position.getNahrungsmittel().getName());
        assertEquals(102000, position.getNahrungsmittel().getVerfuegbareGesamtMenge());
        assertEquals(SpeisenUndNahrungsmittelKategorie.FLEISCH, position.getNahrungsmittel().getKategorie());
        assertEquals(lieferant, position.getLieferant());
    }

    @Test(expected = FehlerBeimErstellenEinerPreislistenPosition.class)
    public void testPreislistenPositionErstellenFail() {
        String zeile = ",,,Bei einem Syntaxfehler wie diesem sollte ihr programm nicht abbrechen :-)";
        PreisListenPosition position = ersteller.erstelle(zeile);
        fail();
    }

    private Lieferant erstelleLieferant() {
        Lieferant lieferant;
        lieferant = new Lieferant() {
            @Override
            public double berechneLieferkosten(double einkaufsWert) {
                return 0.0;
            }
        };
        lieferant.setName("Testlieferant");
        return lieferant;
    }
}

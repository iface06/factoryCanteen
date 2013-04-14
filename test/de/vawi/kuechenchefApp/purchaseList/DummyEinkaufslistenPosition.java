package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.entities.Grosshaendler;
import de.vawi.kuechenchefApp.entities.Bauer;
import de.vawi.kuechenchefApp.lieferanten.*;
import de.vawi.kuechenchefApp.entities.Einheit;
import de.vawi.kuechenchefApp.entities.Nahrungsmittel;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;
import org.junit.Ignore;

/**
 *
 * @author Tatsch
 */
@Ignore
public class DummyEinkaufslistenPosition {

    EinkaufslistenPosition position = new EinkaufslistenPosition(new Nahrungsmittel());

    public DummyEinkaufslistenPosition fuerNahrungsmittel(String name) {
        position.getNahrungsmittel().setName(name);
        return this;
    }

    public DummyEinkaufslistenPosition einheit(Einheit einheit) {
        position.getNahrungsmittel().setEinheit(einheit);
        return this;
    }

    public DummyEinkaufslistenPosition menge(double menge) {
        position.setMenge(menge);
        return this;
    }

    public DummyEinkaufslistenPosition preis(double preis) {
        position.setPreis(preis);
        return this;
    }

    public DummyEinkaufslistenPosition vomBauer(String name, double distanzInKm) {
        position.setLieferant(new Bauer());
        position.getLieferant().setName(name);
        position.getLieferant().setLieferKostenFaktor(distanzInKm);
        return this;
    }

    public DummyEinkaufslistenPosition vomGrosshaendler(String name, double zuschlagsatz) {
        position.setLieferant(new Grosshaendler());
        position.getLieferant().setName(name);
        position.getLieferant().setLieferKostenFaktor(zuschlagsatz);
        return this;
    }

    public EinkaufslistenPosition erstelle() {
        return position;
    }

    public static EinkaufslistenPosition eier() {
        DummyEinkaufslistenPosition dummy = new DummyEinkaufslistenPosition();
        return dummy.fuerNahrungsmittel("Eier").menge(1000).einheit(Einheit.STUECK).preis(500).vomBauer("Heinrich", 10).erstelle();
    }
    
    public static EinkaufslistenPosition rinderhuefte() {
        DummyEinkaufslistenPosition dummy = new DummyEinkaufslistenPosition();
        return dummy.fuerNahrungsmittel("Argentinisches Rinderhüfte").einheit(Einheit.GRAMM).menge(10000).preis(300).vomGrosshaendler("Otto Gourmet", 1.1).erstelle();
    }
}

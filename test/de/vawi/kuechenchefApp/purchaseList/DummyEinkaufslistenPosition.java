package de.vawi.kuechenchefApp.purchaseList;

import de.vawi.kuechenchefApp.purchaseList.PurchaseListPosition;
import de.vawi.kuechenchefApp.entities.Distributer;
import de.vawi.kuechenchefApp.entities.Farmer;
import de.vawi.kuechenchefApp.entities.Unit;
import de.vawi.kuechenchefApp.entities.Food;
import de.vawi.kuechenchefApp.foods.SpeisenUndNahrungsmittelKategorie;
import org.junit.Ignore;

/**
 *
 * @author Tatsch
 */
@Ignore
public class DummyEinkaufslistenPosition {

    PurchaseListPosition position = new PurchaseListPosition(new Food());

    public DummyEinkaufslistenPosition fuerNahrungsmittel(String name) {
        position.getNahrungsmittel().setName(name);
        return this;
    }

    public DummyEinkaufslistenPosition einheit(Unit einheit) {
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
        position.setLieferant(new Farmer());
        position.getLieferant().setName(name);
        position.getLieferant().setLieferKostenFaktor(distanzInKm);
        return this;
    }

    public DummyEinkaufslistenPosition vomGrosshaendler(String name, double zuschlagsatz) {
        position.setLieferant(new Distributer());
        position.getLieferant().setName(name);
        position.getLieferant().setLieferKostenFaktor(zuschlagsatz);
        return this;
    }

    public PurchaseListPosition erstelle() {
        return position;
    }

    public static PurchaseListPosition eier() {
        DummyEinkaufslistenPosition dummy = new DummyEinkaufslistenPosition();
        return dummy.fuerNahrungsmittel("Eier").menge(1000).einheit(Unit.STUECK).preis(500).vomBauer("Heinrich", 10).erstelle();
    }
    
    public static PurchaseListPosition rinderhuefte() {
        DummyEinkaufslistenPosition dummy = new DummyEinkaufslistenPosition();
        return dummy.fuerNahrungsmittel("Argentinisches Rinderhüfte").einheit(Unit.GRAMM).menge(10000).preis(300).vomGrosshaendler("Otto Gourmet", 1.1).erstelle();
    }
}

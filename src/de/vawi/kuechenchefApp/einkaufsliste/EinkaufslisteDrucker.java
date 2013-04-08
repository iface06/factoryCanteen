package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.export.AsciiTable;
import de.vawi.kuechenchefApp.lieferanten.Lieferant;
import java.util.List;

/**
 * Diese Klasse schreibt die Einkaufslistenpositionen eines Lieferanten in einen String.
 * 
 * @author Lepping
 * @version 29.01.2013
 */
class EinkaufslisteDrucker {

    private String ausdruck = new String();

    String drucke(Lieferant lieferant, List<EinkaufslistenPosition> positionen) {
        ausdruck += "Einkaufsliste fuer Lieferanten " + lieferant.getName();
        ausdruck += "\n\n";
        ausdruck += druckeEinkaufslisteZuLieferanten(positionen);

        return ausdruck;
    }

    private String druckeEinkaufslisteZuLieferanten(List<EinkaufslistenPosition> positionen) {
        AsciiTable tabelle = new AsciiTable();
        tabelle.row().cell("Nahrungsmittel").cell("Menge (in Gebinde)").cell("Preis (Euro)");
        for (EinkaufslistenPosition position : positionen) {
            tabelle.row().cell(position.getName()).cell(String.valueOf(position.getMenge())).cell(String.valueOf(position.getPreis()));
        }
        return tabelle.writeTable();
    }
}

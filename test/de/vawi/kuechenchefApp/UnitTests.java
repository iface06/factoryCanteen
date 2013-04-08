
package de.vawi.kuechenchefApp;

import de.vawi.kuechenchefApp.dateien.export.AsciiTableTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    PlanungsPeriodeTest.class,
    de.vawi.kuechenchefApp.speisen.SpeisenImportTest.class,
    de.vawi.kuechenchefApp.lieferanten.LieferantenVerwaltungTest.class,
    de.vawi.kuechenchefApp.dateien.VawiDateiManagerTest.class,
    de.vawi.kuechenchefApp.dateien.CsvZeileSeperatorTest.class, 
    de.vawi.kuechenchefApp.dateien.ParseTest.class,
    de.vawi.kuechenchefApp.dateien.DateiLeserTest.class,
    de.vawi.kuechenchefApp.einkaufsliste.EinkaufslistenExportTest.class, 
    de.vawi.kuechenchefApp.einkaufsliste.EinkaufslistenErstellerTest.class,
    de.vawi.kuechenchefApp.einkaufsliste.EinkaufslisteTest.class,
    de.vawi.kuechenchefApp.einkaufsliste.KostenRechnerTest.class,
    de.vawi.kuechenchefApp.kostenaufstellung.KostenaufstellungTest.class,
    de.vawi.kuechenchefApp.kostenaufstellung.KostenaufstellungErstellerTest.class,
    de.vawi.kuechenchefApp.kostenaufstellung.KostenUebersichtDruckerTest.class,
    de.vawi.kuechenchefApp.lieferanten.PreisListenPositionTest.class,
    de.vawi.kuechenchefApp.lieferanten.PreisListenPositionErstellerTest.class,
    de.vawi.kuechenchefApp.lieferanten.LieferantenErstellerTest.class,
    de.vawi.kuechenchefApp.lieferanten.PreisListenTest.class,
    de.vawi.kuechenchefApp.lieferanten.NahrungsmittelErstellerTest.class,
    de.vawi.kuechenchefApp.speisen.SpeiseTest.class,
    de.vawi.kuechenchefApp.speisen.ZutatErstellerTest.class,
    de.vawi.kuechenchefApp.speisen.ZutatenKalkulatorTest.class,
    de.vawi.kuechenchefApp.speisen.SpeisenVerwaltungTest.class,
    de.vawi.kuechenchefApp.speisen.SpeisenErstellerTest.class,
    de.vawi.kuechenchefApp.speiseplan.SpeiseplanErstellerTest.class,
    de.vawi.kuechenchefApp.speiseplan.SpeiseplanExportTest.class,
    de.vawi.kuechenchefApp.speiseplan.KantineTest.class,
    de.vawi.kuechenchefApp.speiseplan.SpeiseplanDruckerTest.class,
    AsciiTableTest.class
})



public class UnitTests {

}
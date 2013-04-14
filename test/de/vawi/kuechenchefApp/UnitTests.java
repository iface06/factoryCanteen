package de.vawi.kuechenchefApp;

import de.vawi.kuechenchefApp.dateien.export.AsciiTableTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    PlanungsPeriodeTest.class,
    de.vawi.kuechenchefApp.purchaseList.EinkaufslistenExportTest.class,
    de.vawi.kuechenchefApp.purchaseList.EinkaufslistenErstellerTest.class,
    de.vawi.kuechenchefApp.purchaseList.EinkaufslisteTest.class,
    de.vawi.kuechenchefApp.purchaseList.KostenRechnerTest.class,
    de.vawi.kuechenchefApp.costs.KostenaufstellungTest.class,
    de.vawi.kuechenchefApp.costs.KostenaufstellungErstellerTest.class,
    de.vawi.kuechenchefApp.dishes.SpeiseTest.class,
    de.vawi.kuechenchefApp.dishes.ZutatenKalkulatorTest.class,
    de.vawi.kuechenchefApp.menues.SpeiseplanErstellerTest.class,
    de.vawi.kuechenchefApp.menues.KantineTest.class,
    AsciiTableTest.class
})
public class UnitTests {
}
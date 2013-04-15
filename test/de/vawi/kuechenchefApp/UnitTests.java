package de.vawi.kuechenchefApp;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    PlanungsPeriodeTest.class,
    de.vawi.kuechenchefApp.purchaseList.PurchaseListCreatorTest.class,
    de.vawi.kuechenchefApp.purchaseList.PurchaseListTest.class,
    de.vawi.kuechenchefApp.purchaseList.CostCalculatorTest.class,
    de.vawi.kuechenchefApp.costs.KostenaufstellungTest.class,
    de.vawi.kuechenchefApp.costs.KostenaufstellungErstellerTest.class,
    de.vawi.kuechenchefApp.dishes.SpeiseTest.class,
    de.vawi.kuechenchefApp.dishes.ZutatenKalkulatorTest.class,
    de.vawi.kuechenchefApp.menues.SpeiseplanErstellerTest.class,
    de.vawi.kuechenchefApp.menues.KantineTest.class
})
public class UnitTests {
}
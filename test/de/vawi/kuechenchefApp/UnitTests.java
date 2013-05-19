package de.vawi.kuechenchefApp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    PlanungsPeriodeTest.class,
    de.vawi.kuechenchefApp.entities.DishTest.class,
    de.vawi.kuechenchefApp.createMenues.MenusCreatorTest.class,
    de.vawi.kuechenchefApp.createMenues.KantineTest.class
})
public class UnitTests {
}

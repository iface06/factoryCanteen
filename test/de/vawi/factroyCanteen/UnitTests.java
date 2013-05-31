package de.vawi.factroyCanteen;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    PlanungsPeriodeTest.class,
    de.vawi.factoryCanteen.entities.DishTest.class,
    de.vawi.factoryCanteen.createMenu.MenusCreatorTest.class,
    de.vawi.factoryCanteen.createMenu.KantineTest.class
})
public class UnitTests {
}

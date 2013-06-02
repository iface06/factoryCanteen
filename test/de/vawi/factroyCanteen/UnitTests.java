package de.vawi.factroyCanteen;

import de.vawi.factoryCanteen.createMenu.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    PlanungsPeriodeTest.class,
    de.vawi.factoryCanteen.entities.DishTest.class,
    de.vawi.factoryCanteen.createMenu.KantineTest.class,
    AlternativeDishesRuleTest.class,
    CreateMenusInteractorTest.class,
    EveryDayDishMenuRuleTest.class,
    OffersForNextPeriodeCreatorTest.class,
    ThereIsFishOnFridayRuleTest.class
})
public class UnitTests {
}

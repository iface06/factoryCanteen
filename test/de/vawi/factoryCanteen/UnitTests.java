package de.vawi.factoryCanteen;

import de.vawi.factoryCanteen.app.createMenu.AlternativeDishesRuleTest;
import de.vawi.factoryCanteen.app.createMenu.MenuCreatorTest;
import de.vawi.factoryCanteen.app.createMenu.CreateMenusInteractorTest;
import de.vawi.factoryCanteen.app.createMenu.EveryDayDishMenuRuleTest;
import de.vawi.factoryCanteen.app.createMenu.FishOnFridayRuleTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    PlanungsPeriodeTest.class,
    de.vawi.factoryCanteen.app.entities.DishTest.class,
    de.vawi.factoryCanteen.app.createMenu.KantineTest.class,
    AlternativeDishesRuleTest.class,
    CreateMenusInteractorTest.class,
    EveryDayDishMenuRuleTest.class,
    MenuCreatorTest.class,
    FishOnFridayRuleTest.class
})
public class UnitTests {
}

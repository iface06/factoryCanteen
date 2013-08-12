package de.vawi.factoryCanteen;

import de.vawi.factoryCanteen.app.createMenu.*;
import de.vawi.factoryCanteen.app.entities.*;
import de.vawi.factoryCanteen.app.findMenu.FindMenuInteractorTest;
import de.vawi.factoryCanteen.persistence.FileSystem;
import de.vawi.factoryCanteen.persistence.dishes.*;
import de.vawi.factoryCanteen.persistence.files.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    PeriodeConfigurationTest.class,
    DishTest.class,
    CalendarWeekTest.class,
    DishCategoryTest.class,
    CsvLineSeperatorTest.class,
    CsvFileReaderTest.class,
    CreateMenusInteractorTest.class,
    DishesImportTest.class,
    DishesDBTest.class,
    DishCreatorTest.class,
    FindMenuInteractorTest.class,
    MenuCreatorTest.class,
    AlternativeDishesRuleTest.class,
    EveryDayDishMenuRuleTest.class,
    CreateMenusInteractorTest.class,
    FishOnFridayRuleTest.class,
    NoReplicationRuleTest.class
})
public class UnitTests {
}

package de.vawi.kuechenchefApp;

import de.vawi.kuechenchefApp.speisen.ImportIntegrationTest;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    de.vawi.kuechenchefApp.dateien.DateiSchreiberTest.class,
    de.vawi.kuechenchefApp.lieferanten.PreisListenImportIntegrationsTest.class,
    de.vawi.kuechenchefApp.lieferanten.LieferantenImportIntegrationTest.class,
    de.vawi.kuechenchefApp.speisen.ImportIntegrationTest.class
})
public class IntegrationTests {
}
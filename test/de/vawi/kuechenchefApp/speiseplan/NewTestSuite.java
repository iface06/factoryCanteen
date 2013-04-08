
package de.vawi.kuechenchefApp.speiseplan;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    de.vawi.kuechenchefApp.speiseplan.SpeiseplanErstellerTest.class,
    de.vawi.kuechenchefApp.speiseplan.SpeiseplanExportTest.class,
    de.vawi.kuechenchefApp.speiseplan.KantineTest.class,
    de.vawi.kuechenchefApp.speiseplan.SpeiseplanDruckerTest.class})
public class NewTestSuite {

}
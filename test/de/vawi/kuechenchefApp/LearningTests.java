
package de.vawi.kuechenchefApp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    de.vawi.kuechenchefApp.learningTests.MathCeilTest.class,
    de.vawi.kuechenchefApp.learningTests.GoogleAsciiTableTest.class,
    de.vawi.kuechenchefApp.learningTests.ReadLineLearningTest.class,
    de.vawi.kuechenchefApp.learningTests.TaschenrechnerTest.class
})
public class LearningTests {

}

package de.vawi.factoryCanteen;

import de.vawi.factoryCanteen.learningTests.JodaLearningTest;
import de.vawi.factoryCanteen.learningTests.MathCeilTest;
import de.vawi.factoryCanteen.learningTests.persistence.ObjectSerializerLearningTest;
import de.vawi.factoryCanteen.learningTests.persistence.ReadLineLearningTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    MathCeilTest.class,
    JodaLearningTest.class,
    ReadLineLearningTest.class,
    ObjectSerializerLearningTest.class
})
public class LearningTests {

}
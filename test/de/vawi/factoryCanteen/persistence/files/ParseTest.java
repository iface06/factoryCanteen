

package de.vawi.factoryCanteen.persistence.files;

import de.vawi.factoryCanteen.persistence.files.Parse;
import de.vawi.factoryCanteen.persistence.files.Parse.FehlerBeimParsen;
import org.junit.*;
import static org.junit.Assert.*;


public class ParseTest {

    @Test
    public void testParseToDouble() {
        String value = "3,14";
        double pi = Parse.toDouble(value);
        assertEquals(3.14, pi, 0.001);
    }
    
    @Test(expected=FehlerBeimParsen.class)
    public void testParseToDoubleFail() {
        String value = "";
        double pi = Parse.toDouble(value);
        fail();
    }
    
    @Test
    public void testParseToInteger(){
        String value = "1";
        int eins = Parse.toInteger(value);
        assertEquals(1, eins);
    }
    
    @Test(expected=FehlerBeimParsen.class)
    public void testParseToIntegerFail(){
        String value = "a";
        int eins = Parse.toInteger(value);
        fail();
    }

}
package de.vawi.factoryCanteen.learningTests;

import org.junit.Test;
import static org.junit.Assert.*;

public class MathCeilTest {
    
    @Test
    public void testCeilLimit() {
        Double gerundet = Math.ceil(1.5);    
        assertEquals(2.0, gerundet, 0.0001);
    }
    
    @Test
    public void testCeilDoubleBehindLimit() {
        Double gerundet = Math.ceil(1.2);
        assertEquals(2.0, gerundet, 0.0001);
    }
    @Test
    public void testCeilDoubleOverLimit() {
        Double gerundet = Math.ceil(1.7);
        assertEquals(2.0, gerundet, 0.0001);
    }
    
    @Test
    public void testSplitVonDouble() {
        double variable = 1.05;
        variable =  Math.floor(variable);
        System.out.print(variable);
        

    }
}

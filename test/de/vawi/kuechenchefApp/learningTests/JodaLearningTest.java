

package de.vawi.kuechenchefApp.learningTests;


import java.util.Date;
import org.joda.time.DateTime;
import org.junit.*;
import static org.junit.Assert.*;


public class JodaLearningTest {
    
    @Test
    public void testGetCalendarWeekFromDate(){
        DateTime dt = new DateTime().withDate(2013, 4, 20);
        int calendarWeek = dt.getWeekOfWeekyear();
        
        assertEquals(16, calendarWeek);
    }
    
    @Test
    public void testGetYearFromDate(){
        DateTime dt = new DateTime().withDate(2013, 4, 20);
        int year = dt.getYear();
        
        assertEquals(2013, year);
    }
}
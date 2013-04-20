

package de.vawi.kuechenchefApp.entities;

import java.util.Date;
import org.joda.time.DateTime;
import org.junit.*;
import static org.junit.Assert.*;


public class CalendarWeekTest {

    @Test
    public void testCreateCurrentCalendarWeek() {
        DateTime d = new DateTime();
        CalendarWeek week = CalendarWeek.current();
        
        assertEquals(d.getYear(), week.getYear());
        assertEquals(d.getWeekOfWeekyear(), week.getWeek());
    }
    
    @Test
    public void testCreateCalendarWeekFromDate() {
        Date d = createDate(2013, 4, 20);
        CalendarWeek week = CalendarWeek.fromDate(d);
        
        assertEquals(2013, week.getYear());
        assertEquals(16, week.getWeek());
    }
    
    @Test
    public void testEqual(){
        Date d = createDate(2013, 4, 20);
        CalendarWeek week1 = CalendarWeek.fromDate(d);
        CalendarWeek week2 = CalendarWeek.fromDate(d);
        
        assertTrue(week1.equals(week2));
    }
    
    @Test
    public void testGeaterThen(){
        Date d1 = createDate(2013, 4, 20);
        CalendarWeek week1 = CalendarWeek.fromDate(d1);
        Date d2 = createDate(2013, 4, 1);
        CalendarWeek week2 = CalendarWeek.fromDate(d2);
        
        assertTrue(week1.compareTo(week2) > 0);
    }
    
    @Test
    public void testLessThen(){
        Date d1 = createDate(2013, 4, 1);
        CalendarWeek week1 = CalendarWeek.fromDate(d1);
        Date d2 = createDate(2013, 4, 20);
        CalendarWeek week2 = CalendarWeek.fromDate(d2);
        
        assertTrue(week1.compareTo(week2) < 0);
    }
    
    @Test
    public void testEqualsThen(){
        Date d1 = createDate(2013, 4, 20);
        CalendarWeek week1 = CalendarWeek.fromDate(d1);
        Date d2 = createDate(2013, 4, 20);
        CalendarWeek week2 = CalendarWeek.fromDate(d2);
        
        assertTrue(week1.compareTo(week2) == 0);
    }
    
    @Test
    public void testNotEqual(){
        Date d1 = createDate(2013, 4, 20);
        CalendarWeek week1 = CalendarWeek.fromDate(d1);
        Date d2 = createDate(2013, 4, 1);
        CalendarWeek week2 = CalendarWeek.fromDate(d2);
        
        assertFalse(week1.equals(week2));
    }
    
    @Test
    public void testHashEquals(){
        Date d = createDate(2013, 4, 20);
        CalendarWeek week1 = CalendarWeek.fromDate(d);
        CalendarWeek week2 = CalendarWeek.fromDate(d);
        
        assertTrue(week1.hashCode() == week2.hashCode());
    }
    
    @Test
    public void testHashNotEquals(){
        Date d1 = createDate(2013, 4, 20);
        CalendarWeek week1 = CalendarWeek.fromDate(d1);
        Date d2 = createDate(2013, 4, 1);
        CalendarWeek week2 = CalendarWeek.fromDate(d2);
        
        assertTrue(week1.hashCode() != week2.hashCode());
    }

    private Date createDate(int year, int month, int day) {
        Date d1 = new DateTime().withDate(year, month, day).toDate();
        return d1;
    }

}
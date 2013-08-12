package de.vawi.factoryCanteen.learningTests;

import de.vawi.factoryCanteen.app.entities.CalendarWeek;
import java.util.*;
import org.joda.time.*;
import org.junit.*;
import static org.junit.Assert.*;

public class JodaTimeLearningTest {

    private DateTime date;

    @Before
    public void before() {
        date = new DateTime().withDate(2013, 4, 20);

    }

    @Test
    public void testGetCalendarWeekFromDate() {
        int calendarWeek = date.getWeekOfWeekyear();

        assertEquals(16, calendarWeek);
    }

    @Test
    public void testGetYearFromDate() {

        int year = date.getYear();

        assertEquals(2013, year);
    }

    @Test
    public void testGetDaysOfCalendarWeek() {

        List<Date> workingDays = new ArrayList<>();
        for (int day = DateTimeConstants.MONDAY; day <= DateTimeConstants.FRIDAY; day++) {
            workingDays.add(date.withDayOfWeek(day).toDate());
        }

        assertWeekDays(workingDays);
    }

    private void assertWeekDays(List<Date> workingDays) {
        assertTrue(workingDays.size() == 5);
        int expectedDay = DateTimeConstants.MONDAY;
        for (Date date : workingDays) {
            assertEquals(expectedDay, new DateTime(date).getDayOfWeek());
            expectedDay++;
        }
    }
}

package com.jchirp.test.console.formatters;

import com.jchirp.console.formatters.TimeForDisplay;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeForDisplayTest {

    private TimeForDisplay timeForDisplay;

    private void assertTimeSpan(String first, String second, String expected) {
        assertEquals(expected, timeForDisplay.timeSpanBetween(new DateTime(first),new DateTime(second)));
    }

    @Before
    public void setUp(){
        timeForDisplay = new TimeForDisplay();
    }

    @Test public void
    calculateTimeDifferenceIn(){
        assertTimeSpan("2000-01-01T00:00:00.000-00:00", "2000-01-01T00:00:01.000-00:00", "(1 second)");
        assertTimeSpan("2000-01-01T00:00:00.000-00:00", "2000-01-01T00:00:02.000-00:00", "(2 seconds)");
        assertTimeSpan("2000-01-01T00:00:00.000-00:00", "2000-01-01T00:01:01.000-00:00", "(1 minute)");
        assertTimeSpan("2000-01-01T00:00:00.000-00:00", "2000-01-01T01:00:00.000-00:00", "(1 hour)");
        assertTimeSpan("2000-01-01T00:00:00.000-00:00", "2000-01-02T01:00:00.000-00:00", "(1 day)");
        assertTimeSpan("2000-01-01T00:00:00.000-00:00", "2000-02-01T01:00:00.000-00:00", "(1 month)");
        assertTimeSpan("2000-01-01T00:00:00.000-00:00", "2001-01-01T01:00:00.000-00:00", "(1 year)");
    }

    @Test public void
    orderDoesNotMatter(){
        assertTimeSpan("2001-01-01T00:00:00.000-00:00", "2000-01-01T00:00:00.000-00:00", "(1 year)");
    }
}
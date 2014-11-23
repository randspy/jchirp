package com.jchirp.console.formatters;

import org.joda.time.*;

import java.util.ArrayList;
import java.util.List;

public class TimeSpan {
    public String timeSpanBetween(DateTime start, DateTime end) {

        if (start.isAfter(end)) {
            DateTime temp = start;
            start = end;
            end = temp;
        }

        List<String> timeSpans = new ArrayList<String>();
        timeSpans.add(timeSpanInYears(start, end));
        timeSpans.add(timeSpanInMonths(start, end));
        timeSpans.add(timeSpanInDays(start, end));
        timeSpans.add(timeSpanInHours(start, end));
        timeSpans.add(timeSpanInMinute(start, end));
        timeSpans.add(timeSpanInSeconds(start, end));

        for(String timeSpan : timeSpans){
            if (!timeSpan.isEmpty()){
                return timeSpan;
            }
        }
        return "";
    }

    private String timeSpanInYears(DateTime start, DateTime end) {
        Years years = Years.yearsBetween(start, end);
        return years.getYears() > 0 ? formatOutput(years.getYears(), "year") : "";
    }

    private String timeSpanInMonths(DateTime start, DateTime end) {
        Months months = Months.monthsBetween(start, end);
        return months.getMonths() > 0 ? formatOutput(months.getMonths(), "month") : "";
    }

    private String timeSpanInDays(DateTime start, DateTime end) {
        Days days = Days.daysBetween(start, end);
        return days.getDays() > 0 ? formatOutput(days.getDays(), "day") : "";
    }

    private String timeSpanInMinute(DateTime start, DateTime end) {
        Minutes minutes = Minutes.minutesBetween(start, end);
        return minutes.getMinutes() > 0 ? formatOutput(minutes.getMinutes(), "minute"): "";
    }

    private String timeSpanInHours(DateTime start, DateTime end) {
        Hours hours = Hours.hoursBetween(start, end);
        return hours.getHours() > 0 ? formatOutput(hours.getHours(), "hour") : "";
    }

    private String timeSpanInSeconds(DateTime start, DateTime end) {
        Seconds seconds = Seconds.secondsBetween(start, end);

        int secondsValue = seconds.getSeconds();
        return formatOutput(secondsValue, "second");
    }

    private String formatOutput(int value, String unit) {
        return "(" + String.valueOf(value) +  " " + unit +
                ((value == 1) ? ")" : "s)");
    }
}

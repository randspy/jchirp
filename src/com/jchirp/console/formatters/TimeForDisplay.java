package com.jchirp.console.formatters;

import org.joda.time.*;

import java.util.ArrayList;
import java.util.List;

public class TimeForDisplay {
    private DateTime start;
    private DateTime end;
    private List<String> timeSpans;

    public String timeSpanBetween(DateTime starts, DateTime ends) {

        init(starts, ends);

        swapWhenStartIsAfterEnd();

        timeSpans.add(timeSpanForYears());
        timeSpans.add(timeSpanForMonths());
        timeSpans.add(timeSpanForDays());
        timeSpans.add(timeSpanForHours());
        timeSpans.add(timeSpanForMinute());
        timeSpans.add(timeSpanForSeconds());

        return buildOutput();
    }

    private void init(DateTime start, DateTime end) {
        this.start = start;
        this.end = end;
        timeSpans = new ArrayList<String>();
    }

    private void swapWhenStartIsAfterEnd() {
        if (start.isAfter(end)) {
            DateTime temp = start;
            start = end;
            end = temp;
        }
    }

    private String buildOutput() {
        for(String timeSpan : timeSpans){
            if (!timeSpan.isEmpty())
            {
                return timeSpan;
            }
        }
        return "";
    }

    private String timeSpanForYears() {
        Years years = Years.yearsBetween(start, end);
        return years.getYears() > 0 ? formatOutput(years.getYears(), "year") : "";
    }

    private String timeSpanForMonths() {
        Months months = Months.monthsBetween(start, end);
        return months.getMonths() > 0 ? formatOutput(months.getMonths(), "month") : "";
    }

    private String timeSpanForDays() {
        Days days = Days.daysBetween(start, end);
        return days.getDays() > 0 ? formatOutput(days.getDays(), "day") : "";
    }

    private String timeSpanForMinute() {
        Minutes minutes = Minutes.minutesBetween(start, end);
        return minutes.getMinutes() > 0 ? formatOutput(minutes.getMinutes(), "minute"): "";
    }

    private String timeSpanForHours() {
        Hours hours = Hours.hoursBetween(start, end);
        return hours.getHours() > 0 ? formatOutput(hours.getHours(), "hour") : "";
    }

    private String timeSpanForSeconds() {
        Seconds seconds = Seconds.secondsBetween(start, end);

        int secondsValue = seconds.getSeconds();
        return formatOutput(secondsValue, "second");
    }

    private String formatOutput(int value, String unit) {
        return "(" + String.valueOf(value) + " " + formatTimeUnit(value, unit);
    }

    private String formatTimeUnit(int value, String unit) {
        return unit + (isNumberSingular(value) ? ")" : "s)");
    }

    private boolean isNumberSingular(int value) {
        return value == 1;
    }
}

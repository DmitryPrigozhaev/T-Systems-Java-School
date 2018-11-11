package com.railwaycompany.utils;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    private static final Logger LOG = Logger.getLogger(DateConverter.class.getName());

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private static final String DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm";

    private static final long MILLIS_IN_10_MINUTES = 600_000;

    public static Date convertDate(final String dateStr) {
        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            LOG.warn("Cannot parse date string \"" + dateStr + "\"", e);
        } catch (NumberFormatException e) {
            LOG.warn("dateStr: " + dateStr, e);
        } catch (Exception e) {
            LOG.warn(e);
        }
        return date;
    }

    public static Date convertDatetime(String datetimeStr) {
        Date datetime = null;
        try {
            SimpleDateFormat datetimeFormat = new SimpleDateFormat(DATETIME_PATTERN);
            datetime = datetimeFormat.parse(datetimeStr);
        } catch (ParseException e) {
            LOG.warn("Cannot parse datetime string \"" + datetimeStr + "\"", e);
        }
        return datetime;
    }

    public static boolean hasMoreThanTenMinutes(Date date) {
        if (date != null) {
            long timeLeft = date.getTime() - new Date().getTime();
            return timeLeft >= MILLIS_IN_10_MINUTES;
        }
        return false;
    }
}

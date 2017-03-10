package com.danielgauci.gittr.utils;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import timber.log.Timber;

/**
 * Created by daniel on 3/10/17.
 */

public class DateUtils {

    public static String formatDate(String utcFormat, PrettyTime prettyTime){
        // Set date
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date createdAt = dateFormat.parse(utcFormat);
            return prettyTime.format(createdAt);
        } catch (ParseException e){
            Timber.e("Failed to parse event time", e);
            return "moments ago";
        }
    }
}

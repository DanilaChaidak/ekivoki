package edu.javagroup.ekivoki.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import static edu.javagroup.ekivoki.constants.Constants.DATE_TIME_PATTERN;

public class DateUtils {

    public static String getDate(Date source) {
        return new SimpleDateFormat(DATE_TIME_PATTERN).format(source);
    }
}

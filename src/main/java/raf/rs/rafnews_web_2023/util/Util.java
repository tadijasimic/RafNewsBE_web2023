package raf.rs.rafnews_web_2023.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Util {
    public static Timestamp parseDateString(String dateString) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        try {
            java.util.Date date = dateFormat.parse(dateString);
            long time = date.getTime();
            return new Timestamp(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null; // Parsing failed, return null or handle the error accordingly
    }
}

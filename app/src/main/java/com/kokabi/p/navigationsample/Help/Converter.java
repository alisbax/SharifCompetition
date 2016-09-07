package com.kokabi.p.navigationsample.Help;

import android.content.Context;
import android.content.res.Resources;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by R.Miri on 1/11/2016.
 */
public class Converter {

    private static final long ONE_DAY = 1440 * 60 * 1000;
    private static final long TWO_DAYS = 2880 * 60 * 1000;
    static long timeStamp;

    public static float dpToPixel(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static float pixelsToDp(Context context, float px) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static float pixelsToSp(Context context, Float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px / scaledDensity;
    }

    public static String addCommaSeparator(String str) {
        if (str.length() > 0) {
            String str2 = str.replaceAll("[,]", "");
            BigDecimal parsed = new BigDecimal(str2);
            return NumberFormat.getNumberInstance(Locale.US).format(parsed);
        } else {
            return "0";
        }
    }

    public static String removeCommaSeparator(String str) {
        if (str.length() > 0) {
            return str.replaceAll("[,]", "");
        } else {
            return "0";
        }
    }

    public static String arrayToStringWithComma(ArrayList<String> inputData) {
        return inputData.toString().replace("[", "").replace("]", "")
                .replace(", ", ",");
    }

    public static String getTime(String timeStampStr) {
        timeStamp = (long) Double.parseDouble(timeStampStr);
        if (isDateToday(timeStamp)) {
            return ("امروز");
        } else {
            Date date = new Date(timeStamp * 1000);
            int year = Integer.parseInt(DateFormat.format("yyyy", date).toString());
            int month = Integer.parseInt(DateFormat.format("MM", date).toString());
            int day = Integer.parseInt(DateFormat.format("dd", date).toString());
            DateConverter jCal = new DateConverter();
            jCal.GregorianToPersian(year, month, day);
            return (String.valueOf(jCal.getYear() + "/" + jCal.getMonth() + "/" + jCal.getDay()));
        }
    }

    public static boolean isDateToday(long milliSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);

        Date getDate = calendar.getTime();

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date startDate = calendar.getTime();

        return getDate.compareTo(startDate) > 0;
    }
}

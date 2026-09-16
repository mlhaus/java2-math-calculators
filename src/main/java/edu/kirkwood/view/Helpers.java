package edu.kirkwood.view;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Helpers {
    /**
     * Determines whether a string is non-null and nonempty.
     *
     * @param str string to evaluate
     * @return {@code true} when the string contains at least one character
     */
    public static boolean isValidString(String str) {
        return str != null && !str.equals("");
    }

    /**
     * Rounds a number using half-up rounding and removes trailing zeroes.
     *
     * @param number number to round
     * @param numDecPlaces number of decimal places to retain
     * @return the rounded number as a string
     */
    public static String round(double number, int numDecPlaces) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(number));
        bigDecimal = bigDecimal.setScale(numDecPlaces, RoundingMode.HALF_UP).stripTrailingZeros();
        return bigDecimal.toString();
    }

    /**
     * Formats an amount using the default locale's currency format.
     *
     * @param amt amount to format
     * @return the formatted currency amount
     */
    public static String toCurrency(double amt) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(amt);
    }

    /**
     * Formats a date using the default locale's long date style.
     *
     * @param date date to format
     * @return the long-form date
     */
    public static String formatDateLong(LocalDate date) {
        DateTimeFormatter dateFormatOutput = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        return dateFormatOutput.format(date);
    }

    /**
     * Formats a date as {@code M/d/yyyy}.
     *
     * @param date date to format
     * @return the short-form date
     */
    public static String formatDateShort(LocalDate date) {
        DateTimeFormatter dateFormatOutput = DateTimeFormatter.ofPattern("M/d/yyyy");
        return dateFormatOutput.format(date);
    }

    /**
     * Determines whether a date occurs before the current date.
     *
     * @param date date to evaluate
     * @return {@code true} if the date is before today
     * @throws IllegalArgumentException if {@code date} is {@code null}
     */
    public static boolean isDateInThePast(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        return date.isBefore(LocalDate.now()); // Check if the date is before today
    }

    /**
     * Determines whether a date falls within an inclusive date range.
     *
     * @param date date to evaluate
     * @param startDate first date in the range
     * @param endDate last date in the range
     * @return {@code true} if the date is on or between the range boundaries
     * @throws IllegalArgumentException if any date argument is {@code null}
     */
    public static boolean isDateInRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        if (date == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("None of the dates can be null");
        }

        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                (date.isEqual(endDate) || date.isBefore(endDate));
    }
}
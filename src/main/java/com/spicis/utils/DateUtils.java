package com.spicis.utils;

import com.spicis.logger.LogFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static LocalDate nowLocalDate() {
        LocalDateTime nowLocalDateTime = now();
        return nowLocalDateTime.toLocalDate();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now(ZoneId.systemDefault());
    }

    public static Date nowDate() {
        return new Date();
    }

    public static String[] NUMBER_CHINESE_NAME = {"", "一", "二",  "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};

    public static String formatDate(Date date, String pattern) {
        try {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();

            LocalDate localDate = instant.atZone(zoneId).toLocalDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return localDate.format(formatter);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("formatDate", e);
            return "";
        }
    }

    public static String formatDateTime(Date date, String pattern) {
        try {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();

            LocalDateTime localDate = instant.atZone(zoneId).toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return localDate.format(formatter);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("formatDateTime", e);
            return "";
        }
    }

    public static String formatDateTime(String input, String inputFormat, String outputFormat) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);
            LocalDateTime localDateTime = LocalDateTime.parse(input, inputFormatter);

            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);
            return localDateTime.format(outputFormatter);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("formatDateTime", e);
            return "";
        }
    }

    public static LocalDateTime formatDateTime(String input, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime localDateTime = LocalDateTime.parse(input, formatter);
            return localDateTime;
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("formatDateTime", e);
            return null;
        }
    }

    public static LocalDate formatDate(String input, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate localDate = LocalDate.parse(input, formatter);
            return localDate;
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("formatDate", e);
            return null;
        }
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static Date localDateTimeToDate(String input, String pattern) {
        LocalDateTime localDate = formatDateTime(input, pattern);
        return localDateTimeToDate(localDate);
    }

    public static Date localDateToDate(String input, String pattern) {
        LocalDate localDate = formatDate(input, pattern);
        return localDateToDate(localDate);
    }

    public String getChineseNameByNum(Integer number) {
        if (number != null && number <= 12) {
            return NUMBER_CHINESE_NAME[number];
        } else {
            return number + "";
        }
    }

}
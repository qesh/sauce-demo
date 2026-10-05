package com.framework.utils;

import com.framework.constants.FrameworkConstants;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** Thread-safe facade over Java Faker (one Faker instance per thread). */
public final class TestDataGenerator {

    private static final ThreadLocal<Faker> FAKER = ThreadLocal.withInitial(() -> new Faker(Locale.US));
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern(FrameworkConstants.DATE_FORMAT);

    private TestDataGenerator() {
    }

    public static String firstName() {
        return FAKER.get().name().firstName();
    }

    public static String lastName() {
        return FAKER.get().name().lastName();
    }

    public static String postalCode() {
        return FAKER.get().address().zipCode();
    }

    public static String jobTitle() {
        return FAKER.get().job().title();
    }

    public static int numberBetween(int minInclusive, int maxExclusive) {
        return FAKER.get().number().numberBetween(minInclusive, maxExclusive);
    }

    public static boolean bool() {
        return FAKER.get().bool().bool();
    }

    /** ISO date ({@code yyyy-MM-dd}) offset from today. */
    public static String dateFromToday(int days) {
        return LocalDate.now().plusDays(days).format(DATE);
    }
}

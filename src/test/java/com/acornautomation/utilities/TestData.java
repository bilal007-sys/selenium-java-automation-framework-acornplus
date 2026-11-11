package com.acornautomation.utilities;

import net.datafaker.Faker;
import java.util.UUID;

public class TestData {
    private static final Faker faker = new Faker();

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getFullName() {
        return faker.name().fullName();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getPhone() {
        return faker.phoneNumber().cellPhone();
    }

    public static String getAddress() {
        return faker.address().streetAddress();
    }

    public static String getCity() {
        return faker.address().city();
    }

    public static String getState() {
        return faker.address().stateAbbr();
    }

    public static String getZipCode() {
        return faker.address().zipCode();
    }

    public static String getPetName() {
        return faker.dog().name();
    }

    public static String getParagraph() {
        return faker.lorem().paragraph();
    }
    
    public static String getUniqueEmail() {
        return faker.name().firstName().toLowerCase() + "_" + UUID.randomUUID().toString().substring(0, 8) + "@gmail.com";
    }
}
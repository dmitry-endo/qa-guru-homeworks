package utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.*;

public class RandomUtils {

    private static final Faker FAKER = new Faker(new Locale("en-IND"));
    private static final String[] GENDERS = {"Male", "Female", "Other"};
    private static final String[] SUBJECTS = {
            "Chemistry", "Maths", "Physics", "Arts", "English",
            "Biology", "History", "Economics", "Computer Science"};
    private static final String[] HOBBIES = {"Sports", "Reading", "Music"};
    private static final String[] STATES = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    private static final Date BIRTHDAY_DATE = FAKER.date().birthday();
    private static final SimpleDateFormat DAY_FORMATTER = new SimpleDateFormat("d", Locale.ENGLISH);
    private static final SimpleDateFormat MONTH_FORMATTER = new SimpleDateFormat("MMMM", Locale.ENGLISH);
    private static final SimpleDateFormat YEAR_FORMATTER = new SimpleDateFormat("yyyy", Locale.ENGLISH);

    public static String getRandomFirstName() {
        return FAKER.name().firstName();
    }

    public static String getRandomLastName() {
        return FAKER.name().lastName();
    }

    public static String getRandomEmail() {
        return FAKER.internet().emailAddress();
    }

    public static String getRandomGender() {
        return FAKER.options().option(GENDERS);
    }

    public static String getRandomMobile() {
        return FAKER.phoneNumber().subscriberNumber(10);
    }

    public static String getRandomHobby() {
        return FAKER.options().option(HOBBIES);
    }

    public static String getRandomSubject() {
        return FAKER.options().option(SUBJECTS);
    }

    public static String getRandomPicture() {
        return FAKER.options().option("avatar1.jpg", "avatar2.png");
    }

    public static String getRandomStreetAddress() {
        return FAKER.address().streetAddress();
    }

    public static String getRandomState() {
        return FAKER.options().option(STATES);
    }

    public static String getRandomCity(String state) {
        return switch (state) {
            case "NCR" -> FAKER.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> FAKER.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> FAKER.options().option("Karnal", "Panipat");
            case "Rajasthan" -> FAKER.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };
    }

    public static String getRandomBirthDay() {
        return DAY_FORMATTER.format(BIRTHDAY_DATE);
    }

    public static String getRandomBirthMonth() {
        return MONTH_FORMATTER.format(BIRTHDAY_DATE);
    }

    public static String getRandomBirthYear() {
        return YEAR_FORMATTER.format(BIRTHDAY_DATE);
    }
}

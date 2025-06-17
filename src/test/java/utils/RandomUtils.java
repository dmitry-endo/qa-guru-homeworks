package utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    private static final Faker FAKER = new Faker(new Locale("en-IND"));
    private static final Random RANDOM = ThreadLocalRandom.current();

    private static final String[] GENDERS = {"Male", "Female", "Other"};
    private static final String[] SUBJECTS = {
            "Chemistry", "Maths", "Physics", "Arts", "English",
            "Biology", "History", "Economics", "Computer Science"};
    private static final String[] HOBBIES = {"Sports", "Reading", "Music"};
    private static final Date BIRTHDAY_DATE = FAKER.date().birthday();

    private static final Map<String, List<String>> STATE_CITIES = Map.of(
            "NCR", List.of("Delhi", "Gurgaon", "Noida"),
            "Uttar Pradesh", List.of("Agra", "Lucknow", "Merrut"),
            "Haryana", List.of("Karnal", "Panipat"),
            "Rajasthan", List.of("Jaipur", "Jaiselmer")
    );

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

    public static String[] getRandomHobbies(int numOfHobbies) {
        if (numOfHobbies < 1 || numOfHobbies > HOBBIES.length) {
            throw new IllegalArgumentException("Count must be between 1 and " + HOBBIES.length);
        }

        List<String> shuffled = new ArrayList<>(Arrays.asList(HOBBIES));
        Collections.shuffle(shuffled);
        return shuffled.subList(0, numOfHobbies).toArray(new String[0]);
    }

    public static String[] getRandomSubjects(int numOfSubjects) {
        if (numOfSubjects < 1 || numOfSubjects > SUBJECTS.length) {
            throw new IllegalArgumentException("Count must be between 1 and " + SUBJECTS.length);
        }

        List<String> shuffled = new ArrayList<>(Arrays.asList(SUBJECTS));
        Collections.shuffle(shuffled);
        return shuffled.subList(0, numOfSubjects).toArray(new String[0]);
    }

    public static String getRandomStreetAddress() {
        return FAKER.address().streetAddress();
    }

    public static String getRandomState() {
        // Convert keys to list for random access
        List<String> states = new ArrayList<>(STATE_CITIES.keySet());
        return states.get(RANDOM.nextInt(states.size()));
    }

    public static String getRandomCity(String state) {
        List<String> cities = STATE_CITIES.get(state);

        if (cities == null) {
            throw new IllegalArgumentException("Invalid state: " + state);
        }

        return cities.get(RANDOM.nextInt(cities.size()));
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

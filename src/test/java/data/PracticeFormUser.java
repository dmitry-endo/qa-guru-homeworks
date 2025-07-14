package data;

import static utils.RandomUtils.*;

public class PracticeFormUser {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String gender;
    private final String userNumber;
    private final String day;
    private final String month;
    private final String year;
    private final String subject;
    private final String hobby;
    private final String picture;
    private final String currentAddress;
    private final String state;
    private final String city;

    public PracticeFormUser() {
        this.firstName = getRandomFirstName();
        this.lastName = getRandomLastName();
        this.email = getRandomEmail();
        this.gender = getRandomGender();
        this.userNumber = getRandomMobile();
        this.day = getRandomBirthDay();
        this.month = getRandomBirthMonth();
        this.year = getRandomBirthYear();
        this.subject = getRandomSubject();
        this.hobby = getRandomHobby();
        this.picture = getRandomPicture();
        this.currentAddress = getRandomStreetAddress();
        this.state = getRandomState();
        this.city = getRandomCity(state);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getSubject() {
        return subject;
    }

    public String getHobby() {
        return hobby;
    }

    public String getPicture() {
        return picture;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    // Combined fields
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFullBirthDate() {
        return String.format("%s %s,%s", day, month, year);
    }

    public String getStateAndCity() {
        return state + " " + city;
    }
}

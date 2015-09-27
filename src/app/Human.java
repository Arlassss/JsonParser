package app;

import annotations.CustomDateFormat;
import annotations.JsonValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;


public class Human {

    private String firstName;
    private String lastName;

    @JsonValue(name = "fun")
    private String hobby;

    @CustomDateFormat(format = "dd-MM-yyyy")
    private LocalDate birthDate;

    public Human() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = birthDate;
        Date date = formatter.parse(dateInString);
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        this.birthDate = zdt.toLocalDate();


    }

    @Override
    public String toString() {
        return "Human{" +
                "firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' +
                ", hobby='" + this.hobby + '\'' +
                ", birthDate=" + this.birthDate +
                '}';
    }
}

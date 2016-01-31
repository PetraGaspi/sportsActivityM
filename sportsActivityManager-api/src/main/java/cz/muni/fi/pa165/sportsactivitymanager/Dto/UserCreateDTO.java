package cz.muni.fi.pa165.sportsactivitymanager.Dto;

import cz.muni.fi.pa165.sportsactivitymanager.Enums.Sex;
import cz.muni.fi.pa165.sportsactivitymanager.Enums.UserState;

import javax.validation.constraints.*;
import java.util.Objects;

/**
 * @author Petra Gasparikova
 */
public class UserCreateDTO {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Min(0)
    @Max(120)
    private Integer age;

    @NotNull
    @Min(0)
    @Max(250)
    private Double weight;

    @NotNull
    @Min(0)
    @Max(200)
    private Double height;

    @NotNull
    private Sex SEX;

    @NotNull
    private UserState state;

    @NotNull
    @Pattern(regexp = ".+@.+\\....?")
    private String email;

    @NotNull
    @Size(min = 3, max = 50)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Sex getSEX() {
        return SEX;
    }

    public void setSEX(Sex SEX) {
        this.SEX = SEX;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserCreateDTO other = (UserCreateDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserCreateDTO{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", SEX=" + SEX +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

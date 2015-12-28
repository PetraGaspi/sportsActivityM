package cz.muni.fi.pa165.sportsactivitymanager.Entity;

import cz.muni.fi.pa165.sportsactivitymanager.Enums.Sex;
import cz.muni.fi.pa165.sportsactivitymanager.Enums.UserState;

import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Petra Gasparikova
 */
@Entity
//User is a reserved SQL table, so we need to change it to avoid conflicts
@Table(name="app_user")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String name;

    private String passwordHash;
    
    @NotNull
    private Integer age;

    @DecimalMin("0.0")
    @NotNull
    private Double weight;
    
    @DecimalMin("0.0")
    @NotNull
    private Double height;
    
    //@NotNull
    @Enumerated
    private Sex SEX;

    //TODO: state and password should get notNull
    //@NotNull
    @Enumerated
    private UserState state;
      
    @Column(nullable=false,unique=true)
    @NotNull
    @Pattern(regexp=".+@.+\\....?")
    private String email;

    //TODO: remove usages of this:
    public User(Long id) {
        this.id = id;
    }

    public User() {
    }
       
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public Sex getSex() {
        return SEX;
    }

    public void setSex(Sex sex) {
        this.SEX = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", age=" + age + ", weight=" + weight + ", height=" + height + ", SEX=" + SEX + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    
    
    
    
}

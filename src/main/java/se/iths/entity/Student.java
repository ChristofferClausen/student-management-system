package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "first name must not be empty")
    private String firstName;
    @NotEmpty(message = "last name must not be empty")
    private String lastname;
    @Email(message = "invalid email address")
    @NotEmpty(message = "email must not be empty")
    private String email;
    private String phoneNumber;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.PERSIST)
    private final Set<Subject> subjects = new HashSet<>();

    //<editor-fold desc="Getters and setter">

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    //</editor-fold>

}

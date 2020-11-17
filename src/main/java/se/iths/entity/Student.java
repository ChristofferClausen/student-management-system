package se.iths.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class Student {


    @Id
    private Long id;

    @NotEmpty(message = "first name must not be empty")
    private String firstName;
    @NotEmpty(message = "last name must not be empty")
    private String lastname;
    @Email(message = "invalid email address")
    @NotEmpty(message = "email must not be empty")
    private String email;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "subject_student")
    Subject subject;

    public Student(long id, String firstName, String lastname, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
    }

    public Student() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

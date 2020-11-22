package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Student")
public class Student {


    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Subject> subjects = new HashSet<>();

    public void addSubject(Subject subject) {
        subjects.add(subject);
        subject.getStudents().add(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @NotNull
    private String firstName;
    @NotEmpty
    @NotNull
    private String lastname;
    @Email
    @NotEmpty
    @NotNull
    private String email;

    private String phoneNumber;

    public Student(@NotEmpty @NotNull String firstName, @NotEmpty @NotNull String lastname, @NotEmpty @NotNull String email) {
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

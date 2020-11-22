package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @NotNull
    private String subject;

    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.PERSIST)
    private Set<Student> students = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    public Subject(@NotEmpty @NotNull String category) {
        this.subject = category;
    }

    public Subject() {
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Long getId() {
        return id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

}

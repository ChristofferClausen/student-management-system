package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Teacher")
public class Teacher {

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.PERSIST)
    private Set<Subject> subjects = new HashSet<>();

    public void addSubject(Subject subject, Teacher teacher) {
        subjects.add(subject);
        subject.setTeacher(teacher);
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
        subject.setTeacher(null);
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @NotEmpty
    private String name;

    public Teacher(@NotNull @NotEmpty String name) {
        this.name = name;
    }

    public Teacher() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<Subject> getSubjects() {
//        return subjects;
//    }

    public long getId() {
        return id;
    }
}

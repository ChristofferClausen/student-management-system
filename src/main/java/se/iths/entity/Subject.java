package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty
    @NotNull
    String subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    Teacher teacher;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Student> students = new HashSet<>();

    //<editor-fold desc="Getters and Setters">

    public void addStudent(Student student) {
        students.add(student);
        student.getSubjects().add(this);
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.getSubjects().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    //</editor-fold>
}

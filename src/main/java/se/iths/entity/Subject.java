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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher_id")
    Teacher teacher;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Student> students = new HashSet<>();

    public Subject(String subject) {
        this.subject = subject;
    }

    public Subject() {

    }

    //<editor-fold desc="Getters and Setters">

    public void addStudent(Student student) {
        students.add(student);
        student.getSubjects().add(this);
    }

    public void removeStudent(Student student) {
        this.teacher = null;
        student.getSubjects().remove(this);
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.getSubjects().add(this);
    }

    public void removeTeacher(Teacher teacher) {
        this.teacher = null;
        teacher.getSubjects().remove(this);
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Teacher getTeacher() {
        return teacher;
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

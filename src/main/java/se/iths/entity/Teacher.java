package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty
    String name;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true) //TODO Change cascade type
    private Set<Subject> subjects = new HashSet<Subject>();

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

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void addSubjects(Subject subject) {
        this.subjects.add(subject);
    }
}

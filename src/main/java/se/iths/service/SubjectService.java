package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;



    public Subject setTeacher(Long id, Teacher teacher) {
        var subject = entityManager.find(Subject.class, id);
        subject.setTeacher(teacher);
        entityManager.merge(subject);
        entityManager.flush();
        return subject;
    }

    public Object addStudent(Long id, Student student) {
        var subject = entityManager.find(Subject.class, id);
        subject.addStudent(student);
        entityManager.merge(subject);
        entityManager.flush();
        return subject;
    }



    //<editor-fold desc="CRUD">
    public Subject create(Subject subject) {
        entityManager.persist(subject);
        return subject;
    }

    public Subject findById(Long id) {
        return entityManager.find(Subject.class, id);
    }

    public List<Subject> getAll() {
        return entityManager.createQuery("SELECT s from Subject s", Subject.class).getResultList();
    }

    public void remove(Long id) {
        Subject subject = entityManager.find(Subject.class, id);
        entityManager.remove(subject);
    }

    public Subject update(Subject subject) {
        entityManager.merge(subject);
        entityManager.flush();
        return subject;
    }
    //</editor-fold>

}

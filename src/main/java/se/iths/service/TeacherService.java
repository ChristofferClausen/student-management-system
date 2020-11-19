package se.iths.service;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    //<editor-fold desc="CRUD">
    public Teacher create(Teacher teacher) {
        entityManager.persist(teacher);
        return teacher;
    }

    public Teacher findById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    public List<Teacher> getAll() {
        return entityManager.createQuery("SELECT t from Teacher t", Teacher.class).getResultList();
    }

    public void remove(Long id) {
        Teacher teacher = entityManager.find(Teacher.class, id);
        entityManager.remove(teacher);
    }

    public Teacher update(Teacher teacher) {
        entityManager.merge(teacher);
        entityManager.flush();
        return teacher;
    }
    //</editor-fold>

}
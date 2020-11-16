package se.iths.service;

import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

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

}

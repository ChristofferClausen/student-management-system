package se.iths.service;

import se.iths.entity.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    //<editor-fold desc="CRUD">
    public Student createStudent(Student student) {
        entityManager.persist(student);
        return student;
    }

    public Student findStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> findStudentsByLastName(String lastname) {
        Query query = entityManager.createQuery("SELECT s from Student s where upper(s.lastname) = upper(:lastname)", Student.class);
        query.setParameter("lastname", lastname);
        return query.getResultList();
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT s from Student s", Student.class).getResultList();
    }

    public void removeStudent(Long id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }

    public Student replaceFieldsInStudent(Student student) {
        entityManager.merge(student);
        entityManager.flush();
        return student;
    }

    public Student updateFieldsInStudent(Student inStudent) {
        var student = entityManager.find(Student.class, inStudent.getId());
        if (inStudent.getFirstName() != null)
            student.setFirstName(inStudent.getFirstName());
        if (inStudent.getLastname() != null)
            student.setLastname(inStudent.getLastname());
        if (inStudent.getEmail() != null)
            student.setEmail(inStudent.getEmail());
        if (inStudent.getPhoneNumber() != null)
            student.setPhoneNumber(inStudent.getPhoneNumber());
        entityManager.merge(student);
        entityManager.flush();
        return student;
    }
    //</editor-fold>

}

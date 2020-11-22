package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;


    public Subject addStudent(Long id, Student student) {
        var subject = entityManager.find(Subject.class, id);
        student.addSubject(subject);
        entityManager.merge(subject);
        entityManager.merge(student);
        return subject;
    }

    public Subject addTeacher(Long id, Long teacherId) {
        var subject = entityManager.find(Subject.class, id);
        var teacher = entityManager.find(Teacher.class, teacherId);
        teacher.addSubject(subject, teacher);
        entityManager.merge(subject);
        return subject;
    }

    public Subject createSubject(Subject subject) {
        entityManager.persist(subject);
        return subject;
    }

    public List<Subject> getAllSubjects() {
        return entityManager.createQuery("SELECT s FROM Subject s", Subject.class).getResultList();
    }

    public Set<Student> getStudentsForSubject(String teacherName, String subjectName) {
        Subject subject = (Subject) entityManager
                .createQuery("SELECT DISTINCT i FROM Subject i " +
                        "INNER JOIN i.teacher t " +
                        "INNER JOIN i.students s " +
                        "WHERE t.name = :teacherName " +
                        "AND i.subject =:subjectName")
                .setParameter("teacherName", teacherName).setParameter("subjectName", subjectName).getSingleResult();
        Set<Student> studentsResult = subject.getStudents();
        return studentsResult;
    }

    public Set<Student> getStudentsForSubject(String subjectName) {
        Subject subject = (Subject) entityManager
                .createQuery("SELECT DISTINCT i FROM Subject i " +
                        "INNER JOIN i.teacher t " +
                        "INNER JOIN i.students s " +
                        "WHERE i.subject = :subjectName ")
                .setParameter("subjectName", subjectName).getSingleResult();
        Set<Student> studentsResult = subject.getStudents();
        return studentsResult;
    }

}

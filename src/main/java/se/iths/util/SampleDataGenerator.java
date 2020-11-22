//package se.iths.util;
//
//import se.iths.entity.Student;
//import se.iths.entity.Subject;
//import se.iths.entity.Teacher;
//
//import javax.annotation.PostConstruct;
//import javax.ejb.Singleton;
//import javax.ejb.Startup;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//@Singleton
//@Startup
//public class SampleDataGenerator {
//
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @PostConstruct
//    public void initData() {
//        Student student1 = new Student("Erik","Eriksson","erik.erikson.com");
//        Student student2 = new Student("Sven","Svensson","sven@svensson.com");
//        Student student3 = new Student("Anders","Andersson","anders@andersson.com");
//
//        Subject subject1 = new Subject("Maths");
//        Subject subject2 = new Subject("Science");
//
//        Teacher teacher = new Teacher("Bob");
//
//        student1.addSubject(subject1);
//        student2.addSubject(subject1);
//        student2.addSubject(subject2);
//        student3.addSubject(subject2);
//
//        teacher.addSubject(subject1);
//        teacher.addSubject(subject2);
//
//        entityManager.persist(student1);
//        entityManager.persist(student2);
//        entityManager.persist(student3);
//        entityManager.persist(teacher);
//    }
//}
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
//    private void initData() {
//        Subject subject = new Subject("Maths");
//        Subject subject2 = new Subject("Science");
//
//        Teacher teacher1 = new Teacher("Bob");
//        Teacher teacher2 = new Teacher("Billy");
//
//        Student student1 = new Student("Christoffer", "Clausen", "christoffer@clausen.se", "07000");
//        Student student2 = new Student("Bert", "Bertsson", "bert@bertsson.se", "07000");
//        Student student3 = new Student("Sven", "Svensson", "sven@svensson.se", "07000");
//
//        subject.setTeacher(teacher1);
//        subject.addStudent(student1);
//        subject.addStudent(student2);
//        subject.addStudent(student3);
//        entityManager.persist(subject);
//
//        subject2.setTeacher(teacher2);
//        subject2.addStudent(student1);
//        subject2.addStudent(student2);
//        subject2.addStudent(student3);
//        entityManager.persist(subject2);
//    }
//
//}
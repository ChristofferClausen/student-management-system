package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("new")
    @POST
    public Response createStudent(Student student) {
        try {
            studentService.createStudent(student);
        } catch (Exception e) {
            throw new IncorrectInputException("Either ID already exists or you need to provide ID, firstName, lastname and email");
        }
        return Response.ok(student).build();
    }

    @Path("{id}")
    @GET
    public Response findStudentById(@PathParam("id") Long id) {
        Student student = studentService.findStudentById(id);
        if (student != null)
            return Response.ok(student).build();
        throw new StudentNotFoundException("Student with ID " + id + " not found");

    }

    @Path("lastname/{lastname}")
    @GET
    public List<Student> findStudentsByLastname(@PathParam("lastname") String lastname) {
        var students = studentService.findStudentsByLastName(lastname);
        if (students.size() > 0)
            return students;
        throw new StudentNotFoundException("Student with lastname: " + lastname + " not found");
    }

    @Path("all")
    @GET
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Path("update")
    @PUT
    public Response updateAllFieldsInStudent(Student student) {
        Student oldStudent;
        try {
            oldStudent = studentService.findStudentById(student.getId());
            if (oldStudent != null) {
                studentService.replaceFieldsInStudent(student);
                return Response.ok(student).build();
            } else {
                throw new StudentNotFoundException("Student with ID " + student.getId() + " not found");
            }
        } catch (Exception e) {
            throw new IncorrectInputException("You need to provide ID, firstName, lastname and email");
        }
    }

    @Path("update")
    @PATCH
    public Response updateSpecificFieldsInStudent(Student student) {
        Student oldStudent;
        try {
            oldStudent = studentService.findStudentById( student.getId());
            if (oldStudent != null) {
                var updatedStudent = studentService.updateFieldsInStudent(student);
                return Response.ok(updatedStudent).build();
            } else {
                throw new StudentNotFoundException("Student with ID " + student.getId() + " not found");
            }
        } catch (Exception e) {
            throw new IncorrectInputException("You need to provide ID, firstName, lastname and email");
        }
    }

    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @DELETE
    public Response removeStudent(@PathParam("id") Long id) {
        Student student = studentService.findStudentById(id);
        if (student != null) {
            studentService.removeStudent(id);
            return Response.ok().entity("Student with ID " + id + " deleted").type(MediaType.TEXT_PLAIN).build();
        } else {
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }
    }

    @GET
    @Path("get/{subjectName}/{teacherName}")
    public Set<Student> getStudentsBySubjectAndTeacher(@PathParam("subjectName") String subject, @PathParam("teacherName") String teacher) {
        return studentService.getStudentsForSubject(teacher, subject);
    }

    @GET
    @Path("get/{subjectName}")
    public Set<Student> getStudentsBySubject(@PathParam("subjectName") String subject) {
        return studentService.getStudentsForSubject(subject);
    }

}

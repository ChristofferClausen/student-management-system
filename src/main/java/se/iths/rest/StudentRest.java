package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @POST
    @Path("new")
    public Response createStudent(Student student) {
        try {
            studentService.createStudent(student);
        } catch (Exception e) {
            throw new IncorrectInputException("Either ID already exists or you need to provide ID, firstName, lastname and email");
        }
        return Response.ok(student).build();
    }

    @GET
    @Path("{id}")
    public Response findStudentById(@PathParam("id") Long id) {
        Student student = studentService.findStudentById(id);
        if (student != null)
            return Response.ok(student).build();
        throw new StudentNotFoundException("Student with ID " + id + " not found");

    }

    @GET
    @Path("lastname/{lastname}")
    public List<Student> findStudentsByLastname(@PathParam("lastname") String lastname) {
        var students = studentService.findStudentsByLastName(lastname);
        if (students.size() > 0)
            return students;
        throw new StudentNotFoundException("Student with lastname: " + lastname + " not found");
    }

    @GET
    @Path("all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PUT
    @Path("update")
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

    @PATCH
    @Path("update")
    public Response updateSpecificFieldsInStudent(Student student) {
        Student oldStudent;
        try {
            oldStudent = studentService.findStudentById(student.getId());
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

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeStudent(@PathParam("id") Long id) {
        Student student = studentService.findStudentById(id);
        if (student != null) {
            studentService.removeStudent(id);
            return Response.ok().entity("Student with ID " + id + " deleted").type(MediaType.TEXT_PLAIN).build();
        } else {
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }
    }

}

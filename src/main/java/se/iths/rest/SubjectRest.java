package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("subject")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @GET
    @Path("getstudents/{subjectName}/{teacherName}")
    public Set<Student> getStudentsBySubjectAndTeacher(@PathParam("subjectName") String subject, @PathParam("teacherName") String teacher) {
        return subjectService.getStudentsForSubject(teacher, subject);
    }

    @GET
    @Path("getstudents/{subjectName}")
    public Set<Student> getStudentsBySubject(@PathParam("subjectName") String subject) {
        return subjectService.getStudentsForSubject(subject);
    }

    @Path("{id}/add/student")
    @POST
    public Subject addStudent(@PathParam("id") Long id, Student student) {
        return subjectService.addStudent(id, student);
    }

    @Path("{subjectId}/add/teacher/{teacherId}")
    @POST
    public Subject addTeacher(@PathParam("subjectId") Long subjectId, @PathParam("teacherId")  Long teacherId) {
        return subjectService.addTeacher(subjectId, teacherId);
    }

    @Path("create")
    @POST
    public Response createSubject(Subject subject) {
        subjectService.createSubject(subject);
        return Response.ok(subject).build();
    }

    @Path("getall")
    @GET
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

}

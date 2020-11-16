package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teacher")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @POST
    @Path("new")
    public Response create(Teacher teacher) {
        try {
            teacherService.create(teacher);
        } catch (Exception e) {
            throw e; //TODO Replace with more relevant exception
        }
        return Response.ok(teacher).build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Long id) {
        Teacher teacher = teacherService.findById(id);
        if (teacher != null)
            return Response.ok(teacher).build();
        throw new NotFoundException(); //TODO Replace with more relevant exception
    }

    @GET
    @Path("all")
    public List<Teacher> findAll() {
        return teacherService.getAll();
    }

    @PUT
    @Path("update")
    public Response update(Teacher teacher) {
        try {
            Teacher foundTeacher = teacherService.findById(teacher.getId());
            if (foundTeacher != null) {
                teacherService.update(foundTeacher);
                return Response.ok(teacher).build();
            } else {
                throw new NotFoundException(); //TODO Replace with more relevant exception
            }
        } catch (Exception e) {
            throw new IncorrectInputException(""); //TODO Add message
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Teacher teacher = teacherService.findById(id);
        if (teacher != null) {
            teacherService.remove(id);
            return Response.ok().entity("Teacher with ID " + id + " was deleted").type(MediaType.TEXT_PLAIN).build();
        } else {
            throw new NotFoundException(); //TODO Replace with more relevant exception
        }
    }

}

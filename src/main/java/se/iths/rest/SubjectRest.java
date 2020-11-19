package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subject")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;



    @POST
    @Path("{subjectId}/add/teacher")
    public Response setTeacher(@PathParam("subjectId")Long subjectId, Teacher teacher) {
//        subjectService.addTeacher(subjectId,teacher);
        return Response.ok(subjectService.addTeacher(subjectId,teacher)).build();
    }




    @POST
    @Path("new")
    public Response create(Subject subject) {
        try {
            subjectService.create(subject);
        } catch (Exception e) {
            e.printStackTrace(); //TODO Replace with more relevant exception
        }
        return Response.ok(subject).build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Long id) {
        Subject subject = subjectService.findById(id);
        if (subject != null)
            return Response.ok(subject).build();
        throw new NotFoundException(); //TODO Replace with more relevant exception
    }

    @GET
    @Path("all")
    public List<Subject> getAll() {
        return subjectService.getAll();
    }

    @PUT
    @Path("update")
    public Response update(Subject subject) {
        try {
            Subject foundSubject = subjectService.findById(subject.getId());
            if (foundSubject != null) {
                subjectService.update(subject);
                return Response.ok(subject).build();
            } else {
                throw new NotFoundException(); //TODO Replace with more relevant exception
            }
        } catch (NotFoundException e) {
            throw new IncorrectInputException(""); //TODO Replace with more relevant exception
        }
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        Subject subject = subjectService.findById(id);
        if (subject != null) {
            subjectService.remove(id);
            return Response.ok().entity("Subject with ID " + id + " was deleted").type(MediaType.TEXT_PLAIN).build();
        } else {
            throw new NotFoundException(); //TODO Replace with more relevant exception
        }
    }


}

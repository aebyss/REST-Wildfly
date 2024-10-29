package de.pdbm;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    private PersonRepository personRepository;

    @GET
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getPerson(@PathParam("id") Long id) {
        Person person = personRepository.find(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(person).build();
    }

    @POST
    public Response addPerson(Person person) {
        personRepository.save(person);
        return Response.status(Response.Status.CREATED).entity(person).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") Long id, Person person) {
        Person existingPerson = personRepository.find(id);
        if (existingPerson == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        person.setId(id);
        personRepository.update(person);
        return Response.ok(person).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") Long id) {
        Person person = personRepository.find(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        personRepository.delete(id);
        return Response.noContent().build();
    }
}

package rest;

import dto.PersonDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonsDTO;
import entities.Person;
import exceptions.InputError;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String serverIsUp() {
        return "{\"msg\":\"API is running\"}";
    }
    
    @Path("/phone/{phoneNumber}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonByPhone(@PathParam("phoneNumber") int phone ) throws PersonNotFoundException   {
        PersonDTO personDTO = FACADE.getPersonByPhone(phone);
        return GSON.toJson(personDTO);
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editPersonById(@PathParam("id") int id, String person)
    {
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        PersonDTO editedPerson = FACADE.editPerson(id, p);
        return GSON.toJson(editedPerson);
    }
    
    @GET
    @Path("/hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersonsByHobby(@PathParam("hobby") String hobby) throws MissingInputException {
        PersonsDTO pdtoList = FACADE.getAllPersonsByHobby(hobby);
        return GSON.toJson(pdtoList.getAll());
    }
    
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
       return Response.ok().entity(GSON.toJson(FACADE.getAllPersons())).build();
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String addPerson(String person) throws MissingInputException, InputError {
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        PersonDTO newP = FACADE.addPerson(p);
        return GSON.toJson(newP);
    }
    
    



    /*
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String updatePerson(@PathParam("id") int id, String person) throws PersonNotFoundException, MissingInputException{
        PersonDTO pdto = GSON.fromJson(person, PersonDTO.class);
        pdto.setId(id);
        PersonDTO newpdto = FACADE.editPerson(pdto);
        return GSON.toJson(newpdto);
    }*/
    
}
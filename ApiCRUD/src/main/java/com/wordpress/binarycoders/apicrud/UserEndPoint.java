package com.wordpress.binarycoders.apicrud;

import java.net.URI;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/users")
@Produces ({ MediaType.APPLICATION_JSON })
@Consumes ({ MediaType.APPLICATION_JSON })
@Stateless
public class UserEndPoint {
    
    @EJB
    private UserService service;
    
    @Context
    private UriInfo uriInfo;
    
    @GET
    public Response findAll() {
        List<User> users = this.service.findAll();
        
        GenericEntity<List<User>> list = new GenericEntity<List<User>>(users) {};
        
        return Response.ok(list).build();
    }
    
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        User user = this.service.findById(id);
        
        if (user == null) {
            throw new NotFoundException();
        }
        
        return Response.ok(user).build();
    }
    
    @POST
    public Response create(User user) {
        this.service.create(user);
        
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(user.getId())).build();
        
        return Response.created(uri).build();
    }
    
    @PUT
    public Response update(User user) {
        this.service.update(user);
        
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        this.service.delete(id);
        
        return Response.noContent().build();
    }
}

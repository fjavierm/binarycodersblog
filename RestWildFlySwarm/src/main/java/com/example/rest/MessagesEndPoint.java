package com.example.rest;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
public class MessagesEndPoint {

  @GET
  public Response getMessage() {
    return Response.ok("This is our first WildFly Swarm App. Awesome!").build();
  }
}
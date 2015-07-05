package com.wordpress.binarycoders.calculator.rest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wordpress.binarycoders.calculator.model.BinaryOperationData;
import com.wordpress.binarycoders.calculator.rest.common.DataRequest;
import com.wordpress.binarycoders.calculator.rest.common.DataResponse;
import com.wordpress.binarycoders.calculator.service.OperationService;

/**
 *
 * @author fjavierm
 */
@Path("/addition")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Stateless
public class AdditionOperationEndPoint {

    @EJB
    private OperationService additionService;

    @GET
    @Path("/exone")
    public Response performAddition(final DataRequest<BinaryOperationData> request) {

        final Integer result = this.additionService.perform(request.getData());

        final DataResponse<Integer> response = new DataResponse<>();
        response.setIdentifier(request.getIdentifier());
        response.setResult(result);

        return Response.ok(response).build();
    }

    @GET
    @Path("/extwo")
    public void performMultipleAdditionAsync(@Suspended final AsyncResponse response,
                    final DataRequest<List<BinaryOperationData>> request) {

        CompletableFuture
                        .runAsync(() -> this.additionService.performMultiple(request.getData()))
                        .thenApply((result) -> response.resume(new DataResponse<>(request.getIdentifier(),
                                        result)))
                        .exceptionally(e -> response.resume(Response.serverError().entity(e).build()));
    }

    @GET
    @Path("/exthree")
    public void performMultipleHeavyAddition(@Suspended final AsyncResponse response,
                    final DataRequest<List<BinaryOperationData>> request) {

        CompletableFuture
                        .runAsync(() -> this.additionService.performMultipleHeavy(request.getData()))
                        .thenApply((result) -> response.resume(new DataResponse<>(request.getIdentifier(),
                                        result)))
                        .exceptionally(e
                                        -> response.resume(Response.serverError().entity(e).build()));
    }
}

package com.salesforce.mirus.rest;

import com.salesforce.mirus.MirusSourceConnector;
import org.apache.kafka.connect.health.ConnectClusterState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/health/connect")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HealthCheckResource {
    private final Map<String, ?> configs;
    private final ConnectClusterState clusterState;
    //    private Herder herder;
    private static final Logger logg = LoggerFactory.getLogger(HealthCheckResource.class);

    public HealthCheckResource(Map<String, ?> configs, ConnectClusterState clusterState) {
        // initialize resource
        this.configs = configs;
        this.clusterState = clusterState;
        // HealthState status;
        // log.info("initialize with configs {} and clusterState {}", configs, clusterState);
    }

    //    @GET
    //    @Path("/{connector}/task-config")
    //    public Response health(@PathParam("connector") String connector) {
    //        return Response.ok(HealthResponse.from(this.clusterState, connector)).build();
    //    }
    @GET
    @Path("/connectivity")
    public Response health() {
        if (MirusSourceConnector.connectionSuccess) {
            return Response.ok("yay Mirus is working").build();
        }
        logg.info("resource is working");

        return Response.ok("Mirus connector has failed to connect").build();
        // return Response.serverError().build();
    }
}

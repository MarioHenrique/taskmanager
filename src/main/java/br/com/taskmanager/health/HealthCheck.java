package br.com.taskmanager.health;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path(value="/system/")
public class HealthCheck {
	
	@GET
	@Path(value="healthcheck")
	@Produces(value="application/json")
	public Response healthCheck(){
		return Response.status(200).entity(new HealthCheckResponse("ok")).build();
	}

}

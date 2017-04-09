package org.ideaexchange.services;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/static")
public class StaticContentService {

	final static Logger logger = LoggerFactory.getLogger(StaticContentService.class);
	
	@GET
	@Path("{path:.*}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response Get(@PathParam("path") String path) {
		
		if (path.equalsIgnoreCase("swagger.json")){
		
			InputStream inputStream = 
				getClass().getClassLoader().getResourceAsStream("/" + path);
		    
			if (inputStream == null){
				logger.warn("Static resource not found: " + path);
				return Response.serverError().build();
			}
			
			return Response.ok(inputStream, MediaType.APPLICATION_OCTET_STREAM)
				.header("Content-Disposition", "attachment; filename=\"" + path + "\"" ) //optional
				.build();
		}
		else
		{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
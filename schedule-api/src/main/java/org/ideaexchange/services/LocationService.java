package org.ideaexchange.services;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.ideaexchange.entity.LocationEntity;
import org.ideaexchange.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/location")
@Produces(MediaType.APPLICATION_JSON)
public class LocationService {
	
	final static Logger logger = LoggerFactory.getLogger(LocationService.class);
	
	@GET
	public LocationEntity get(@QueryParam("id") int id)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		LocationEntity location = (LocationEntity)session.get(LocationEntity.class, (long) id); 
		return location;
	}
	
	@PUT
	public LocationEntity put(@QueryParam("id") int id)
	{
		LocationEntity entity = new LocationEntity();
		entity.setName("Queen's Square");
		entity.setShortName("QS");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
		
		return entity;
	}
}
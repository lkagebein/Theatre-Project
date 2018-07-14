package com.aca.stage.controller;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aca.stage.model.Performances;
import com.aca.stage.model.Shows;
import com.aca.stage.model.Venue;
import com.aca.stage.service.EverythingService;
import com.aca.stage.service.PerformancesService;
import com.aca.stage.service.ShowsService;
import com.aca.stage.service.VenueService;



@Path("/stage")
public class TheatreController {
	
	@GET
	@Path("/everything")
	@Produces(MediaType.APPLICATION_JSON)
	public void getEverything () {
		EverythingService.getEverything();
	}
	
	@GET
	@Path("/empty")
	@Produces(MediaType.APPLICATION_JSON)
	public void emptyEverything () {
		EverythingService.emptyEverything();
	}

	@GET
	@Path("/venues")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Venue> getVenueInformation () {
		return VenueService.getVenueInformation();
	}
	
	@GET
	@Path("/shows")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shows> getShowInformation () {
		return ShowsService.getShowsInformation();
	}
	
	@GET
	@Path("/venue/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Venue> getSingleVenueInformation(@PathParam("value") String theatre) {
		List<Venue> venue = VenueService.getSingleVenueInformation(theatre);
		return venue;
	}
	
	@GET
	@Path("/shows/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shows> getShowsByVenue(@PathParam("value") String theatre) {
		List<Shows> shows = ShowsService.getShowsByVenue(theatre);
		return shows;
	}
	
	@GET
	@Path("/show/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shows> getShowInformationByTitle(@PathParam("value") String title) {
		List<Shows> shows = ShowsService.getShowInformationByTitle(title);
		return shows;
	}
	
	@GET
	@Path("/performances")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Performances> getPerformancesInformation () {
		return PerformancesService.getPerformancesInformation();
	}
	
	@GET
	@Path("/performances/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Performances> getPerformancesBySingleDate (@PathParam("value") String performanceDate) {
		//System.out.println(performanceDate);
		return PerformancesService.getPerformancesBySingleDate(performanceDate);
	}
	
	@GET
	@Path("/performances/{value1}/{value2}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Performances> getPerformancesByDateRange (@PathParam("value1") String startDate, @PathParam("value2") String endDate ) {
		return PerformancesService.getPerformancesByDateRange(startDate, endDate);
	}

}

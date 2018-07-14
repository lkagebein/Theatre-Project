package com.aca.stage.service;

import java.util.ArrayList;
import java.util.List;

import com.aca.stage.dao.VenueDao;
import com.aca.stage.model.Venue;

public class VenueService {

	public static List<Venue> getVenueInformation() {
		return VenueDao.getVenueInformation();	
	}
	
	public static void insertVenue (List<Venue> venuesToAdd) {
		
		List<Venue> venues = new ArrayList<Venue>();
		
		List<Venue> venuesAquired = new ArrayList<Venue>();
		venuesAquired = getVenueInformation();
		for (Venue v : venuesToAdd) {
			int count = 0;
			//System.out.println(v.toString());
			for (Venue ven : venuesAquired) {
				if (ven.getVenueName().equals(v.getVenueName())) {
					System.out.println("Match Found: " + ven.getVenueName() + " already in the database.");
					count++;
				}
			}
			if (count == 0) {
				venues.add(v);
				System.out.println(v.getVenueName() + " Added");
			}
		}
		
		VenueDao.insertVenue(venues);
	}
	
	public static void clearDatabase () {
		VenueDao.clearDatabase();
	}

	public static List<Venue> getSingleVenueInformation(String theatre) {
		List<Venue> venue = VenueDao.getSingleVenueInformation(theatre);
		return venue;
	}

}

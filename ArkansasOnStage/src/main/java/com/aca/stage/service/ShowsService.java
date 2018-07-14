package com.aca.stage.service;

import java.util.ArrayList;
import java.util.List;

import com.aca.stage.dao.ShowsDao;
import com.aca.stage.model.Shows;


public class ShowsService {

	public static List<Shows> getShowsInformation() {
		return ShowsDao.getShowsInformation();	
	}
	
	public static void insertShows (List<Shows> showsToAdd) {
		
		List<Shows> shows = new ArrayList<Shows>();
		
		List<Shows> showsAquired = new ArrayList<Shows>();
		showsAquired = getShowsInformation();

		for (Shows s : showsToAdd) {
			int count = 0;
			//System.out.println(v.toString());
			for (Shows sh : showsAquired) {
				if (sh.getTheatreName().equals(s.getTheatreName()) && sh.getTitle().equals(s.getTitle())) {
					System.out.println("Match Found: " + sh.getTitle() + " is already in the database.");
					count++;
				}
			}
			if (count == 0) {
				shows.add(s);
				System.out.println(s.getTitle() + " Added");
			}
		}
		
		ShowsDao.insertShows(shows);
	}
	
	public static List<Shows> getShowsByVenue(String theatre) {
		List<Shows> shows = ShowsDao.getShowsByVenue(theatre);
		return shows;
	}

	public static List<Shows> getShowInformationByTitle(String title) {
		List<Shows> shows = ShowsDao.getShowInformationByTitle(title);
		return shows;
	}

	public static void clearDatabase () {
		ShowsDao.clearDatabase();
	}

}

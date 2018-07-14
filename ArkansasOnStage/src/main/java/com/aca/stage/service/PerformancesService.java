package com.aca.stage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aca.stage.dao.PerformancesDao;
import com.aca.stage.model.Performances;

public class PerformancesService {

	public static List<Performances> getPerformancesInformation() {
		return PerformancesDao.getPerformancesInformation();	
	}

	public static void insertPerformances (List<Performances> performancesToAdd) {

		List<Performances> performances = new ArrayList<Performances>();

		List<Performances> performancesAquired = new ArrayList<Performances>();
		performancesAquired = getPerformancesInformation();

		for (Performances p : performancesToAdd) {
			int count = 0;
			//System.out.println(v.toString());
			for (Performances per : performancesAquired) {
				if (per.getTitle().equals(p.getTitle()) 
						&& per.getPerformanceDate().compareTo(p.getPerformanceDate()) == 0
						&& per.getPerformanceTime().equals(p.getPerformanceTime())) {
					System.out.println("Match Found: " + per.getTitle() + " is already in the database.");
					count++;
				}
			}
			if (count == 0) {
				performances.add(p);
				System.out.println("Added: " + p.getTitle() + "\n On: " 
				       + p.getPerformanceDate() + " At: " + p.getPerformanceTime());
				
			}
		}
		PerformancesDao.insertPerformances(performances);
		performancesAquired.clear();
	}

	public static List<Performances> getPerformancesByVenue(String theatre) {
		List<Performances> performances = PerformancesDao.getPerformancesByVenue(theatre);
		return performances;
	}

	public static List<Performances> getPerformanceInformationByTitle(String title) {
		List<Performances> performances = PerformancesDao.getPerformanceInformationByTitle(title);
		return performances;
	}

	public static void clearDatabase () {
		PerformancesDao.clearDatabase();
	}

	public static List<Performances> getPerformancesBySingleDate(String performanceDate) {
		return PerformancesDao.getPerformancesBySingleDate(performanceDate);
	}

	public static List<Performances> getPerformancesByDateRange(String startDate, String endDate) {
		return PerformancesDao.getPerformancesByDateRange(startDate, endDate);
	}


}


package com.aca.stage.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import com.aca.stage.model.Performances;
import com.aca.stage.model.Shows;
import com.aca.stage.model.Venue;
import com.aca.stage.performance.pom.MurrysPerformancesPage;
import com.aca.stage.performance.pom.ReynoldsPerformancesPage;
import com.aca.stage.performance.pom.RobinsonCenterPerformancesPage;
import com.aca.stage.performance.pom.TheRepPerformancesPage;
import com.aca.stage.performance.pom.TheWeekendTheatrePerformancesPage;
import com.aca.stage.performance.pom.WaltonArtsPerformancesPage;
import com.aca.stage.service.PerformancesService;
import com.aca.stage.service.ShowsService;
import com.aca.stage.service.VenueService;
import com.aca.stage.shows.pom.MurrysShowsPage;
import com.aca.stage.shows.pom.ReynoldsShowsPage;
import com.aca.stage.shows.pom.RobinsonCenterShowsPage;
import com.aca.stage.shows.pom.TheRepShowsPage;
import com.aca.stage.shows.pom.TheWeekendTheatreShowsPage;
import com.aca.stage.shows.pom.WaltonArtsCenterShowsPage;
import com.aca.stage.venue.pom.MurrysPage;
import com.aca.stage.venue.pom.ReynoldsPage;
import com.aca.stage.venue.pom.RobinsonCenterPage;
import com.aca.stage.venue.pom.TheRepPage;
import com.aca.stage.venue.pom.TheWeekendTheatrePage;
import com.aca.stage.venue.pom.WaltonArtsCenterPage;

public class GetInformationWD {

	private WebDriver driver;
	private static List<Venue> venues = new ArrayList<Venue>();
	private static List<Shows> shows = new ArrayList<Shows>();
	private static List<Performances> performances = new ArrayList<Performances>();

	public static void main (String[] args)  {

		GetInformationWD info = new GetInformationWD();

		//info.getAllInformation();
 
		//info.clearDatabase();

	}
	
	public static void getEverything() {
		
		GetInformationWD info = new GetInformationWD();
		
		info.getAllInformation();
		
	}
	
	public static void emptyEverything() {
		
		GetInformationWD info = new GetInformationWD();
		
		info.clearDatabase();
		
	}

	public void getAllInformation()  {

		getMurrysInformation();
		getReynoldsInformation();
		getRobinsonInformation();
		//getTheRepInformation();
		//getTheWeekendTheatreInformation();

		VenueService.insertVenue (venues);
		ShowsService.insertShows (shows);
		PerformancesService.insertPerformances(performances);

	}

	public void setUp()  {

		try {

			System.setProperty("webdriver.chrome.driver",
					"C:\\Program Files\\chrome-2.39\\chromedriver.exe");
					//"target/classes/chrome-2.39/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("chrome.switches", "--disable-extensions");

			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(1,  TimeUnit.SECONDS);

		}catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void getMurrysInformation()  { 

		setUp();
		//Venue
		driver.get("http://murrysdp.com/");
		MurrysPage murrysPage = PageFactory.initElements(driver, MurrysPage.class);
		venues.add(murrysPage.getInformation());
		//Shows
		driver.get("http://murrysdp.com/the-shows/");
		MurrysShowsPage murrysShowsPage = PageFactory.initElements(driver, MurrysShowsPage.class);
		List<Shows> murrys = murrysShowsPage.getInformation();
		for(Shows m : murrys) {
			shows.add(m);
		}
		//Performances
		MurrysPerformancesPage murrysPerformancePage = PageFactory.initElements(driver, MurrysPerformancesPage.class);
		List<Performances> murrysPerformances = murrysPerformancePage.getInformation();
		for(Performances p : murrysPerformances) {
			performances.add(p);
		}

		tearDown();

	}

	public void getReynoldsInformation() {

		setUp();
		//Venue
		driver.get("http://uca.edu/publicappearances/");
		ReynoldsPage reynoldsPage = PageFactory.initElements(driver, ReynoldsPage.class);
		venues.add(reynoldsPage.getInformation());
		//Shows
		driver.get("http://uca.edu/publicappearances/2018-2019-season/");
		ReynoldsShowsPage reynoldsShowsPage = PageFactory.initElements(driver, ReynoldsShowsPage.class);
		List<Shows> reynolds = reynoldsShowsPage.getInformation();

		for(Shows r : reynolds) {
			shows.add(r);
		}
		//Performances
		ReynoldsPerformancesPage reynoldsPerformancePage = PageFactory.initElements(driver, ReynoldsPerformancesPage.class);
		List<Performances> reynoldsPerformances = reynoldsPerformancePage.getInformation();
		for(Performances p : reynoldsPerformances) {
			performances.add(p);
		}

		tearDown();

	}

	public void getRobinsonInformation()  {

		setUp();
		//Venue
		driver.get("http://www.robinsoncentersecondact.com/");
		RobinsonCenterPage robinsonCenterPage = PageFactory.initElements(driver, RobinsonCenterPage.class);
		venues.add(robinsonCenterPage.getInformation());
		//Shows
		driver.get("https://www.celebrityattractions.com/seasontickets.asp?locid=4");
		RobinsonCenterShowsPage robinsonCenterShowsPage = PageFactory.initElements(driver, RobinsonCenterShowsPage.class);
		List<Shows> robinson = robinsonCenterShowsPage.getInformation();

		for (Shows rc : robinson) {
			shows.add(rc);
		}
		
		driver.get("https://www.celebrityattractions.com/seasontickets.asp?locid=4");
		RobinsonCenterPerformancesPage robinsonCenterPerformancesPage = PageFactory.initElements(driver, RobinsonCenterPerformancesPage.class);
		List<Performances> robinsonCenterPerformances = robinsonCenterPerformancesPage.getInformation();
		for(Performances p : robinsonCenterPerformances) {
			performances.add(p);
		}

		tearDown();

	}

	public void getTheRepInformation()  {

		setUp();

		driver.get("https://www.therep.org/");
		TheRepPage theRepPage = PageFactory.initElements(driver, TheRepPage.class);
		venues.add(theRepPage.getInformation());

		driver.get("https://tickets.therep.org/TheatreManager/1/login&event=0");
		TheRepShowsPage theRepShowsPage = PageFactory.initElements(driver, TheRepShowsPage.class);
		List<Shows> rep = theRepShowsPage.getInformation();

		for (Shows tr : rep) {
			shows.add(tr);
		}
		
		driver.get("https://tickets.therep.org/TheatreManager/1/login&event=0");
		TheRepPerformancesPage theRepPerformancesPage = PageFactory.initElements(driver, TheRepPerformancesPage.class);
		List<Performances> theRepPerformances = theRepPerformancesPage.getInformation();
		for(Performances p : theRepPerformances) {
			performances.add(p);
		}

		tearDown();

	}

	public void getTheWeekendTheatreInformation()  {

		setUp();
		//Venue
		driver.get("https://weekendtheater.org/about-us/location/");
		TheWeekendTheatrePage theWeekendTheatrePage = PageFactory.initElements(driver, TheWeekendTheatrePage.class);
		venues.add(theWeekendTheatrePage.getInformation());
		//Shows
		driver.get("https://weekendtheater.org/2018-2019-season/");
		TheWeekendTheatreShowsPage theWeekendTheatreShowsPage = PageFactory.initElements(driver, TheWeekendTheatreShowsPage.class);
		List<Shows> weekend = theWeekendTheatreShowsPage.getInformation();

		for (Shows twt : weekend) {
			shows.add(twt);
		}
		
		driver.get("https://weekendtheater.org/2018-2019-season/");
		TheWeekendTheatrePerformancesPage theWeekendTheatrePerformancesPage = PageFactory.initElements(driver, TheWeekendTheatrePerformancesPage.class);
		List<Performances> theWeekendTheatrePerformances = theWeekendTheatrePerformancesPage.getInformation();
		for(Performances p : theWeekendTheatrePerformances) {
			performances.add(p);
		}

		tearDown();

	}

//	public void getWaltonArtsCenter() throws Exception {
// //		getWaltonArtsCenter();
//		setUp();
//		//Venue
////		driver.get("https://waltonartscenter.org/");
////		WaltonArtsCenterPage waltonArtsCenterPage = PageFactory.initElements(driver, WaltonArtsCenterPage.class);
////		venues.add(waltonArtsCenterPage.getInformation());
//		//Shows
//		driver.get("https://waltonartscenter.org/tickets/series/");
//		WaltonArtsCenterShowsPage waltonArtsCenterShowsPage = PageFactory.initElements(driver, WaltonArtsCenterShowsPage.class);
////		List<Shows> walton = waltonArtsCenterShowsPage.getInformation();
////
////		for (Shows wac : walton) {
////			shows.add(wac);
////		}
//		
//		driver.get("https://waltonartscenter.org/tickets/series/");
//		WaltonArtsPerformancesPage waltonArtsPerformancesPage = PageFactory.initElements(driver, WaltonArtsPerformancesPage.class);
//		List<Performances> waltonArtsPerformances = waltonArtsPerformancesPage.getInformation();
//		for(Performances p : waltonArtsPerformances) {
//			performances.add(p);
//		}
//
//		tearDown();
//
//	}

	public void tearDown()  {
		driver.quit();	
	}

	public void clearDatabase() {
		PerformancesService.clearDatabase();
		System.out.println("Performances Cleared");
		ShowsService.clearDatabase();
		System.out.println("Shows Cleared");
		VenueService.clearDatabase();
		System.out.println("Venues Cleared");

	}


}


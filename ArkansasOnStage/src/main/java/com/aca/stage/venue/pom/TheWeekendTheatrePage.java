package com.aca.stage.venue.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aca.stage.model.Venue;


public class TheWeekendTheatrePage {
	
	WebDriver driver;

	public TheWeekendTheatrePage (WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath="//*[@id=\'site-header\']/div[1]/img")
	public WebElement icon;
	public String getIcon(){
		return icon.getAttribute("src");
	}
	
	public Venue getInformation() {
		
		Venue venue = new Venue();
		
//		private String venueName;
		venue.setVenueName("The Weekend Theatre");

//		private String icon;
		venue.setIcon(getIcon());
		
//		private String homePage;
		venue.setHomepage("https://weekendtheater.org/");
		
//		private String address;
		venue.setAddress("1001 W. 7th St., Little Rock, AR 72201");
//		
//		private String phoneNumber;
		venue.setPhoneNumber("501-374-3761");
		
//		private String ticketInfoSite;
		venue.setTicketInfoSite("https://centralarkansastickets.com/organizations/the-weekend-theater");
		

		return venue;
		
	}
	
	
	
	
	
}

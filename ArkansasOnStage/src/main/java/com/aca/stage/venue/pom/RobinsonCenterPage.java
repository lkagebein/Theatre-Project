package com.aca.stage.venue.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aca.stage.model.Venue;


public class RobinsonCenterPage {
	
	WebDriver driver;

	public RobinsonCenterPage (WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath="//*[@id=\'site-header\']/a/img")
	public WebElement icon;
	public String getIcon(){
		return icon.getAttribute("src");
	}

	public Venue getInformation() {
		
		Venue venue = new Venue();
		
//		private String venueName;
		venue.setVenueName("Robinson Center");

//		private String icon;
		venue.setIcon(getIcon());
		
//		private String homePage;
		venue.setHomepage("http://www.robinsoncentersecondact.com/");
		
//		private String address;
		venue.setAddress("426 W Markham St, Little Rock, AR 72201");
		
//		private String phoneNumber;
		venue.setPhoneNumber("501-244-8800");
		
//		private String ticketInfoSite;
		venue.setTicketInfoSite("https://www.celebrityattractions.com/");
		

		return venue;
		
	}
	
	
	
	
	
}

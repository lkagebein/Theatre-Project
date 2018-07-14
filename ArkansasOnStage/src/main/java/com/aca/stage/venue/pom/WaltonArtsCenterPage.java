package com.aca.stage.venue.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aca.stage.model.Venue;


public class WaltonArtsCenterPage {
	
	WebDriver driver;

	public WaltonArtsCenterPage (WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath="//*[@id=\'nav-mobile\']/div/div[1]/a/img")
	public WebElement icon;
	public String getIcon(){
		return icon.getAttribute("src");
	}
	
	public Venue getInformation() {
		
		Venue venue = new Venue();
		
//		private String venueName;
		venue.setVenueName("Walton Arts Center");

//		private String icon;
		venue.setIcon(getIcon());
		
//		private String homePage;
		venue.setHomepage("https://waltonartscenter.org/");
		
//		private String address;
		venue.setAddress("495 W. Dickson St. Fayetteville, AR 72701");
//		
//		private String phoneNumber;
		venue.setPhoneNumber("479.443.5600");
		
//		private String ticketInfoSite;
		venue.setTicketInfoSite("https://waltonartscenter.org/tickets/series/");
		

		return venue;
		
	}
	
	
	
	
	
}

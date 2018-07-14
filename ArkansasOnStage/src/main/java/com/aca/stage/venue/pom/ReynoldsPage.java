package com.aca.stage.venue.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aca.stage.model.Venue;


public class ReynoldsPage {
	
	WebDriver driver;

	public ReynoldsPage (WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath="//*[@id=\'widget_sp_image-2\']/div/img")
	public WebElement icon;
	public String getIcon(){
		return icon.getAttribute("src");
	}
	
	
	//*[@id="footer"]/div/div[1]/p/text()[1]
	@FindBy(xpath="//*[@id=\'footer\']/div/div[1]/p")
	public WebElement addressPhone;
	public String getAddressPhone() {
		return addressPhone.getText();
	}

	@FindBy(xpath="//*[@id=\'text-13\']/div/div/a")
	public WebElement getTicketInfoSite;
	public String getTicketInfoSite() {
		return getTicketInfoSite.getAttribute("href");
	}

	
	
	public Venue getInformation() {
		
		Venue venue = new Venue();
		
//		private String venueName;
		venue.setVenueName("Reynolds Performance Hall");

//		private String icon;
		venue.setIcon(getIcon());
		
//		private String homePage;
		venue.setHomepage("http://uca.edu/publicappearances/");
		
//		private String address;
		venue.setAddress("201 Donaghey Ave., Conway, AR 72035");
//		
//		private String phoneNumber;
		venue.setPhoneNumber("(501) 450-5000");
		
//		private String ticketInfoSite;
		venue.setTicketInfoSite(getTicketInfoSite());
		

		return venue;
		
	}
	
	
	
	
	
}

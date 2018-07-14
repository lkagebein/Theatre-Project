package com.aca.stage.venue.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aca.stage.model.Venue;


public class TheRepPage {
	
	WebDriver driver;

	public TheRepPage (WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath="//*[@id=\'site\']/footer/div/div/div[1]/div[1]/img")
	public WebElement icon;
	public String getIcon(){
		return icon.getAttribute("src");
	}
	
	@FindBy(xpath="//*[@id=\'site\']/footer/div/div/div[1]/div[1]/address")
	public WebElement address;
	public String getAddress() {
		return address.getText();
	}
	
	@FindBy(xpath="//*[@id=\'site\']/footer/div/div/div[1]/div[2]/a[1]")
	public WebElement phoneNumber;
	public String getPhoneNumber(){
		return phoneNumber.getText();
	}
	
	@FindBy(xpath="//*[@id=\'site\']/footer/div/div/div[3]/a")
	public WebElement getTicketInfoSite;
	public String getTicketInfoSite() {
		return getTicketInfoSite.getAttribute("href");
	}

	public Venue getInformation() {
		
		Venue venue = new Venue();
		
//		private String venueName;
		venue.setVenueName("Arkansas Repertory Theatre");

//		private String icon;
		venue.setIcon(getIcon());
		
//		private String homePage;
		venue.setHomepage("https://www.therep.org/");
		
//		private String address;
		venue.setAddress(getAddress());
//		
//		private String phoneNumber;
		venue.setPhoneNumber(getPhoneNumber());
		
//		private String ticketInfoSite;
		venue.setTicketInfoSite(getTicketInfoSite());
		

		return venue;
		
	}
	
	
	
	
	
}

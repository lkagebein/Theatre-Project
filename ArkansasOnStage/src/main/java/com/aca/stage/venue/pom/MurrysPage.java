package com.aca.stage.venue.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aca.stage.model.Venue;


public class MurrysPage {
	
	WebDriver driver;

	public MurrysPage (WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath="//*[@id=\'branding\']/a/img")
	public WebElement icon;
	public String getIcon(){
		return icon.getAttribute("src");
	}
	
	@FindBy(xpath="//*[@id=\'colophon\']/div/p/a[2]")
	public WebElement address;
	public String getAddress() {
		return address.getText();
	}
	
	@FindBy(xpath="//*[@id='page-masthead']/div[1]/div[2]/div[1]/strong")
	public WebElement phoneNumber;
	public String getPhoneNumber(){
		return phoneNumber.getText();
	}
	
	@FindBy(xpath="/html/body/div[3]/div/div/div[2]/div[1]/div[1]/a")
	public WebElement getTicketInfoSite;
	public String getTicketInfoSite() {
		return getTicketInfoSite.getAttribute("href");
	}
	
	
	public Venue getInformation() {
		
		Venue venue = new Venue();
		
//		private String venueName;
		venue.setVenueName("Murry's Dinner Playhouse");

//		private String icon;
		venue.setIcon(getIcon());
		
//		private String homePage;
		venue.setHomepage("http://murrysdp.com/");
		
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

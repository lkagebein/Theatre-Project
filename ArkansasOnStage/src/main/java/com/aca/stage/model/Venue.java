package com.aca.stage.model;

public class Venue {

	private String venueName;
	private String icon;
	private String homepage;
	private String address;
	private String phoneNumber;
	private String ticketInfoSite;
	
	public String getVenueName() {
		return venueName;
	}
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getTicketInfoSite() {
		return ticketInfoSite;
	}
	public void setTicketInfoSite(String ticketInfoSite) {
		this.ticketInfoSite = ticketInfoSite;
	}
	
	@Override
	public String toString () {
		
		String view = "";
		
		view = "Venue : " + this.venueName + "\n" +
		"Icon Link: " + this.icon + "\n" +
		"Homepage Link: " + this.homepage + "\n" +
		"Address: " + this.address + "\n" +
		"Phone Number: " + this.phoneNumber + "\n" +
		"Ticket Link: " + this.ticketInfoSite + "\n";
		
		return view;
	}
	
	
}

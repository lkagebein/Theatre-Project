package com.aca.stage.model;

public class Shows {

	private String theatreName;
	private String title;
	private String descriptionLink;
	private String pictureUrl;
	
	
	public String getTheatreName() {
		return theatreName;
	}



	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescriptionLink() {
		return descriptionLink;
	}



	public void setDescriptionLink(String descriptionLink) {
		this.descriptionLink = descriptionLink;
	}



	public String getPictureUrl() {
		return pictureUrl;
	}



	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}



	@Override
	public String toString () {
		
		String view = "";
		
		view = "Venue : " + this.theatreName + "\n" +
		"Title: " + this.title + "\n" +
		"Description Link: " + this.descriptionLink + "\n" +
		"Picture Url: " + this.pictureUrl + "\n";
		
		return view;
	}
	
	
}

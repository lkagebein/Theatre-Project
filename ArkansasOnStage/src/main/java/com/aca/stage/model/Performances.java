package com.aca.stage.model;

import java.util.Date;

public class Performances {
	
	private Integer id;
	private String theatreName;
	private String title;
	private Date performanceDate;
	private String performanceTime;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
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
	
	public Date getPerformanceDate() {
		return performanceDate;
	}
	
	public void setPerformanceDate(Date performanceDate) {
		this.performanceDate = performanceDate;
	}

	public String getPerformanceTime() {
		return performanceTime;
	}

	public void setPerformanceTime(String performanceTime) {
		this.performanceTime = performanceTime;
	}

	@Override
	public String toString() {
		
		String output = "Performance Information \n" +
		"Theatre: " + theatreName + "\n" +
		"Title: " + title + "\n" +
		"Performance Date: " + performanceDate.toString() + "\n" +
		"Permormance Time: " + performanceTime + "\n";
		
		return output;
	}


	
	
	
}

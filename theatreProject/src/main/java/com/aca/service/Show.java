package com.aca.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Show {

	private String title;
	private String link;
	private String times;
	List<Date> dates;
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		String year = "2018", time = "";
		times = times + " " + year;
		String[] temp = times.split("\\s+");
		temp[2] = year;
		for (String i : temp) {
			time = time + i + " ";
		}
		
		this.times = time;
		setDate(this.dates);
	}
	
	public List <Date> getDates() {
		return dates;
	}
	
	public void setDate (List <Date> dates) {
		try {
			dates = ShowTimeConversion.convertToDateArray(times);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dates = dates;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString(){

		String result = "Title: " + this.title + "\n" +
				" Link: " + this.link  + "\n" +
				"Times: " + getDates() + "\n" +
				"Description: " + this.description + "\n";
		return result;
	}
}

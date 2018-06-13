package com.aca.service;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aca.pom.MurrysShows;

public class CurrentShows {
	
	public static int getNumberOfShows2018() {
		List<WebElement> listOfShows = MurrysShows.getListOf2018Shows();
		return listOfShows.size();
	}
	
	public static List<Show> get2018Shows() {
		List<Show> shows = new ArrayList<Show>();
		List<WebElement> listOfShows = MurrysShows.getListOf2018Shows();
		for (WebElement showElement : listOfShows) {
			
			WebElement aElement = showElement.findElement (By.cssSelector("div > a"));
			String hrefLink = aElement.getAttribute("href");
			String title = aElement.getAttribute("title");
			
			Show show = new Show();
			show.setTitle(title);
			show.setLink(hrefLink);
			show.setTimes(showElement.getText());
			
			shows.add(show);
		}
		
		return shows;
	}	
}

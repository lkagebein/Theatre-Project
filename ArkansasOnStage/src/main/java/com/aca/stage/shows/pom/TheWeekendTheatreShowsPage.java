package com.aca.stage.shows.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aca.stage.model.Shows;


public class TheWeekendTheatreShowsPage {
	
	//private WebDriver driver;
	
	public TheWeekendTheatreShowsPage(WebDriver driver) {
		//this.driver=driver;
	}

	
	@FindBy(xpath="//*[@id=\'post-4138\']")
	public WebElement showsList;
	
	public List<WebElement> getListOfShows() {
		List<WebElement> listOfAllShows = 
				showsList.findElements(By.tagName("h3"));
		return listOfAllShows;
	}
	
	public List<Shows> getInformation() {
		
		List<Shows> shows = new ArrayList<Shows>();
		List<WebElement> listOfAllShows = getListOfShows();
		String descriptionLink = "", title = "", pictureUrl = "";
		int count = 1;
		
		for(WebElement showElement : listOfAllShows) {
			
			WebElement element = null;
			
			if (count == 1 || (count > 5 && count < 10)) {
			
			element = showElement.findElement(By.cssSelector("em > a:nth-child(1)")); 
			
			pictureUrl = element.getAttribute("href");
			
			element = showElement.findElement(By.cssSelector("em > a:nth-child(2)")); 
			
			descriptionLink = element.getAttribute("href");
			
			title = element.getText(); 
			
			} else if (count == 5 ) {
				
			} else if (count >= 10) {
				
				element = showElement.findElement(By.cssSelector("em:nth-child(1) > a")); 
				
				pictureUrl = element.getAttribute("href");
				
				element = showElement.findElement(By.cssSelector("em:nth-child(2) > a")); 
				
				descriptionLink = element.getAttribute("href");
				
				title = element.getText(); 
				
			} else {
				
				element = showElement.findElement(By.cssSelector("em > a")); 
				
				pictureUrl = element.getAttribute("href"); 
				
				element = showElement.findElement(By.cssSelector("h3 > a")); 
				
				descriptionLink = element.getAttribute("href");
				
				title = element.getText();
			} 
			
			count++;
			
			Shows show = new Shows();
			show.setTheatreName("The Weekend Theatre");
			show.setTitle(title);
			show.setDescriptionLink(descriptionLink);
			show.setPictureUrl(pictureUrl);
			
			int temp = 0;
			for (Shows s : shows) {
				if(show.getTitle().equals(s.getTitle())) {
					temp++;
				}
			}
			if (temp == 0) {
				shows.add(show);
			}
		}
			
			return shows;
		}	
		
	
	
	
	
	
}

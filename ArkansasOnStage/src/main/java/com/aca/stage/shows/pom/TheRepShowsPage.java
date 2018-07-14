package com.aca.stage.shows.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aca.stage.model.Shows;

public class TheRepShowsPage {
	
	//private WebDriver driver;
	
	public TheRepShowsPage(WebDriver driver) {
		//this.driver=driver;
	}
	
	
	@FindBy(xpath="//*[@id=\'content\']")
	public WebElement showList;
	
	public List<WebElement> getListOfShows() {
		List<WebElement> listOfShows = 
				showList.findElements(By.tagName("div"));
		return listOfShows;
	}

	public List<Shows> getInformation() { 

		List<Shows> shows = new ArrayList<Shows>();
		List<WebElement> listOfShows = getListOfShows();
		String descriptionLink = "", title = "", pictureUrl = "";

		for(WebElement showElement : listOfShows) { 
			
			String comment = showElement.getAttribute("class");
			if (comment.equals("media")) {
				WebElement elImg = showElement.findElement(By.cssSelector("div > a "));
				pictureUrl = elImg.findElement(By.tagName("img")).getAttribute("src");
				
			}
			

			String classComment = showElement.getAttribute("class"); 
			if (classComment.equals("media-body")) {
				
				WebElement element = showElement.findElement(By.cssSelector(" div > h4  "));
				if (element.getAttribute("class").contains("media-heading")) {
					element = showElement.findElement(By.cssSelector("h4 > a"));

					descriptionLink = element.getAttribute("href");
					title = element.getText();
				}

				Shows show = new Shows();
				show.setTheatreName("Arkansas Repertory Theatre");
				show.setTitle(title);
				show.setDescriptionLink(descriptionLink);
				show.setPictureUrl(pictureUrl);

				int count = 0;
				for (Shows s : shows) {
					if(show.getTitle().equals(s.getTitle())) {
						count++;
					}
				}
				if (count == 0) {
					//System.out.println("Add Show");
					//System.out.println(show.toString());
					shows.add(show);
				}
			}



		}	
		return shows;
	}	






}

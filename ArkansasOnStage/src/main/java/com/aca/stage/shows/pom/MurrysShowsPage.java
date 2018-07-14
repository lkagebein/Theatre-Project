package com.aca.stage.shows.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aca.stage.model.Shows;


public class MurrysShowsPage {

	WebDriver driver;

	public MurrysShowsPage (WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath="//*[@id='content']/ul[2]")
	public WebElement showList;

	public List<WebElement> getListOfShows() {
		List<WebElement> listOfMurrysShows = 
				showList.findElements(By.tagName("li"));
		return listOfMurrysShows;
	}

	public List<Shows> getInformation() {

		List<Shows> shows = new ArrayList<Shows>();
		List<WebElement> listOfShows = getListOfShows();

		for(WebElement showElement : listOfShows) {

			WebElement element = showElement.findElement(By.cssSelector("div > a"));
			String descriptionLink = element.getAttribute("href");
			String title = element.getAttribute("title");
			String pictureUrl = element.findElement(By.tagName("img")).getAttribute("src");

			Shows show = new Shows();
			show.setTheatreName("Murry's Dinner Playhouse");
			show.setTitle(title);
			show.setDescriptionLink(descriptionLink);
			show.setPictureUrl(pictureUrl);

			//			System.out.println("Add Show");
			//			System.out.println(show.toString());
			//			
			shows.add(show);

		}

		return shows;
	}	






}

package com.aca.stage.shows.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aca.stage.model.Shows;


public class ReynoldsShowsPage {
	
	WebDriver driver;

	public ReynoldsShowsPage (WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(css="#content > div > div")
	public WebElement ShowList2018;

	public List<WebElement> getListOf2018Shows() {
		List<WebElement> listOfReynoldsShows = 
				ShowList2018.findElements(By.tagName("div"));
		return listOfReynoldsShows;
	}

	public List<Shows> getInformation() {

		List<Shows> shows = new ArrayList<Shows>();
		List<WebElement> listOfShows = getListOf2018Shows();

		for (WebElement showElement : listOfShows) {

			String classComment = showElement.getAttribute("class");

			if (classComment.contains("one-third")) {

				WebElement imgOfShowElement = showElement.findElement(By.tagName("img"));
				String pictureUrl = imgOfShowElement.getAttribute("src");

				String title = showElement.findElement(By.tagName("strong")).getText();

				WebElement aElement = showElement.findElement (By.cssSelector("a"));
				String descriptionLink = aElement.getAttribute("href");

				Shows show = new Shows();
				show.setTheatreName("Reynolds Performance Hall");
				show.setTitle(title);
				show.setDescriptionLink(descriptionLink);
				show.setPictureUrl(pictureUrl);

				shows.add(show);
			}
		}

		return shows;
	}	
}



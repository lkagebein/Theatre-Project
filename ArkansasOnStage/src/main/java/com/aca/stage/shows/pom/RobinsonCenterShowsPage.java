package com.aca.stage.shows.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aca.stage.model.Shows;


public class RobinsonCenterShowsPage {

	private WebDriver driver;

	public RobinsonCenterShowsPage(WebDriver driver) {
		this.driver=driver;
	}

	@FindBy(xpath="//*[@id=\'show-page\']/div[2]/div[1]/div")
	public WebElement showList;

	public List<WebElement> getListOfShows() {
		List<WebElement> listOfShows = 
				showList.findElements(By.tagName("div"));
		return listOfShows;
	}

	public List<Shows> getInformation() {

		List<Shows> shows = new ArrayList<Shows>();
		List<WebElement> listOfShows = getListOfShows();

		for(WebElement showElement : listOfShows) {

			WebElement element = showElement.findElement(By.cssSelector("div > a"));
			String descriptionLink = element.getAttribute("href");
			String title = element.findElement(By.tagName("img")).getAttribute("showname");
			String pictureUrl = element.findElement(By.tagName("img")).getAttribute("src");

			Shows show = new Shows();
			show.setTheatreName("Robinson Center");
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
				shows.add(show);
			}


		}


		List<Shows> additional = new ArrayList<Shows>();
		additional = getExtraInformation();
		for(Shows a : additional) {
			shows.add(a);
		}

		return shows;
	}	


	@FindBy(xpath="//*[@id=\"about-page\"]/div") 
	public WebElement moreShows;

	public List<WebElement> getListOfMoreShows() {
		List<WebElement> listOfMoreShows = 
				moreShows.findElements(By.tagName("div"));
		return listOfMoreShows;
	}

	public List<Shows> getExtraInformation() {
		driver.get("https://www.celebrityattractions.com/showinfo/alternativeshows.asp?locid=4");
		List<Shows> shows = new ArrayList<Shows>();
		List<WebElement> listOfMoreShows = getListOfMoreShows();
		String descriptionLink = "", title = "", pictureUrl = "";
		int temp = 1;
		for(WebElement showElement : listOfMoreShows) {

			if (temp == 1) {
				WebElement eDescription = showElement.findElement(By.cssSelector(" div > a"));
				descriptionLink = eDescription.getAttribute("href");

				WebElement element = showElement.findElement(By.cssSelector(" a > img"));
				pictureUrl = element.getAttribute("src");

				WebElement eTitle = showElement.findElement(By.cssSelector("div > a > h1"));
				title = eTitle.getText();
			}

			if (temp == 2) {

			}

			if(temp ==3) {
				Shows show = new Shows();
				show.setTheatreName("Robinson Center");
				show.setTitle(title);
				show.setDescriptionLink(descriptionLink);
				show.setPictureUrl(pictureUrl);
				shows.add(show);
				temp = 0;
			}

			temp++;

		}
		return shows;
	}
}
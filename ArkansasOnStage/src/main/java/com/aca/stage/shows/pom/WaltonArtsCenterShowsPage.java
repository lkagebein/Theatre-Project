package com.aca.stage.shows.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aca.stage.model.Shows;


public class WaltonArtsCenterShowsPage {

	private WebDriver driver;
	List<Shows> shows = new ArrayList<Shows>();

	public WaltonArtsCenterShowsPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath="//*[@id=\'main\']/div[3]/div/div")
	public WebElement seriesList;

	public List<WebElement> getListOfAllSeries() {
		List<WebElement> listOfAllSeries = 
				seriesList.findElements(By.tagName("p"));
		return listOfAllSeries;
	}

	@FindBy(xpath="//*[@id=\'main\']/section[2]/div/div")
	public WebElement showList;

	public List<WebElement> getListOfAllShows() {
		return showList.findElements(By.tagName("article"));
	}





	public List<String> getSeriesInformation() {

		List<String> series = new ArrayList<String>();
		List<WebElement> listOfAllSeries = getListOfAllSeries();
		for (WebElement seriesElement : listOfAllSeries) {
			WebElement element = seriesElement.findElement(By.cssSelector("p > a"));
			String seriesLink = element.getAttribute("href");
			series.add(seriesLink);
		}

		return series;

	}

	public List<Shows> getInformation() {

		List<String> allSeries = getSeriesInformation();

		for (String series : allSeries) {
			driver.get(series);
			
			if (series.endsWith("additional-shows/")) {

			} else {
				List<WebElement> listOfAllShows = getListOfAllShows();

				for (WebElement showElement : listOfAllShows) {  

					WebElement element = showElement.findElement(By.cssSelector("div> a"));
					String descriptionLink = element.getAttribute("href");
					String pictureUrl = element.findElement(By.tagName("img")).getAttribute("src");
					WebElement el = showElement.findElement(By.cssSelector("div > h3 > a"));
					String title = el.getText();  
					

					Shows show = new Shows();
					show.setTheatreName("Walton Arts Center");
					show.setTitle(title);
					show.setDescriptionLink(descriptionLink);
					show.setPictureUrl(pictureUrl);

					shows.add(show);
				}
			}	
		}
		
		return shows;
	}

}









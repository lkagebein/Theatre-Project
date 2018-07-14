package com.aca.stage.performance.pom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aca.stage.conversions.MurrysPerformanceConversion;
import com.aca.stage.model.Performances;

public class MurrysPerformancesPage {

	WebDriver driver;

	public MurrysPerformancesPage (WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath="//*[@id='content']/ul[2]")
	public static WebElement showList;

	public static List<WebElement> getListOfShows() {
		List<WebElement> listOfMurrysShows = 
				showList.findElements(By.tagName("li"));
		return listOfMurrysShows;
	}
	
	public static int getNumberOfSHows2018() {
		List<WebElement> listOfShows = getListOfShows();
		System.out.println("N: " + listOfShows.size());
		return listOfShows.size();
	}

	public List<Performances> getInformation() {

		//getNumberOfSHows2018();
		
		List<Performances> performances = new ArrayList<Performances>();
		performances.clear();
		List<WebElement> listOfShows = getListOfShows();
		//List <Performances> temp = new ArrayList<Performances>();
		String title = "", performDate = "";
		
		
		for(WebElement showElement : listOfShows) {

			
			WebElement element = showElement.findElement(By.cssSelector("div > a"));
			title = element.getAttribute("title");
			//System.out.println("T: " + title);
			performDate = element.getText();
			//System.out.println("D: " + performanceDate);
			

			List <Date> dateList = new ArrayList<Date>();
			dateList = MurrysPerformanceConversion.convertToDateList(performDate);
			
			
			for (Date d : dateList) {
				//System.out.println(d.toString());
				Performances perform = null;
				perform = new Performances();
				perform.setTheatreName("Murry's Dinner Playhouse");
				perform.setTitle(title);
				perform.setPerformanceDate(d);
				//System.out.println("D1: " + d);
				perform.setPerformanceTime(MurrysPerformanceConversion.timeConversion(d));
				performances.add(perform);
				//System.out.println("D2: " + performance.getPerformanceDate().toString());
			}
			dateList.clear();
		}

//		for (Performances p : performances) {
//			System.out.println("D: " + p.getPerformanceDate().toString());
//		}
		return performances;
	}	



}

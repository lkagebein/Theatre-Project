package com.aca.stage.performance.pom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aca.stage.conversions.MurrysPerformanceConversion;
import com.aca.stage.conversions.ReynoldsPerformanceConversions;
import com.aca.stage.model.Performances;

public class ReynoldsPerformancesPage {
	WebDriver driver;

	public ReynoldsPerformancesPage (WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(css="#content > div > div")
	public WebElement PerformanceList2018;

	public List<WebElement> getListOf2018Performances() {
		List<WebElement> listOfReynoldsPerformances = 
				PerformanceList2018.findElements(By.tagName("div"));
		return listOfReynoldsPerformances;
	}

	public List<Performances> getInformation() {

		List<Performances> performances = new ArrayList<Performances>();
		List<WebElement> listOfPerformances = getListOf2018Performances();

		for (WebElement performanceElement : listOfPerformances) {

			String classComment = performanceElement.getAttribute("class");

			if (classComment.contains("one-third")) {

				WebElement imgOfPerformanceElement = performanceElement.findElement(By.tagName("img"));

				String title = performanceElement.findElement(By.tagName("strong")).getText();
				
				String allText = performanceElement.getText();
				//System.out.println(allText);
				
				Performances performance = new Performances();

				List <Date> dateList = new ArrayList<Date>();
				dateList = ReynoldsPerformanceConversions.convertDate(allText);
				
				List <String> timeList = new ArrayList<String>();
				timeList = ReynoldsPerformanceConversions.convertTime(allText);
				
				for (int i = 0; i < dateList.size(); i++) {
					performance.setTheatreName("Reynolds Performance Hall");
					performance.setTitle(title);
					performance.setPerformanceDate(dateList.get(i));
					performance.setPerformanceTime(timeList.get(i));
					
					performances.add(performance);
//					System.out.println("Add performance:");
//					System.out.println(performance.toString());
				}

			}
		}

		return performances;
	}	
}

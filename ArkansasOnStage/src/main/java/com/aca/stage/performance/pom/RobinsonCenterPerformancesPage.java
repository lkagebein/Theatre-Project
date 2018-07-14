package com.aca.stage.performance.pom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aca.stage.conversions.RobinsonPerformanceConversions;
import com.aca.stage.model.Performances;

public class RobinsonCenterPerformancesPage {

	private WebDriver driver;

	public RobinsonCenterPerformancesPage(WebDriver driver) {
		this.driver=driver;
	}

	@FindBy(xpath="//*[@id=\'show-page\']/div[2]/div[1]/div")
	public WebElement performanceList;

	public List<WebElement> getListOfPerformances() {
		List<WebElement> listOfPerformances = 
				performanceList.findElements(By.tagName("div"));
		return listOfPerformances;
	}

	@FindBy(xpath="//*[@id=\'show-page\']/div/div[1]/h2[1]")
	public WebElement performanceTitle;

	public String getTitle() {
		return performanceTitle.getText();
	}

	@FindBy(xpath="//*[@id='show-page']/div/div[1]/div[1]/div[2]/p[1]/strong") 
	public WebElement performanceDates;

	public String getPerformanceDates() {
		return performanceDates.getText();
	}



	public List<Performances> getInformation() {

		List<Performances> performances = new ArrayList<Performances>();
		List<WebElement> listOfPerformances = getListOfPerformances();
		List<String> descriptions = new ArrayList<String>();

		for(WebElement performanceElement : listOfPerformances) {
			WebElement element = performanceElement.findElement(By.cssSelector("div > a"));
			String descriptionLink = element.getAttribute("href");
			int count = 0;
			for (String d : descriptions) {
				if (d.equals(descriptionLink)) {
					count++;
				}
			}
			if (count == 0) {
				descriptions.add(descriptionLink);	
			}
		}

		for (String d : descriptions) {
			driver.get(d);
			String title = getTitle();
			title = RobinsonPerformanceConversions.convertTitle(title);
			String date = getPerformanceDates();

			List <Date> allDates = RobinsonPerformanceConversions.convertToDate(date);

			for (Date da : allDates) {
				Performances performance = new Performances();
				performance.setTheatreName("Robinson Center");
				performance.setPerformanceDate(da);
				performance.setTitle(title);
				performance.setPerformanceTime("TBA");
				performances.add(performance);
				//System.out.println("Per: " + performance.toString());

			}
		}

		List<Performances> additional = new ArrayList<Performances>();
		additional = getExtraInformation();
		for(Performances a : additional) {
			int c = 0;
			for (Performances per: performances) {
				if (per.getTitle().equals(a.getTitle()) &&
						per.getPerformanceDate().equals(a.getPerformanceDate()) &&
						per.getPerformanceTime().equals(a.getPerformanceTime())) {
					c++;
				}
			}
			if (c == 0) {
				performances.add(a);
				//System.out.println("Per: " + a.toString());
			}
		}
		return performances;
	}	


	@FindBy(xpath="//*[@id=\"about-page\"]/div") 
	public WebElement morePerformances;

	public List<WebElement> getListOfMorePerformances() {
		List<WebElement> listOfMorePerformances = 
				morePerformances.findElements(By.tagName("div"));
		return listOfMorePerformances;
	}

	@FindBy(xpath="//*[@id=\'show-page\']/div/div[4]/div/div/div[3]")    //"//*[@id=\"show-page\"]/div/div[4]/div/div/div[3]/div")
	public WebElement timeSlots;

	public List<WebElement> getListOfTimeSlots() {
		List<WebElement> listOfTimeSlots = 
				timeSlots.findElements(By.tagName("div"));
		return listOfTimeSlots;
	}

	@FindBy(xpath="//*[@id=\'show-page\']/div/div[2]/h2")
	public WebElement altTitle;

	public String getAltTitle() {
		return altTitle.getText();
	}

	@FindBy(xpath="//*[@id=\'show-page\']/div/div[2]/div[1]/div[2]/p[1]/strong") 
	public WebElement altperformanceDates;

	public String getaltPerformanceDates() {
		return altperformanceDates.getText();
	}



	public List<Performances> getExtraInformation() {
		driver.get("https://www.celebrityattractions.com/showinfo/alternativeshows.asp?locid=4");
		List<Performances> performances = new ArrayList<Performances>();
		List<WebElement> listOfMorePerformances = getListOfMorePerformances();
		List<String> descriptions = new ArrayList<String>();
		String descriptionLink = "", title = "";

		int temp = 1;
		for(WebElement performanceElement : listOfMorePerformances) {

			if (temp == 1) {
				WebElement eDescription = performanceElement.findElement(By.cssSelector(" div > a"));
				descriptionLink = eDescription.getAttribute("href");
			}

			if (temp == 2) {

			}

			if(temp ==3) {
				descriptions.add(descriptionLink);
				temp = 0;
			}

			temp++;
		}

		for (String d : descriptions) {
			driver.get(d);
			title = getAltTitle();

			List<WebElement> timeSlots = getListOfTimeSlots();
			for(WebElement timeElement : timeSlots) {
				String t = timeElement.getText();
				List<String> convertTime = RobinsonPerformanceConversions.convertTime (t);

				for (int i = 0; i < convertTime.size(); i = i + 2) {
//					System.out.println("Title: " + title);
//					System.out.println("Date: " + convertTime.get(i));
//					System.out.println("Time: " + convertTime.get(i + 1));

					Performances performance = new Performances();
					performance.setTheatreName("Robinson Center");
					Date dateConverted = RobinsonPerformanceConversions.singleDate(convertTime.get(i));
					performance.setPerformanceDate(dateConverted);
					performance.setTitle(title);
					String timeConverted = RobinsonPerformanceConversions.timeTrim(convertTime.get(i+1));
					performance.setPerformanceTime(timeConverted);
					performances.add(performance);
					//System.out.println(performance);
				}
			}
		}

		return performances;
	}

}

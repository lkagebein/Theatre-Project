package com.aca.pom;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MurrysShows {

	private WebDriver driver;

	public MurrysShows(WebDriver driver){
		this.driver = driver;
	}

	@FindBy(xpath="//*[@id='page-masthead']/div[1]/div[2]/div[1]/strong")
	public WebElement phoneNumber;

	@FindBy(xpath="//*[@id='page-masthead']/div[1]/div[2]/div[1]/a")
	public WebElement contactLink;

//	@FindBy(css="#content > h1:nthchild(3)")
//	public WebElement shows2018;

	@FindBy(xpath="//*[@id='content']/ul[2]")
	public static WebElement unordered2018ShowList;

	@FindBy(css="div.show-copy > p:nth-child(4)")
	public WebElement description;



	public String getPhoneNumber(){
		return phoneNumber.getText();
	}

	public String getContactLink(){
		return contactLink.getAttribute("href");
	}

	public String getHomePageTitle(){
		return driver.getTitle();
	}

	public static List<WebElement> getListOf2018Shows() {
		List<WebElement> listOfMurrysShows = 
				unordered2018ShowList.findElements(By.tagName("li"));
		return listOfMurrysShows;
	}
	
	public String getDescription() {
		return description.getText();
	}
}
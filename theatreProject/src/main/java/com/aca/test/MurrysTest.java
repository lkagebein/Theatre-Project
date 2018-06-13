package com.aca.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import com.aca.pom.MurrysShows;
import com.aca.service.CurrentShows;
import com.aca.service.Show;

public class MurrysTest {

	private WebDriver driver;
	private MurrysShows murrysShows;
	
	@Before
	public void setUp() throws Exception {
		try {
			System.setProperty("webdriver.chrome.driver", 
					"target/classes/chrome-2.39/chromedriver.exe");
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("chrome.switches", "--disable-extensions");
			
			this.driver = new ChromeDriver(options);
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("http://murrysdp.com/the-shows/");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		murrysShows = PageFactory.initElements(driver, MurrysShows.class);
	}

	@After
	public void tearDown() throws Exception {
		this.driver.quit();
	}

	@Test
	public void testGetPhoneNumber() {
		assertEquals("phone number must be 501.562.3131", 
				murrysShows.getPhoneNumber(), 
				"501.562.3131");
	}
	
	@Test
	public void testGetContactLink() {
		assertEquals("address must be http://murrysdp.com/contact", 
				murrysShows.getContactLink(), 
				"http://murrysdp.com/contact");	
	}

	@Test
	public void testGetTitle() {
		assertEquals("Page tite", 
				murrysShows.getHomePageTitle(),
				"The Shows | Murry's Dinner Playhouse");
	}
	
	@Test
	public void getAllShowInfo() {
		System.out.println(" number of 2018 shows: " +
			CurrentShows.getNumberOfShows2018());
		
		List<Show> shows = CurrentShows.get2018Shows();
		
		for (Show show : shows) {
			System.out.println("===============================");
			System.out.println(show.toString());
		}
	}
	 
	@Test
	public void getDescription() {
		List<Show> shows = CurrentShows.get2018Shows();
		
		for (Show show : shows) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(show.getLink());		
			show.setDescription(murrysShows.getDescription());
			System.out.println("===============================");
			System.out.println(show.toString());
			//System.out.println(murrysShows.getDescription());
		}
		
		System.out.println("Test");
		
	}
}







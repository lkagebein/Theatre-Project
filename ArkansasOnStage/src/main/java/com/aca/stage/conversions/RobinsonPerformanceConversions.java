package com.aca.stage.conversions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.aca.stage.service.DateIterator;


public class RobinsonPerformanceConversions {

	public static String convertTitle(String title) {

		int index = title.indexOf('-');

		return title.substring(0, index);

	}

	@SuppressWarnings("deprecation")
	public static List<Date> convertToDate(String s) {

		List<Date> performanceDates = new ArrayList<Date>();

		if (s.contains("-")) {

			int index = s.indexOf(' ');
			String month = s.substring(0,index);
			s = s.substring(index);
			index = s.indexOf('-');
			String sDay = s.substring(1, index);
			s = s.substring(index);
			index = s.indexOf(',');
			String eDay = s.substring(1, index);
			String year = s.substring(index+1);

			DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
			Date start = null, end = null;
			try {
				start = format.parse(month + " " + sDay + ", " + year);
				end = format.parse(month + " " + eDay + ", " + year);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Iterator<Date> i = new DateIterator(start, end);
			while(i.hasNext())
			{
				Date date = i.next();

				if (date.getDay() == 1) {

				} else {
					performanceDates.add(date);
				}
			}
			//return performanceDates;
			
		} else {
			
			int index = s.indexOf(' ');
			String month = s.substring(0,index);
			s = s.substring(index + 1);
			index = s.indexOf(',');
			String sDay = s.substring(0, index);
			s = s.substring(index);
			String year = s.substring(index+2);

			DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
			Date start = null;
			try {
				start = format.parse(month + " " + sDay + ", " + year);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			performanceDates.add(start);
		}
		
		return performanceDates;
	}


	public static Date singleDate(String s) {
		
//		int index = s.indexOf(' ');
//		String month = s.substring(0,index);
//		System.out.println("M: " + month);
//		s = s.substring(index + 1);
//		index = s.indexOf(',');
//		String sDay = s.substring(0, index);
//		System.out.println("D: " + sDay);
//		s = s.substring(index);
//		String year = s.substring(index+2);
//		System.out.println("Y: " + year);

		DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		Date start = null;
		try {
			//start = format.parse(month + " " + sDay + ", " + year);
			start = format.parse(s.substring(1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return start;
	}
	
	public static List<String> convertTime (String line) {

		String [] lines = line.split("\\r?\\n");

		List<String> timeString = new ArrayList<String>();
		
		for (String s : lines) {
			if (s.contains("$")) {
			}
			else {
				timeString.add(s);
			}
		}
		return timeString;
	}

	public static String timeTrim(String s) {
		
		int index = s.indexOf('(');
		s = s.substring(index+1, s.length()-1);
		
		return s;
	}

}


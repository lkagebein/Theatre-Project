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


public class MurrysPerformanceConversion {

	private static List<Date> performanceDates = new ArrayList<Date>();

//	public static void main (String[] args) {
//
//		String converting = "May 29 - July 5";
//
//		convertToDateList(converting);
//
//	}

	public static List<Date> convertToDateList(String converting)  {

		String year = "2018";
		converting = converting + " " + year;
		String[] temp = converting.split("\\s+");
		temp[2] = year;
		converting = "";
		for (String i : temp) {
			converting = converting + i + " ";
		}

		if (temp[0].equals("Sept")) {
			temp[0] = "Sep";
		}
		if (temp[3].equals("Sept")) {
			temp[3] = "Sep";
		}

		return convertStringToListOfDates(temp);
	}

	@SuppressWarnings("deprecation")
	public static List<Date> convertStringToListOfDates (String[] temp) {

		DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		Date start = null, end = null;
		try {
			start = format.parse(temp[0] + " " + temp[1] + ", " + temp[2]);
			end = format.parse(temp[3] + " " + temp[4] + ", " + temp[5]);
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

		return performanceDates;
	}

	@SuppressWarnings("deprecation")
	public static String timeConversion(Date d) {
		String performanceTime = "";

		switch (d.getDay()) {

		case 0: performanceTime = "12:45 p.m. & 6:45 p.m."; break;
		case 3: performanceTime = "12:45 p.m & 7:30 p.m."; break;
		case 2: 
		case 4:
		case 5:
		case 6: performanceTime = "7:30 p.m."; break;
		default: System.out.println("Invalid"); break;

		}

		return performanceTime;
	}
}

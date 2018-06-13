package com.aca.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowTimeConversion {

	public static List<Date> convertToDateArray(String times) throws ParseException {
		List <String> date = new ArrayList <String> ();
		String temp = "";
		for (int i = 0; i < times.length(); i++) {
			if (times.charAt(i) == ' ') {
				temp = convert(temp);
				date.add(temp);		
				temp = "";	
			}else{
				temp = temp + times.charAt(i);
				if (i == (times.length() -1)) {
					temp = convert(temp);
					date.add(temp);
				}
			}
		}
		
		Date startDate = new SimpleDateFormat("MM/dd/yyyy").parse(date.get(0) + "/" + date.get(1) + "/" + date.get(2));
		Date endDate = new SimpleDateFormat("MM/dd/yyyy").parse(date.get(3) + "/" + date.get(4) + "/" + date.get(5));
		System.out.println(startDate +"\t" + endDate);
		
		List <Date> dates = new ArrayList <Date> ();
		dates.add(startDate);
		dates.add(endDate);
		
		return dates;
		
	}
	
	private static String convert (String monthConversion) {

		switch (monthConversion) {
		case "January": monthConversion = "01";
		break;
		case "Feb": monthConversion = "02";
		break;
		case "Mar": monthConversion = "03";
		break;
		case "Apr": monthConversion = "04";
		break;
		case "May": monthConversion = "05";
		break;
		case "June": monthConversion = "06";
		break;
		case "July": monthConversion = "07";
		break;
		case "Aug": monthConversion = "08";
		break;
		case "Sept": monthConversion = "09";
		break;
		case "Oct": monthConversion = "10";
		break;
		case "Nov": monthConversion = "11";
		break;
		case "Dec": monthConversion = "12";
		break;
		default: break;
		}
		
		if (monthConversion.length() == 1) {
			monthConversion = "0" + monthConversion;
		}
		
		return monthConversion;
	}
	
/*
	public static void main (String[] args) throws ParseException  {
		String times = "May 29 - July 7";
		String year = "2018";
		times = times + " " + year;
		String[] temp = times.split("\\s+");
		temp[2] = year;
		times = "";
		for (String i : temp) {
			times = times + i + " ";
		}
		//List <String> date = new ArrayList <String> ();		
		convertToDateArray(times);
	}
*/
}

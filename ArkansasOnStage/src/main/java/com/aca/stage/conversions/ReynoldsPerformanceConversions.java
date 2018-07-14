package com.aca.stage.conversions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReynoldsPerformanceConversions {

	public static List<Date> convertDate (String line) {

		String [] lines = line.split("\\r?\\n");

		List<Date> dateString = new ArrayList<Date>();

		Date date = null;

		for (int i = 0; i < lines.length; i++) {

			if (i != 0 && i < lines.length -1) {
				String[] temp = lines[i].split("\\s+");

				temp[0] = temp[0].substring(0, 3);

				if (temp[1].endsWith(",")) {
					temp[1] = temp[1].substring(0, temp[1].length()-1);
				}

				DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
				date = null;
				try {
					if (temp[2].equals("2018") || temp[2].equals("2019")) {
						date = format.parse(temp[0] + " " + temp[1] + ", " + temp[2]);
					} else {
						date = format.parse(temp[0] + " " + temp[1] + ", " + "2019");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				dateString.add(date);	
			}
		}
		return dateString;
	}
	
	public static List<String> convertTime (String line) {

		String [] lines = line.split("\\r?\\n");

		List<String> timeString = new ArrayList<String>();

		String time = "";

		for (int i = 0; i < lines.length; i++) {

			if (i != 0 && i < lines.length -1) {
				time = lines[i];
				int index = time.indexOf("at");
				time = time.substring(index + 3);
				time = time.replace("and", "&");

				timeString.add(time);	
			}
		}
		return timeString;
	}
}















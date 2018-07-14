package com.aca.stage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aca.stage.model.Shows;


public class ShowsDao {

	private final static String ShowsInfo =
			" Theatre, Title, DescriptionLink, PictureUrl ";

	private final static String sqlSelectAllShows =
			" SELECT " + ShowsInfo + 
			" FROM shows "+
			" ORDER BY title ASC ";

	private final static String sqlInsertShows = 
			" INSERT INTO shows ( " + ShowsInfo + " ) " +
					" VALUES ( ?, ?, ?, ? ) ";

	private final static String sqlgetShowInformationByTitle =
			" SELECT " + ShowsInfo + 
			" FROM shows " +
			" WHERE title = ? ";
	
	private final static String sqlGetShowsByVenue =
			" SELECT " + ShowsInfo + 
			" FROM shows " +
			" WHERE theatre = ? ";
	
	private final static String sqlClearDatabase =
			" DELETE FROM shows "; 

	public static List<Shows> getShowsInformation() {

		List<Shows> shows = new ArrayList<Shows>();

		ResultSet result = null;
		Statement statement = null;
		Connection conn = MariaDbUtil.getConnection();

		try {
			statement = conn.createStatement();
			result = statement.executeQuery(sqlSelectAllShows);

			while (result.next()) {
				Shows show = createShows(result);
				shows.add(show);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return shows;

	}

	private static Shows createShows (ResultSet result) throws SQLException {

		Shows shows = new Shows();

		shows.setTheatreName(result.getString("Theatre"));
		shows.setTitle(result.getString("Title"));
		shows.setDescriptionLink(result.getString("DescriptionLink"));
		shows.setPictureUrl(result.getString("PictureUrl"));
		
		return shows;
	}

	public static void insertShows(List<Shows> shows) {

		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();

		if (shows.isEmpty()) {
			System.out.println("No Shows Added");
		} else {
			try {
				for (Shows show : shows) {

					//Theatre, Title, DescriptionLink, PictureUrl
					statement = conn.prepareStatement(sqlInsertShows);
					statement.setString(1, show.getTheatreName());
					statement.setString(2, show.getTitle());
					statement.setString(3, show.getDescriptionLink());
					statement.setString(4, show.getPictureUrl());
					statement.executeUpdate();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					statement.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static List<Shows> getShowsByVenue(String theatre) {
		List<Shows> shows = new ArrayList<Shows>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlGetShowsByVenue);
			statement.setString(1,  theatre);
			result = statement.executeQuery();
			
			while(result.next())
			{
				Shows show = createShows(result);
				shows.add(show);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return shows;
	}

	public static List<Shows> getShowInformationByTitle(String title) {
List<Shows> shows = new ArrayList<Shows>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlgetShowInformationByTitle);
			statement.setString(1,  title);
			result = statement.executeQuery();
			
			while(result.next())
			{
				Shows show = createShows(result);
				shows.add(show);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return shows;
	}
	
	public static void clearDatabase () {

		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();

		try {
			statement = conn.prepareStatement(sqlClearDatabase);
			statement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//	public static void main (String[] args) {
	//		ShowsDao ven = new ShowsDao();
	//		List <Shows> v = new ArrayList<Shows>();
	//		v = ven.getShowsInformation();
	//		for (Shows ve : v) {
	//			System.out.println("Info: " + ve.toString());
	//		}
	//		
	//		
	//	}
}










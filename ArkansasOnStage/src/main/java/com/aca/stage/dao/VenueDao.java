package com.aca.stage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aca.stage.model.Venue;

public class VenueDao {

	private final static String venueInfo =
			" Theatre, Icon, Homepage, Address, Phone, Ticket ";

	private final static String sqlSelectAllVenue =
			" SELECT " + venueInfo + 
			" FROM venue ";

	private final static String sqlInsertVenue = 
			" INSERT INTO venue ( " + venueInfo + " ) " +
					" VALUES ( ?, ?, ?, ?, ?, ?) ";

	private final static String sqlGetSingleVenueInformation =
			" SELECT " + venueInfo + 
			" FROM venue " +
			" WHERE theatre = ? ";
	
	private final static String sqlClearDatabase =
			" DELETE FROM venue " ;
	

	public static List<Venue> getVenueInformation() {

		List<Venue> venues = new ArrayList<Venue>();

		ResultSet result = null;
		Statement statement = null;
		Connection conn = MariaDbUtil.getConnection();

		try {
			statement = conn.createStatement();
			result = statement.executeQuery(sqlSelectAllVenue);

			while (result.next()) {
				Venue venue = createVenue(result);
				venues.add(venue);
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

		return venues;

	}

	private static Venue createVenue (ResultSet result) throws SQLException {

		Venue venue = new Venue();

		venue.setVenueName(result.getString("Theatre"));
		venue.setIcon(result.getString("Icon"));
		venue.setHomepage(result.getString("Homepage"));
		venue.setAddress(result.getString("Address"));
		venue.setPhoneNumber(result.getString("Phone"));
		venue.setTicketInfoSite(result.getString("Ticket"));

		return venue;
	}

	public static void insertVenue(List<Venue> venues) {

		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();

		if (venues.isEmpty()) {
			System.out.println("No Venues Added");
		} else {
			try {
				for (Venue venue : venues) {

					//Theatre, Icon, Homepage, Address, Phone, Ticket
					statement = conn.prepareStatement(sqlInsertVenue);
					statement.setString(1, venue.getVenueName());
					statement.setString(2, venue.getIcon());
					statement.setString(3, venue.getHomepage());
					statement.setString(4, venue.getAddress());
					statement.setString(5, venue.getPhoneNumber());
					statement.setString(6, venue.getTicketInfoSite());
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

	
	public static List<Venue> getSingleVenueInformation(String theatre) {
		
		List<Venue> venues = new ArrayList<Venue>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlGetSingleVenueInformation);
			statement.setString(1,  theatre);
			result = statement.executeQuery();
			
			while(result.next())
			{
				Venue venue = createVenue(result);
				venues.add(venue);
				
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
		return venues;
	}

	//	public static void main (String[] args) {
	//		VenueDao ven = new VenueDao();
	//		List <Venue> v = new ArrayList<Venue>();
	//		v = ven.getVenueInformation();
	//		for (Venue ve : v) {
	//			System.out.println("Info: " + ve.toString());
	//		}
	//		
	//		
	//	}
}










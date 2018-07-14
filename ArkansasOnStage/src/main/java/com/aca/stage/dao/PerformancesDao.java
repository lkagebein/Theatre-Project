package com.aca.stage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aca.stage.model.Performances;

public class PerformancesDao {
	
	private final static String PerformancesInfo =
			" Theatre, Title, PerformanceDate, PerformanceTime ";

	private final static String sqlSelectAllPerformances =
//			" SELECT PerformanceNumber, Theatre, Title, PerformanceDate, PerformanceTime " + 
//	        " FROM performances ";
			" SELECT " + PerformancesInfo + 
			" FROM performances ";

	private final static String sqlInsertPerformances = 
			" INSERT INTO performances ( " + PerformancesInfo + " ) " +
					" VALUES ( ?, ?, ?, ? ) ";

	private final static String sqlgetPerformanceInformationByTitle =
			" SELECT " + PerformancesInfo + 
			" FROM performances " +
			" WHERE title = ? ";

	private final static String sqlGetPerformancesByVenue =
			" SELECT " + PerformancesInfo + 
			" FROM performances " +
			" WHERE theatre = ? ";
	
	private final static String sqlGetPerformancesBySingleDate =
			" SELECT " + PerformancesInfo +  
			" FROM performances " +
			" WHERE performanceDate = ? " +
			" ORDER BY title ASC ";
	
	private final static String sqlGetPerformancesByDateRange =
			" SELECT " + PerformancesInfo +  
			" FROM performances " + 
			" WHERE performanceDate BETWEEN ? AND ? " +
			" ORDER BY performanceDate, title ASC ";

	private final static String sqlClearDatabase =
			" DELETE FROM performances "; 

	public static List<Performances> getPerformancesInformation() {

		List<Performances> performances = new ArrayList<Performances>();

		ResultSet result = null;
		Statement statement = null;
		Connection conn = MariaDbUtil.getConnection();

		try {
			statement = conn.createStatement();
			result = statement.executeQuery(sqlSelectAllPerformances);

			while (result.next()) {
				Performances performance = createPerformances(result);
				performances.add(performance);
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

		return performances;

	}

	private static Performances createPerformances (ResultSet result) throws SQLException {

		Performances performances = new Performances();
		//performances.setId(result.getInt("PerformanceNumber"));
		performances.setTheatreName(result.getString("Theatre"));
		performances.setTitle(result.getString("Title"));
		java.util.Date d = new java.util.Date(result.getDate("PerformanceDate").getTime());
		performances.setPerformanceDate(d);
		performances.setPerformanceTime(result.getString("PerformanceTime"));

		return performances;
	}

	public static void insertPerformances(List<Performances> performances) {

		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();

		if (performances.isEmpty()) {
			System.out.println("No Performances Added");
		} else {
			try {
				for (Performances performance : performances) {

					statement = conn.prepareStatement(sqlInsertPerformances);
					statement.setString(1, performance.getTheatreName());
					statement.setString(2, performance.getTitle());
					java.sql.Date sd = new java.sql.Date(performance.getPerformanceDate().getTime());
					statement.setDate(3, sd);
					statement.setString(4, performance.getPerformanceTime());
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

	public static List<Performances> getPerformancesByVenue(String theatre) {
		List<Performances> performances = new ArrayList<Performances>();

		ResultSet result = null;
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();

		try {
			statement = conn.prepareStatement(sqlGetPerformancesByVenue);
			statement.setString(1,  theatre);
			result = statement.executeQuery();

			while(result.next())
			{
				Performances performance = createPerformances(result);
				performances.add(performance);

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
		return performances;
	}

	public static List<Performances> getPerformanceInformationByTitle(String title) {
		List<Performances> performances = new ArrayList<Performances>();

		ResultSet result = null;
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();

		try {
			statement = conn.prepareStatement(sqlgetPerformanceInformationByTitle);
			statement.setString(1,  title);
			result = statement.executeQuery();

			while(result.next())
			{
				Performances performance = createPerformances(result);
				performances.add(performance);

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
		return performances;
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

	public static List<Performances> getPerformancesBySingleDate(String performanceDate) {
		List<Performances> performances = new ArrayList<Performances>();

		ResultSet result = null;
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();

		try {
			statement = conn.prepareStatement(sqlGetPerformancesBySingleDate);
			//java.sql.Date sd = new java.sql.Date(PerformanceDate.getTime());
			statement.setString(1,  performanceDate);
			result = statement.executeQuery();

			while(result.next())
			{
				Performances performance = createPerformances(result);
				performances.add(performance);

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
		return performances;
	}

	public static List<Performances> getPerformancesByDateRange(String startDate, String endDate) {
		List<Performances> performances = new ArrayList<Performances>();

		ResultSet result = null;
		PreparedStatement statement = null;
		Connection conn = MariaDbUtil.getConnection();

		try {
			statement = conn.prepareStatement(sqlGetPerformancesByDateRange);
			//java.sql.Date sd = new java.sql.Date(startDate.getTime());
			statement.setString(1,  startDate);
			//java.sql.Date ed = new java.sql.Date(endDate.getTime());
			statement.setString(2,  endDate);
			result = statement.executeQuery();

			while(result.next())
			{
				Performances performance = createPerformances(result);
				performances.add(performance);

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
		return performances;
	}


}

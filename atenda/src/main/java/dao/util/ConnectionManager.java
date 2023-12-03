package dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.DataException;

public class ConnectionManager {
	private static final Logger logger= Logger.getLogger(ConnectionManager.class.getName());
	private static final ResourceBundle cfg= ResourceBundle.getBundle("config");
	private static final String DB_URL = "jdbc.url";
	private static final String DB_USER = "jdbc.user";
	private static final String DB_PASSWORD = "jdbc.password";	
	private static final String JDBC_DRIVER = "jdbc.driver.classname";	
	static {
		try {		
			Class.forName(cfg.getString(JDBC_DRIVER));
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, e.getMessage());
		}

	}

	public static final Connection getConnection() throws DataException {
		try {
			
		return DriverManager
				.getConnection(cfg.getString(DB_URL)
						, cfg.getString(DB_USER)
						, cfg.getString(DB_PASSWORD));
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
			throw new DataException(e);
		}
		
	}
	
	public static final void closeResultSet (ResultSet resultSet) {
		try {
			if (resultSet != null)
				resultSet.close();

		} catch (SQLException se) {
			logger.log(Level.SEVERE, se.toString());
		}
	}
	public static final void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null)
				preparedStatement.close();

		} catch (SQLException se) {
			logger.log(Level.SEVERE, se.toString());
		}
	}
	public static final void closeConnection (Connection connection )  {
		try {
			if (connection != null) 
				connection.close();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	public static void closeConnection(Connection connection, boolean commit)
			throws DataException {
	        try {
	            if (connection != null) {
	                if (commit) {
	                	connection.commit();
	                } else {
	                	connection.rollback();                        
	                }
	                connection.close();
	            }
	        } catch (SQLException e) {
	            throw new DataException(e);
	        }
		}
}
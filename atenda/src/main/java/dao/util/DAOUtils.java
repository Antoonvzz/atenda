package dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUtils {
	public static final int geTotalRow(ResultSet resultSet) throws SQLException {
		int totalRows = 0;
		if (resultSet.last()) {
			totalRows = resultSet.getRow();
		}
		resultSet.beforeFirst();
		return totalRows;
	}
}

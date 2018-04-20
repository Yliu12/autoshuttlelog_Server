import java.sql.*;

public class TestOrclConnect {

	public static void main(String[] args) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dbURL = "jdbc:oracle:thin:@tband.aws.bsu.edu:1521/TEST";
			conn = DriverManager.getConnection(dbURL, "bsu_yliu12", "XpW0BU_lo17Drop1");
			System.out.println(dbURL);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
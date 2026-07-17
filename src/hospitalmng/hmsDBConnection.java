package hospitalmng;
import java.sql.Connection;
import java.sql.DriverManager;
public class hmsDBConnection {
	

		static Connection con;

	    public static Connection getConnection() {

	        try {

	            Class.forName("com.mysql.cj.jdbc.Driver");

	            con = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/hospitaldb",
	                    "root",
	                    "Mahi@1258"
	            );

	        } catch(Exception e) {

	            e.printStackTrace();
	        }

	        return con;
	    }
	}




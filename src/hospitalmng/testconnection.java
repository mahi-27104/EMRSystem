package hospitalmng;
import java.sql.Connection;
public class testconnection {
	public static void main(String[] args) {

        Connection con = hmsDBConnection.getConnection();

        if(con != null) {

            System.out.println("Connection Successful");
        }
        else {

            System.out.println("Connection Failed");
        }
    }
}

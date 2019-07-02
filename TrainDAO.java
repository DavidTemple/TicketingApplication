import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainDAO {
	static String DRIVER_NAME = new String("oracle.jdbc.driver.OracleDriver");
	static String DB_URL = new String("jdbc:oracle:thin:@localhost:1521:xe");
	static String USERNAME = new String("hr");
	static String PASSWORD = new String("hr");
	
	Train findTrain(int trainNumber) {
		Train train = null;
		
		try {
			Class.forName(DRIVER_NAME);
			Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from trains where train_no = " + trainNumber);
			while(rs.next()) {
				train = new Train(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
		
		return train;
	}

	public static void main(String[] args) {
		TrainDAO trainConnection = new TrainDAO();
		Train train = trainConnection.findTrain(1001);
		System.out.println(train);
	}

}

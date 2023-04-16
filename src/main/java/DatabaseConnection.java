import java.sql.*;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs4350lab4", "root", ""); //password not included

        return conn;

    }

    public static void getTripFromDB(String start, String dest, Connection conn) throws SQLException{
        String query = "SELECT T.startlocationname, T.destinationname, TOF.date, TOF.scheduledstarttime, TOF.scheduledarrivaltime, TOF.drivername, TOF.busid FROM TRIP AS T, TRIPOFFERING AS TOF WHERE T.tripnumber = TOF.tripnumber AND T.startlocationname = " + "'" + start +  "'" + " AND T.destinationname = " + "'" + dest + "';";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                System.out.print(rs.getString(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.print(rs.getString(4) + ", ");
                System.out.print(rs.getString(5) + ", ");
                System.out.print(rs.getString(6) + ", ");
                System.out.print(rs.getString(7) + ", ");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}

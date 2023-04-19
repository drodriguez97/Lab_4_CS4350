import java.sql.*;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs4350lab4", "root", ""); // password
                                                                                                             // not
                                                                                                             // included

        return conn;

    }

    public static void getTripFromDB(String start, String dest, Connection conn) throws SQLException {
        String query = "SELECT T.startlocationname, T.destinationname, TOF.tripdate, TOF.scheduledstarttime, TOF.scheduledarrivaltime, TOF.drivername, TOF.busid FROM TRIP AS T, TRIPOFFERING AS TOF WHERE T.tripnumber = TOF.tripnumber AND T.startlocationname = "
                + "'" + start + "'" + " AND T.destinationname = " + "'" + dest + "';";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.print(rs.getString(4) + ", ");
                System.out.print(rs.getString(5) + ", ");
                System.out.print(rs.getString(6) + ", ");
                System.out.print(rs.getString(7) + "\n");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteTripFromDB(String tripNum, String date, String time, Connection conn) throws SQLException {
        String query = "DELETE FROM TRIPOFFERING " +
                "WHERE tripnumber = " + tripNum + " AND " +
                "tripdate = '" + date + "' AND " +
                "ScheduledStartTime = '" + time + "'";

        String afterDelete = "SELECT * FROM TRIPOFFERING";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            ResultSet rs = stmt.executeQuery(afterDelete);
            System.out.println("THE TABLE AFTER DELETION: ");
            while (rs.next()) {
                System.out.print(rs.getString(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.print(rs.getString(4) + ", ");
                System.out.print(rs.getString(5) + ", ");
                System.out.print(rs.getString(6) + "\n");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addTripOffering(String tripNum, String date, String startTime, String arrivTime,
            String driverName, String busID, Connection conn) throws SQLException {
        String query = "INSERT INTO TRIPOFFERING VALUES ('" + tripNum + "', '" + date + "', '" + startTime + "', '"
                + arrivTime + "', '" + driverName + "', '" + busID + "')";

        String afterInsert = "SELECT * FROM TRIPOFFERING";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            ResultSet rs = stmt.executeQuery(afterInsert);
            System.out.println("THE TABLE AFTER INSERTION: ");
            while (rs.next()) {
                System.out.print(rs.getString(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.print(rs.getString(4) + ", ");
                System.out.print(rs.getString(5) + ", ");
                System.out.print(rs.getString(6) + "\n");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeTripDriver(String tripNum, String date, String startTime, String driverName,
            Connection conn) throws SQLException {
        String query = "UPDATE TRIPOFFERING " +
                "SET drivername = '" + driverName + "' " +
                "WHERE tripnumber = '" + tripNum + "' AND " +
                "tripdate = '" + date + "' AND " +
                "scheduledstarttime = '" + startTime + "'";

        String afterUpdate = "SELECT * FROM TRIPOFFERING";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            ResultSet rs = stmt.executeQuery(afterUpdate);
            System.out.println("THE TABLE AFTER UPDATE: ");
            while (rs.next()) {
                System.out.print(rs.getString(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.print(rs.getString(4) + ", ");
                System.out.print(rs.getString(5) + ", ");
                System.out.print(rs.getString(6) + "\n");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeTripBusID(String tripNum, String date, String startTime, String busID, Connection conn)
            throws SQLException {
        String query = "UPDATE TRIPOFFERING " +
                "SET busID = '" + busID + "' " +
                "WHERE tripnumber = '" + tripNum + "' AND " +
                "tripdate = '" + date + "' AND " +
                "scheduledstarttime = '" + startTime + "'";

        String afterUpdate = "SELECT * FROM TRIPOFFERING";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            ResultSet rs = stmt.executeQuery(afterUpdate);
            System.out.println("THE TABLE AFTER UPDATE: ");
            while (rs.next()) {
                System.out.print(rs.getString(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.print(rs.getString(4) + ", ");
                System.out.print(rs.getString(5) + ", ");
                System.out.print(rs.getString(6) + "\n");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void displayTripStops(String tripNum, Connection conn) throws SQLException {
        String query = "SELECT * FROM tripstopinfo WHERE tripnumber = '" + tripNum + "'";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("ALL STOPS FOR TRIP NUMBER " + tripNum + ": ");
            while (rs.next()) {
                System.out.print(rs.getString(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.print(rs.getString(4) + "\n");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Display the weekly schedule of a given driver and date
    public static void displayDriverSchedule(String name, String date, Connection conn) throws SQLDataException {
        String query = "SELECT tripnumber, tripdate, scheduledstarttime, scheduledarrivaltime FROM DRIVER  WHERE drivername = '"
                + name + "' + AND tripdate = '" + date + "' ORDER BY scheduledstartime";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("WEEKLY SCHEDULE FOR DRIVER " + name + ": ");
            while (rs.next()) {
                System.out.print(rs.getString(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.print(rs.getString(4) + ", ");
                System.out.print(rs.getString(5) + ", ");
                System.out.print(rs.getString(6) + ", ");
                System.out.print(rs.getString(7) + "\n");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addDriver(String name, String phoneNumber, Connection conn) throws SQLException {
        String query = "INSERT INTO driver VALUES('" + name + "', '" + phoneNumber + "')";

        String afterInsertion = "SELECT * FROM DRIVER;";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            ResultSet rs = stmt.executeQuery(afterInsertion);
            System.out.println("AFTER INSERTION: \n");
            while (rs.next()) {
                System.out.print(rs.getString(1) + ", ");
                System.out.print(rs.getString(2) + "\n");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Add a bus
    public static void addBus(String busID, String busModel, String busYear, Connection conn) throws SQLException {
        String query = "INSERT INTO bus VALUES('" + busID + "', '" + busModel + "', '" + busYear + "')";

        String afterInsertion = "SELECT * FROM BUS;";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            ResultSet rs = stmt.executeQuery(afterInsertion);
            System.out.println("AFTER INSERTION: \n");
            while (rs.next()) {
                System.out.print(rs.getString(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + "\n");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Delete a driver
    public static void deleteBus(String busID, Connection conn) throws SQLException {
        String query = "DELETE bus WHERE busid = '" + busID + "'";

        String afterDelete = "SELECT * FROM BUS;";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            ResultSet rs = stmt.executeQuery(afterDelete);
            System.out.println("AFTER DELETION: \n");
            while (rs.next()) {
                System.out.print(rs.getString(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + "\n");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void recordTripData(String tripNum, String date, String startTime, String stopNum, String arrTime,
            String actualStart,
            String actualArrival, String passIn, String passOut, Connection conn) {

        String query = "INSERT INTO actualtripstopinfo VALUES('" + tripNum + "', '" + date + "', '" + startTime
                + "''" + stopNum + "', '" + arrTime + "', '" + actualStart + "''" + actualArrival + "', '" + passIn
                + "', '" + passOut + "') ";

        String afterInsertion = "SELECT * FROM ACTUALTRIPSTOPINFO;";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            ResultSet rs = stmt.executeQuery(afterInsertion);
            System.out.println("AFTER INSERTION: \n");
            while (rs.next()) {
                System.out.print(rs.getString(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.print(rs.getString(4) + "\n");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
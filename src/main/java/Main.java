import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner myScanner = new Scanner(System.in);
        Connection transitConnection = null;

        try {
            transitConnection = DatabaseConnection.getConnection();
        } catch(SQLException e){
            e.printStackTrace();
        }

        System.out.println("\nWELCOME TO THE POMONA TRANSIT SYSTEM. WHAT WOULD YOU LIKE TO DO?\n" +
                            "==================================================================\n" +
                            "1. DISPLAY ALL TRIPS FOR A GIVEN START LOCATION AND DESTINATION .\n" +
                            "2. \n");

        int option = myScanner.nextInt();
        if(option == 1){
            System.out.println("PLEASE ENTER DESIRED START LOCATION:");
            String start = myScanner.next();
            System.out.println("PLEASE ENTER DESIRED DESTINATION:");
            String dest = myScanner.next();

            try{
                DatabaseConnection.getTripFromDB(start, dest, transitConnection);
            } catch(SQLException e) {
                e.printStackTrace();
            }

        }
        if(option == 2){
            //option 2 code
        }
    }
}

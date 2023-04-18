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
                            "=======================================================================================\n" +
                            "1. DISPLAY ALL TRIPS FOR A GIVEN START LOCATION AND DESTINATION.\n" +
                            "2. DELETE A TRIP OFFERING BASED ON TRIP NUMBER, DATE, AND SCHEDULED START TIME.\n" +
                            "3. ENTER A NEW TRIP OFFERING INTO THE SYSTEM.\n" +
                            "4. CHANGE THE DRIVER OF A CURRENT TRIP OFFERING.\n" +
                            "5. CHANGE THE BUS ID OF A CURRENT TRIP OFFERING. \n" +
                            "6. DISPLAY ALL STOPS FOR A GIVEN TRIP NUMBER. \n" +
                            "7. ");

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
            System.out.println("PLEASE ENTER TRIP #: ");
            String tripNum = myScanner.next();
            System.out.println("PLEASE ENTER DATE: ");
            String date = myScanner.next();
            System.out.println("PLEASE ENTER SCHEDULED START TIME: ");
            String time = myScanner.next();

            try{
                DatabaseConnection.deleteTripFromDB(tripNum, date, time, transitConnection);
            } catch(SQLException e) {
                e.printStackTrace();
            }


        }
        if(option == 3){
            boolean moreInputs = true;
            while(moreInputs) {
                System.out.println("PLEASE ENTER TRIP #: ");
                String tripNum = myScanner.next();
                System.out.println("PLEASE ENTER DATE: ");
                String date = myScanner.next();
                System.out.println("PLEASE ENTER SCHEDULED START TIME: ");
                String time = myScanner.next();
                System.out.println("PLEASE ENTER SCHEDULED ARRIVAL TIME: ");
                String arrivTime = myScanner.next();
                System.out.println("PLEASE ENTER DRIVER NAME: ");
                String name = myScanner.next() + " " + myScanner.next();
                System.out.println("PLEASE ENTER BUS ID: ");
                String busID = myScanner.next();

                try {
                    DatabaseConnection.addTripOffering(tripNum, date, time, arrivTime, name, busID, transitConnection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                System.out.println("WOULD YOU LIKE TO INPUT MORE TRIPS? Y/N");
                String response = myScanner.next();
                if(response.equals("Y") || response.equals("y"))
                    moreInputs = true;
                else
                    moreInputs = false;
            }
        }
        if(option == 4){
            System.out.println("PLEASE ENTER TRIP #: ");
            String tripNum = myScanner.next();
            System.out.println("PLEASE ENTER DATE: ");
            String date = myScanner.next();
            System.out.println("PLEASE ENTER SCHEDULED START TIME: ");
            String time = myScanner.next();
            System.out.println("PLEASE ENTER NEW DRIVER NAME: ");
            String name = myScanner.next() + " " + myScanner.next();

            try{
                DatabaseConnection.changeTripDriver(tripNum, date, time, name, transitConnection);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        if(option == 5){
            System.out.println("PLEASE ENTER TRIP #: ");
            String tripNum = myScanner.next();
            System.out.println("PLEASE ENTER DATE: ");
            String date = myScanner.next();
            System.out.println("PLEASE ENTER SCHEDULED START TIME: ");
            String time = myScanner.next();
            System.out.println("PLEASE ENTER NEW BUSID: ");
            String busID = myScanner.next();

            try{
                DatabaseConnection.changeTripBusID(tripNum, date, time, busID, transitConnection);
            } catch (SQLException e){
                e.printStackTrace();
            }

        }

        if(option == 6){
            System.out.println("PLEASE ENTER TRIP #: ");
            String tripNum = myScanner.next();

            try{
                DatabaseConnection.displayTripStops(tripNum, transitConnection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(option == 7){

        }
    }
}

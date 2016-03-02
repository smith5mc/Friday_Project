package part1;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * @author  Nguyen,Van Duc 
 *          Nguyen,Patrick Quang 
 *          Smith,Micah Copeland 
 *          Pena Trujillo,Julian
 * 
 * Date Created 2/25/16
 * 
 * @version 1.0(2/25/2016)
 * @version 1.1(3/1/2016)
 * 
 * Hosted on https://github.com/smith5mc/Semester-Project
 * 
 * The program is written for CIS 331 at JMU
 * It is the first iteration of the Bank Group Assignment 
 * 
 * NOTES: This prototype as of version 1.1 can only handle 
 * 50 transactions 
 */
public class Bank
{
    public static void main(String args[])
    {
        int arrayCount = 0;
        
        Scanner scan = new Scanner(System.in);
        String lastName =  "";
        String firstName = "";
        String middleInitial = "";

        System.out.print("Welcome to First Shenandoah Bank!"
                         + "\nEnter New Customer Name(Lastname,"
                         + " Firstname MI):");
        lastName = scan.next();
        firstName = scan.next();
        middleInitial = scan.next();

        mainMenu(arrayCount);
    }//end main


    /***************************************************************************
     * A method to deposit to the account
     * Can only be accessed from the main menu
     * 
     * @param  description : descriptions of the transactions
     * @param  amount : amount of the transaction
     * @param  date : date of the transaction
     * @param  arrayCount : current counter of the array
     * @return arrayCount : Pre-incrimented by 1
     */
    public static int deposit(String[] description, double[] amount, 
                               long[] date, int arrayCount)
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Deposit Description: ");
        description[arrayCount] = scan.nextLine();

        System.out.print("Enter Amount: ");
        amount[arrayCount] = scan.nextDouble();

         date[arrayCount] = getDate();
        return ++arrayCount;
    }//end deposit


    /***************************************************************************
     * A method to get the current account balance
     * Can only be accessed from the main menu
     * 
     * @param amount : amount of the transaction
     * @return at string with the current balance
     */
    public static String getCurrent(double[] amount)
    {
        int total = 0;
        for(double e: amount)
            total += e;
        return("Your Current Balance Is: $" + total + "\n");		
    }//end get current

    /***************************************************************************
     * A method to get the current date
     * 
     * Taken from JavaDocs on System
     * @return the date in long
     */
    public static long getDate()
    {
        //Date current = new Date();
        //return current;
        long date = System.currentTimeMillis();
        return date;
    }


    /***************************************************************************
     * A method to generate a report of transactions
     * Can only be accessed from the main menu
     * 
     * @param  description : descriptions of the transactions
     * @param  amount : amount of the transaction
     * @param  date : date of the transaction
     * @param  arrayCount : current counter of the array
     */
    public static void getReport(String[] description, double[] amount, 
                                long[] date, int arrayCount)
    {
        System.out.println("\nTransaction Report:\nDate:\t\t\t" +
                            "Amount:\tDescription:");
        System.out.println("____________________________________" +
                            "_________________");

        for (int i = 0; i < arrayCount; i++)
        {
                System.out.println(printDate(i, date) + "\t" + 
                                    amount[i] + "\t" + description[i]);			
        }
        System.out.println("\n_____________________________________" +
                            "________________");
        System.out.println(getCurrent(amount));
        System.out.println("\n______________________________________" +
                            "_______________");
    }//end getReport


    /***************************************************************************
     * A method to provide the "Main Menu" functionality
     * Gets and directs user choice to methods to handle 
     * their respective functionality
     * 
     * @param arrayCount an integer with the current array count
     */
    public static void mainMenu(int arrayCount)
    {
        boolean quit = false;
        int choice = -1;
        String[] description = new String[50];
        double[] amount = new double[50];
        long[] date = new long[50];

        do
        {
            System.out.print("\nPlease choose an option:" 
                             + "\n1: Make a Deposit"
                             + "\n2: Make a Withdrawal"
                             + "\n3: Tools Menu"
                             + "\n4: Quit Program"
                             + "\n____________________"
                             + "\nChoice: ");

            choice = validChoice(1,4);

            switch (choice)
            {
            case 1: arrayCount = deposit(description, amount, date, arrayCount); 
                break;
            case 2: arrayCount= withdraw(description, amount, date, arrayCount); 
                break;
            case 3: tools(description, amount, date, arrayCount); 
                break;
            case 4: quit(); 
                quit = true;
                break;
            default: System.out.println("Error in validChoice"); 
                break;
            }
        }while(!quit);
    }//end main Menu

    /***************************************************************************
     * A method to print out the date from a long data type
     * 
     * adapted from http://stackoverflow.com/questions/4772425/change-date-
     *              format-in-a-java-string
     * 
     * @param i counter the master counter iterating through an array
     * @param date - long data type from getDate()'
     * @return A string formated with the date
     */
    public static String printDate(int i, long[] date) 
    {
        Date instanceDate = new Date(date[i]);
        SimpleDateFormat current = new SimpleDateFormat("EEE, d MMM yyyy "
                                                        + "HH:mm:ss"); 
        String dateFormated = current.format(instanceDate);
        return dateFormated;
    }


    /***************************************************************************
     * Quits functionality, Dead ends program
     * NOTE: Unsecured for future versions
     */
    public static void quit()
    {
        System.out.println("Logging out... Thank you!");
    }


    /***************************************************************************
     * A method to provide the "Tools Menu" functionality
     * Can only be reached by the main menu
     * 
     * @param description : descriptions of the transactions
     * @param  amount : amount of the transaction
     * @param  date : date of the transaction
     * @param arrayCount : current counter of the array
     */
    public static void tools(String[] description, double[] amount, 
                            long[] date, int arrayCount)
    {
        int choice = -1;
        do
        {
            System.out.print("\nMake Tools Choice:" 
                             + "\n1: Current Balance"
                             + "\n2: Transaction Report"
                             + "\n3: <-- Back to Main Menu"
                             + "\n____________________"
                             + "\nChoice: ");

            choice = validChoice(1,3);

            switch (choice)
            {
            case 1: System.out.print(getCurrent(amount)); 
                break;
            case 2: getReport(description, amount, date, arrayCount); 
                break;
            case 3: mainMenu(arrayCount); 
                break;
            default: System.out.println("Error in validChoice"); 
                break;
            }
        } while (choice !=3);
    }//end tools


    /***************************************************************************
     * A method to validate a choice that was made
     * in the menu
     * 
     * @param low : the lowest valid number
     * @param high : the highest valid number
     * @return the choice between low and high
     */
    public static int validChoice(int low, int high)
    {
        Scanner scan = new Scanner(System.in);
        boolean validChoice = false;
        int choice = 0;

        do
        {
            if (scan.hasNextInt())
            {
                    choice = scan.nextInt();
                    if ((choice < low) || (choice > high))
                    {
                        validChoice = false;
                        System.out.println("Please enter a number between " 
                                           + low + " and " + high + "."); 
                    }
                    else
                        validChoice = true;
            } 
            else
            {
                System.out.println("Please enter an number (" + low
                                   + "-" + high +").");
                scan.next();
                validChoice = false;
            }
        } while (!validChoice);

        return choice;
    }//end validChoice


    /***************************************************************************
     * A method to provide the withdraw functionality
     * Can only be accessed from the main menu
     * 
     * @param description : descriptions of the transactions
     * @param amount : amount of the transaction
     * @param date : date of the transaction
     * @param arrayCount : current counter of the array
     * @return arrayCount : Preincrimented by 1
     */
    public static int withdraw(String[] description, double[] amount, 
                                long[] date, int arrayCount)
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Withdrawal Description: ");
        description[arrayCount] = scan.nextLine();

        System.out.print("Enter Amount: ");
        amount[arrayCount] = scan.nextDouble();

        date[arrayCount] = getDate();
        return ++arrayCount;
    }//end withdraw
}//end Bank Class



	

package part1;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * @author Micah Smith, 
 * @version 1.0.0: Date 2/25/16
 * The program is written for CIS 331 @JMU
 * It is the first iteration of the Bank Group Assignemnt 
 */
public class Bank
{
	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);
		String last =  "";
		String first = "";
		String mi = "";
		
		System.out.print("Welcome to First Shenandoah Bank!"
				 + "\nEnter New Customer Name(Lastname,"
				 + " Firstname MI):");
		last = scan.next();
		first = scan.next();
		mi = scan.next();
	
		mainMenu();
	}//end main
	
	
	/**
	 * A method to deposit to the account
	 * Can only be accessed from the main menu
	 * 
	 * @param String[] description : descriptions of the transactions
	 * @param double[] amount : amount of the transaction
	 * @param Long[] date : date of the transaction
	 * @param int arrayCount : current counter of the array
	 */
	public static int deposit(String[] description, double[] amount, long[] date, int arrayCount)
	{
		Scanner scan = new Scanner(System.in);
	
		System.out.print("Enter Deposit Description: ");
		description[arrayCount] = scan.nextLine();
	
		System.out.print("Enter Amount: ");
		amount[arrayCount] = scan.nextDouble();
	
		 date[arrayCount] = getDate();
		return ++arrayCount;
	}//end deposit

	
	/***************************************************************************************************
	 * A method to get the current account balance
	 * Can only be accessed from the main menu
	 * 
	 * @param double[] amount : amount of the transaction
	 */
	public static String getCurrent(double[] amount)
	{
		int total = 0;
		for(double e: amount)
			total += e;
		return("Your Current Balance Is: $" + total + "\n");		
	}//end get current
	
	/*****************************************************************************************************
	 * A method to get the current date
	 * 
	 */
	public static long getDate()
	{
		//Date current = new Date();
		//return current;
		long date = System.currentTimeMillis();
		return date;
	}

	
	/*****************************************************************************************************
	 * A method to generate a report of transactions
	 * Can only be accessed from the main menu
	 * 
	 * @param String[] description : descriptions of the transactions
	 * @param double[] amount : amount of the transaction
	 * @param long[] date : date of the transaction
	 * @param int arrayCount : current counter of the array
	 */
	public static void getReport(String[] description, double[] amount, long[] date, int arrayCount)
	{
		System.out.println("\nTransaction Report:\nDate:\t\t\tAmount:\tDescription:");
		System.out.println("_____________________________________________________");
		
		for (int i = 0; i <= arrayCount; i++)
		{
			System.out.println(printDate(i, date) + "\t" + amount[i] + "\t" + description[i]);			
		}
		System.out.println("\n_____________________________________________________");
		System.out.println(getCurrent(amount));
		System.out.println("\n_____________________________________________________");
	}//end getReport

	
	/******************************************************************************************************
	 * A method to provide the "Main Menu" functionality
	 * Gets and directs user choice to methods to handle 
	 * their respective functionality
	 * 
	 * @param : N/A
	 * @version : 1.0.0
	 */
	public static void mainMenu()
	{
		boolean quit = false;
		int choice = -1;
		int arrayCount = 0;
		String[] description = new String[15];
		double[] amount = new double[15];
		long[] date = new long[15];

		while(!quit)
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
			case 1: arrayCount = deposit(description, amount, date, arrayCount); break;
			case 2: arrayCount = withdraw(description, amount, date, arrayCount); break;
			case 3: tools(description, amount, date, arrayCount); break;
			case 4: quit(); 
					quit = true;
					break;
			default: System.out.println("Error in validChoice"); break;
			}
		}
	}//end main Menu

	
	public static String printDate(int i, long[] date) 
	{
		Date instanceDate = new Date(date[i]);
		SimpleDateFormat current = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss"); 
		String dateFormated = current.format(instanceDate);
		return dateFormated;
	}
	
	
	/******************************************************************************************************
	 * Quits functionality, Dead ends program
	 * NOTE: Unsecured for future versions
	 * 
	 * @param : n/a
	 * @version 1.0.0 
	 */
	public static void quit()
	{
		System.out.println("Logging out... Thank you!");
	}

	
	/**********************************************************************************************
	 * A method to provide the "Tools Menu" functionality
	 * Can only be acessed by the main menu
	 * 
	 * @param String[] description : descriptions of the transactions
	 * @param double[] amount : amount of the transaction
	 * @param long[] date : date of the transaction
	 * @param int arrayCount : current counter of the array
	 */
	public static void tools(String[] description, double[] amount, long[] date, int arrayCount)
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
			case 1: System.out.print(getCurrent(amount)); break;
			case 2: getReport(description, amount, date, arrayCount); break;
			case 3: mainMenu(); break;
			default: System.out.println("Error in validChoice"); break;
			}
		} while (choice !=3);
	}//end tools


	/**********************************************************************************
	 * A method to validate a choice that was made
	 * in the menu
	 * 
	 * @param int low : the lowest valid number
	 * @param int high : the highest valid number
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

	
	/************************************************************************************************
	 * A method to provide the withdraw functionality
	 * Can only be accessed from the main menu
	 * 
	 * @param String[] description : descriptions of the transactions
	 * @param double[] amount : amount of the transaction
	 * @param String[] date : date of the transaction
	 * @param int arrayCount : current counter of the array
	 */
	public static int withdraw(String[] description, double[] amount, long[] date, int arrayCount)
	{
		Scanner scan = new Scanner(System.in);

		System.out.print("Enter Withdrawal Description: ");
		description[arrayCount] = scan.nextLine();
	
		System.out.print("Enter Amount: ");
		amount[arrayCount] = scan.nextDouble();

		//date needs to be done date[arrayCount] = ______
		return ++arrayCount;
	}//end withdraw
}//end Bank Class



	
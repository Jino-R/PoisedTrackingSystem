

/**
 * @author Jino Rigney
 * 
 * This class executes the code
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;



public class Main {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		// Connect to the poisepms database, via the jdbc:mysql: channel on localhost (this PC)
		 // Use username "otheruser", password "swordfish".
		
			// Create a direct line to the database for running our queries
			
			try {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
						 "otheruser",
						 "swordfish"
				);
				Statement statement = connection.createStatement();
			
				int rowsAffected;
				ResultSet results ;
				System.out.println("Do you want enter a new project(p) if not, press any key to update project details");
String option = input.nextLine();

if(option.equals("p")) {
System.out.println("Enter architect details ");
				

				// Ask questions for architect
				System.out.println("Enter name");
				String architectName = input.nextLine();

				System.out.println("Enter email");
				String architectEmail = input.nextLine();

				System.out.println("Enter Physical Address");
				String architectPhysicalAddress = input.nextLine();

				System.out.println("Enter telephone number");
				String architectTelephoneNumber = input.nextLine();

				// create architect object and set values to questions asked
				Person architect = new Person(architectName, architectEmail, architectPhysicalAddress, architectTelephoneNumber);
				
				//add architect details to architect table
				rowsAffected = statement.executeUpdate(
						"INSERT INTO architect VALUES('"+architectName+"','"+architectEmail+"','"+architectPhysicalAddress+"','"+architectTelephoneNumber+"')"
						);

				// print architect details
				System.out.println(architect);
				System.out.println(" ");

				// ask engineer questions
				System.out.println("Enter Engineer's details");
				System.out.println("Enter name");
				String engineerName = input.nextLine();

				System.out.println("Enter email");
				String engineerEmail = input.nextLine();

				System.out.println("Enter physical address");
				String engineerPhysicalAddress = input.nextLine();

				System.out.println("Enter telephone number");
				String engineerTelephoneNumber = input.nextLine();

				// create contractor object and set values to questions asked
				Person engineer = new Person(engineerName, engineerEmail, engineerPhysicalAddress,
						engineerTelephoneNumber);
				
				//add engineer details to engineer table
				rowsAffected = statement.executeUpdate("INSERT INTO engineer Values('"+engineerName+"','"+ engineerEmail+"','"+engineerPhysicalAddress+"','"+engineerTelephoneNumber+"')");

				// print contractor details
				System.out.println(engineer);
				System.out.println(" ");
				
				// ask proj_manager questions
				System.out.println("Enter Project Manager's details");
				System.out.println("Enter name");
				String projManagerName = input.nextLine();

				System.out.println("Enter email");
				String projManagerEmail = input.nextLine();

				System.out.println("Enter physical address");
				String projManagerPhysicalAddress = input.nextLine();

				System.out.println("Enter telephone number");
				String projManagerTelephoneNumber = input.nextLine();
				
				//create project manager object
				Person projManager = new Person(projManagerName, projManagerEmail, projManagerPhysicalAddress, projManagerTelephoneNumber);
				System.out.println(projManager);
				System.out.println(" ");
				
				//add project manager details to project manager table
				rowsAffected = statement.executeUpdate("INSERT INTO proj_manager Values('"+projManagerName+"','"+ projManagerEmail+"','"+projManagerPhysicalAddress+"','"+projManagerTelephoneNumber+"')");
							
					
				// ask customer details
				System.out.println("Enter Customers's details");
				System.out.println("Enter name");
				String customerName = input.nextLine();

				System.out.println("Enter email");
				String customerEmail = input.nextLine();

				System.out.println("Enter physical address");
				String customerPhysicalAddress = input.nextLine();

				System.out.println("Enter telephone number");
				String customerTelephoneNumber = input.nextLine();

				// create customer object and set values to questions asked
				Person customer = new Person(customerName, customerEmail, customerPhysicalAddress, customerTelephoneNumber);
				
				//add customer details to customer table
				rowsAffected = statement.executeUpdate("INSERT INTO customer Values('"+customerName+"','"+ customerEmail+"','"+customerPhysicalAddress+"','"+customerTelephoneNumber+"')");

				// print customer details
				System.out.println(customer);
				System.out.println(" ");

				// Ask project details questions
				System.out.println("Enter project details");
				
				System.out.println("Enter project address");
				String projectAddress = input.nextLine();

				System.out.println("Enter building type");
				String buildingType = input.nextLine();
				
				System.out.println("Enter project name");
				String projectName = input.nextLine();
				
				//if the user has no project name 
				if(projectName.isBlank()) {
					projectName += buildingType + customerName;
				}

				System.out.println("Enter deadline (yyyy-mm-dd)");
				String deadline = input.nextLine();
				
				//if date is wrong format ask user to enter deadline again
				deadline = checkDateFormat(input, deadline);
				
				System.out.println("Enter total fee in (R_,_) ");
				enterFloat(input);
				float totalFee = input.nextFloat();

				System.out.println("Enter amount paid to date in (R_,_) ");
				enterFloat(input);
				float amountPaid = input.nextFloat();
				
				input.nextLine();

				System.out.println("Enter erf number");
				String erfNumber = input.nextLine();

				System.out.println("Enter project number");
				enterInt(input);
				int projectNumber = input.nextInt();

				String completed = "no";
			
				// create project object
				Project project = new Project(projectNumber, projectName, buildingType, projectAddress, erfNumber, totalFee,
						amountPaid, deadline, completed);
				//add project details to project table
				rowsAffected = statement.executeUpdate("INSERT INTO project Values('"+projectNumber+"','"+ projectName+"','"+buildingType+"','"+projectAddress+"','"+erfNumber+"','"+totalFee+"','"+amountPaid+"','"+deadline+"','"+completed+"')");

				// print project details
				System.out.println(project);
				System.out.println(" ");
				
				System.out.println("Customer invoice");
				
				System.out.println(customerName + " " + customerEmail + " " + customerTelephoneNumber);
				System.out.println(" Amount Owed");
				System.out.println(totalFee - amountPaid);
				System.out.println(" ");
				
				//close connections
				statement.close();
				connection.close();
				
}

if(option.equals("v")) {
	
	System.out.println("Incomplete projects ");
	System.out.println(" ");

	// executeQuery: runs a SELECT statement and returns the results for projects that are not completed
	 results = statement.executeQuery("SELECT Project_Number, Project_Name, Building_Type, Project_Address, ERF_NUMBER,Total_Fee,Amount_Paid,Deadline, Completed FROM project WHERE Completed = 'no'");

	while (results.next()) {
		System.out.println( results.getInt("Project_Number") + ", "
		+ results.getString("Project_Name") + ", "
		+ results.getString("Building_Type") + ", "
		+ results.getString("Project_Address") + ", "
		+ results.getString("ERF_NUMBER") + ", "
		+ results.getFloat("Total_Fee") + ", "
		+ results.getFloat("Amount_Paid") + ", "
		+ results.getString("Deadline") + ", "
		+ results.getString("Completed") 
		);
	}
	System.out.println(" ");

	//get today's date
	Date today = new Date();
	//create date object 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		//convert today's date to simple date format 
		String todayFormat = dateFormat.format(today);

	System.out.println("Overdue Projects, if there are none it will appear blank");
	System.out.println(" ");

	 results = statement.executeQuery("SELECT Project_Number, Project_Name, Building_Type, Project_Address, ERF_NUMBER,Total_Fee,Amount_Paid,Deadline, Completed FROM project WHERE deadline < '"+todayFormat+"'");

					while (results.next()) {
						System.out.println( results.getInt("Project_Number") + ", "
						 + results.getString("Project_Name") + ", "
						 + results.getString("Building_Type") + ", "
						 + results.getString("Project_Address") + ", "
						 + results.getString("ERF_NUMBER") + ", "
						 + results.getFloat("Total_Fee") + ", "
						 + results.getFloat("Amount_Paid") + ", "
						 + results.getString("Deadline") + ", "
						 + results.getString("Completed") 
						 
						 );	
					}
					// Close up our connections
					results.close();
					 statement.close();
					 connection.close();

              }

			
			} 
			catch (SQLException e) {
				System.out.println("Unable to connect to database");
			}
			

			// ask user if they want to update details
			System.out.println("Do you want to update any details? (yes/no) : ");
			String question = input.nextLine().toLowerCase();

			// if question != yes or no ask user to enter again

			question = checkYesOrNO(input, question);
            
			// Create a direct line to the database for running our queries
									
			Connection connection;
							
			try {
				 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
				 "otheruser",
				 "swordfish"
				 );
				 Statement statement = connection.createStatement();
				 int rowsAffected;
				 if (question.equals("yes")) {

// ask which details to update

System.out.println(" Update architect details(a), Update engineer details(e),Update project manager details(m),Update customer details(c) update project(p), mark as complete(y)");
String change = input.nextLine().toLowerCase();
            
//make sure user selects option from the menu
change = checkChangeInput(input, change);

// change details based on user input and print updated details
if (change.equals("p")) {

	// ask user to enter new project details information
	System.out.println("Enter project number you want to update");
	enterInt(input);
	int changeProjectNumber = input.nextInt();

	System.out.println("Enter project details");
	System.out.println("Enter name");
	String newProjectName = input.nextLine();

	System.out.println("Enter project address");
	String newProjectAddress = input.nextLine();

	System.out.println("Enter building type");
	String newBuildingType = input.nextLine();

	System.out.println("Enter deadline(yy-mm-dd)");
	String newDeadline = input.nextLine();
	newDeadline = checkDateFormat(input, newDeadline);

	System.out.println("Enter total fee in (R_,_) ");
	enterFloat(input);
	float newTotalFee = input.nextFloat();
	

	System.out.println("Enter amount paid to date in (R_,_) ");
	enterFloat(input);
	float newAmountPaid = input.nextFloat();
	


	System.out.println("Enter erf number");
	String newErfNumber = input.nextLine();



	rowsAffected = statement.executeUpdate("Update project Set Project_Name = '"+newProjectName+"' WHERE Project_Number = '"+changeProjectNumber+"'" );
	rowsAffected = statement.executeUpdate("Update project Set Project_Address = '"+newProjectAddress+"' WHERE Project_Number = '"+changeProjectNumber+"'" );
	rowsAffected = statement.executeUpdate("Update project Set Project_Building_Type = '"+newBuildingType+"' WHERE Project_Number = '"+changeProjectNumber+"'" );
	rowsAffected = statement.executeUpdate("Update project Set Deadline = '"+newDeadline+"' WHERE Project_Number = '"+changeProjectNumber+"'" );
	rowsAffected = statement.executeUpdate("Update project Set Total_Fee = '"+newTotalFee+"' WHERE Project_Number = '"+changeProjectNumber+"'" );
	rowsAffected = statement.executeUpdate("Update project Set Amount_Paid = '"+newAmountPaid+"' WHERE Project_Number = '"+changeProjectNumber+"'" );
	rowsAffected = statement.executeUpdate("Update project Set ERF_NUMBER = '"+newErfNumber+"' WHERE Project_Number = '"+changeProjectNumber+"'" );
     
	statement.close();
	connection.close(); 

	System.out.println("Project Updated");
}

if (change.equals("a")) {


	//ask user to enter new Architect details
	System.out.println("Enter the architect's name that you want to change");
	String name = input.nextLine();
	System.out.println("Enter new Architect details");
	System.out.println(" ");

	System.out.println("Enter name");
	String newArchitectName = input.nextLine();

	System.out.println("Enter email");
	String newArchitectEmail = input.nextLine();

	System.out.println("Enter Physical Address");
	String newArchitectPhysicalAddress = input.nextLine();

	System.out.println("Enter telephone number");
	String newArchitectTelephoneNumber = input.nextLine();

	rowsAffected = statement.executeUpdate("Update architect Set Name = '"+newArchitectName+"' WHERE Name = '"+name+"'" );
	rowsAffected = statement.executeUpdate("Update architect Set Email = '"+newArchitectEmail+"' WHERE Name = '"+name+"'" );
	rowsAffected = statement.executeUpdate("Update architect Set Physical_Address = '"+newArchitectPhysicalAddress+"' WHERE Name = '"+name+"'" );
	rowsAffected = statement.executeUpdate("Update architect Set Telephone_Number = '"+newArchitectTelephoneNumber+"' WHERE Name = '"+name+"'" );
    
	statement.close();
	connection.close();
	System.out.println("Architect details updated");
}

if (change.equals("e")) {
	//ask user to enter engineers's new information
	System.out.println("Enter name of engineer you want to update");
	String name = input.nextLine();

	System.out.println("Enter Engineer's details");
	System.out.println("Enter name");
	String newEngineerName = input.nextLine();

	System.out.println("Enter email");
	String newEngineerEmail = input.nextLine();

	System.out.println("Enter physical address");
	String newEngineerPhysicalAddress = input.nextLine();

	System.out.println("Enter telephone number");
	String newEngineerTelephoneNumber = input.nextLine();

	rowsAffected = statement.executeUpdate("Update architect Set Name = '"+newEngineerName+"' WHERE Name = '"+name+"'" );
	rowsAffected = statement.executeUpdate("Update architect Set Email = '"+newEngineerEmail+"' WHERE Name = '"+name+"'" );
	rowsAffected = statement.executeUpdate("Update architect Set Physical_Address = '"+newEngineerPhysicalAddress+"' WHERE Name = '"+name+"'" );
	rowsAffected = statement.executeUpdate("Update architect Set Telephone_Number = '"+newEngineerTelephoneNumber+"' WHERE Name = '"+name+"'" );

	statement.close();
	connection.close();

	System.out.println("Engineer's details updated");
}
if (change.equals("c")) {
	System.out.println("Enter the customer's name that you want to change");
	String name = input.nextLine();

	//ask user to enter new customer details
	System.out.println("Enter Customers's details");
	System.out.println("Enter name");
	String newCustomerName = input.nextLine();

	System.out.println("Enter email");
	String newCustomerEmail = input.nextLine();

	System.out.println("Enter physical address");
	String newCustomerPhysicalAddress = input.nextLine();

	System.out.println("Enter telephone number");
	String newCustomerTelephoneNumber = input.nextLine();

	rowsAffected = statement.executeUpdate("Update customer Set Name = '"+newCustomerName+"' WHERE Name = '"+name+"'" );
	rowsAffected = statement.executeUpdate("Update customer Set Email = '"+newCustomerEmail+"' WHERE Name = '"+name+"'" );
	rowsAffected = statement.executeUpdate("Update customer Set Physical_Address = '"+newCustomerPhysicalAddress+"' WHERE Name = '"+name+"'" );
	rowsAffected = statement.executeUpdate("Update customer Set Telephone_Number = '"+newCustomerTelephoneNumber+"' WHERE Name = '"+name+"'" );

	statement.close();
	connection.close();

	System.out.println("Customer details updated");
}

if (change.equals("y")) {

	System.out.println("Enter the project Number you want to mark as completed");
	enterInt(input);
	int completeProjectNumber = input.nextInt();	

	rowsAffected = statement.executeUpdate("Update  Set Completed = 'yes' WHERE Project_Number = '"+completeProjectNumber+"'" );


	System.out.println("project completed");
	// Close up our connections
	
	statement.close();
	connection.close();

}

}

// if user does not want to update details then tell user details have been
// stored

else if (question.equals("no")) {
	System.out.println("Details stored successfuly");


}
								
} 
catch (SQLException e) {
	System.out.println("Unable to connect to database");
}

}



	/**
	 * @param input
	 */
	private static void enterFloat(Scanner input) {
		while (!input.hasNextFloat()) {
			System.out.println("Enter a float");
			input.next();

		}
	}



	/**
	 * This method checks if the date the user entered is in the correct format
	 * @param input
	 * @param deadline
	 * @return
	 */
	private static String checkDateFormat(Scanner input, String deadline) {
		while(!deadline.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
			System.out.println("Enter correct date format(yyyy-mm-dd)");
			deadline = input.nextLine();
		}
		return deadline;
	}
			



	/**
	 * This method makes sure the user enters an option from the menu
	 * 
	 * @param input user selects option from menu
	 * @param change
	 * @return
	 */
	private static String checkChangeInput(Scanner input, String change) {
		// if change does not equal to d, t, or u
		while (!change.equals("a") && !change.equals("e") && !change.equals("m") && !change.equals("c") &&!change.equals("p") &&!change.equals("y")) {
			System.out.println("Enter 'a' or 'e' or 'm' or 'c' or 'p' or 'y'");
			change = input.nextLine().toLowerCase();

		}
		return change;
	}


	

	

	/**
	 * This method makes sure the user enters either yes or no
	 * 
	 * @param input user input
	 * @param question
	 * @return
	 */
	private static String checkYesOrNO(Scanner input, String question) {
		while (!question.equals("yes") && !question.equals("no")) {
			System.out.println("Enter yes or no");
			question = input.nextLine();

		}
		return question;
	}

	
	// check if user input is an integer
	/**
	 * This method makes sure the user enters an integer 
	 * 
	 * @param input user selects option from the menu
	 */
	public static void enterInt(Scanner input) {
		while (!input.hasNextInt()) {
			System.out.println("Enter an integer");
			input.next();

		}
	}
		
		 /**
		 * Method printing all values in all rows.
		 * Takes a statement to try to avoid spreading DB access too far.
		 *
		 * @param a statement on an existing connection
		 * @throws SQLException
		 */
		public static void printAllFromTable(Statement statement) throws
		SQLException{
		ResultSet results = statement.executeQuery("SELECT Project_Number, Project_Name, Building_Type, Project_Address, ERF_NUMBER,Total_Fee,Amount_Paid,Deadline, Completed FROM project");
		 while (results.next()) {
		 System.out.println(
		 results.getInt("Project_Number") + ", "
		 + results.getString("Project_Name") + ", "
		 + results.getString("Building_Type") + ", "
		 + results.getString("Project_Address") + ", "
		 + results.getString("ERF_NUMBER") + ", "
		 + results.getFloat("Toatl_Fee") + ", "
		 + results.getFloat("Amount_Paid") + ", "
		 + results.getString("Deadline") + ", "
		 + results.getString("Completed")
		 );
		 }

	}

}

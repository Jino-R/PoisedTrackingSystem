
/**
 * 
 * @author Jino Rigney
 * 
 * This class creates the person details
 *
 */
public class Person {

	// Attributes	
	private String name;
	private String email;
	private String physicalAddress;
	private String telephoneNumber;

	// constructor
	public  Person(String name, String email,String telephoneNumber, String physicalAddress ) {
	     
		  this.name = name;
	      this.email = email;
	      this.telephoneNumber = telephoneNumber;
	      this.physicalAddress = physicalAddress;
	}

	// methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	// this is where we get our out out for each value
	public String toString() {
		String output = "Name:" + name;
		output += "\nEmail: " + email;
		output += "\nPhysical Address:" + physicalAddress;
		output += "\nTelephone Number:" + telephoneNumber;

		return output;
	}
}
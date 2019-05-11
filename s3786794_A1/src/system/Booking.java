package system;
import utilities.DateTime;

/*
 * Class:           Booking
 * Description:     The class represents a single booking
 * 					that is parsed by the user if possible.
 * Author:			Peter Bui - s3786794
 */

public class Booking {
	private String id;
	private final double bookingFee = 1.5;
	private DateTime pickUpDateTime;
	private String firstName;
	private String lastName;
	private int numPassengers;
	private double kilometersTravelled;
	private double tripFee;
	private Car car;
	
	public Booking(String firstName, String lastName, 
				   DateTime required, int numPassengers, Car car) {
		this.pickUpDateTime = required;
		
		/* takes the substring up to the 3rd index from 
		 * both first and last name for id purposes*/
		this.firstName = firstName.substring(0).toUpperCase();
		String firstNameUpper = firstName.substring(0, 3).toUpperCase();
		
		this.lastName = lastName.substring(0).toUpperCase();
		String lastNameUpper = lastName.substring(0, 3).toUpperCase();

		this.car = car;
		
		this.numPassengers = numPassengers;

		id = car.getRegNo() + "_" + firstNameUpper + lastNameUpper + 
			 "_" + pickUpDateTime.getEightDigitDate();
	}
	
	/*
	 * ALGORITHM
	 * BEGIN
	 *     GET total kilometers traveled from user
	 *         CALCULATE total fee from the booking trip
	 *             SHOW user the total amount calculated
	 * END
	 */
	public double calculateTotal(double kilometers) {
		kilometersTravelled = kilometers;
		tripFee = (kilometersTravelled * (0.3 * bookingFee)) + bookingFee;
		return this.tripFee;
	}

	public String getDetails() {
		return "id:           " + id + "\n" + 
			   "Booking Fee:  " + "$" + bookingFee + "0" + "\n" +
			   "Pick up date: " + pickUpDateTime.getFormattedDate() + "\n" + 
			   "Name:         " + firstName + " " + lastName + "\n" + 
			   "Passengers:   " + numPassengers + "\n" +
			   "Travelled:    " + kilometersTravelled + "\n" + 
			   "Trip Fee:     " + tripFee + "\n" +
			   "Car Id:       " + car.getRegNo();
	}

	public String toString() {
		return id + ":" + bookingFee + ":" + pickUpDateTime.getEightDigitDate() + 
			   ":" + firstName + " " + lastName + ":" + numPassengers + 
			   ":" + kilometersTravelled + ":" + tripFee + ":" + car.getRegNo();
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public DateTime getPickUpDate() {
		return pickUpDateTime;
	}
	
	public void setPickUpDate(DateTime pickUpDateTime) {
		this.pickUpDateTime = pickUpDateTime;
	}
	
	public double getTripFee() {
		return tripFee;
	}
	
	public void setTripFee(double tripFee) {
		this.tripFee = tripFee;
	}
}

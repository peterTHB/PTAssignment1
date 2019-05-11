package system;

import utilities.DateTime;
import java.util.Scanner;

/*
 * Class:           Car
 * Description:     The class represents a single 
 * 					car that is created by the user
 * 					if possible, and stores bookings 
 * 					of both the present and the past.
 * Author:			Peter Bui - s3786794
 */

public class Car {
	private String regNo;
	private String make;
	private String model;
	private String driverName;
	private int passengerCapacity;
	private boolean available = true;
	private Booking[] currentBookings = new Booking[5];
	private Booking[] pastBookings = new Booking[15];
	private int bookingArray = 0;
	private int oldBookingArray = 0;
	
	Scanner input = new Scanner(System.in);
	
	// Construct car class for setting regulations on user input
	public Car(String regNo, String make, String model, String driverName, int passengerCapacity) {
		this.setRegNo(regNo.toUpperCase());
		
		/* converts only the 0 index of both the make and 
		 * model strings to upper case */
		String make1 = make.substring(0, 1).toUpperCase();
		String make2 = make.substring(1, make.length()).toLowerCase();
		this.make = make1 + make2;

		String model1 = model.substring(0, 1).toUpperCase();
		String model2 = model.substring(1, model.length()).toLowerCase();
		this.model = model1 + model2;
		
		this.driverName = driverName;

		this.passengerCapacity = passengerCapacity;
	}

	// Check availability of car if its able to be booked or not
	public boolean book(String firstName, String lastName, 
						DateTime required, int numPassengers) {
		Booking carBookings = new Booking(firstName, lastName, 
										  required, numPassengers, this);
		if (bookingArray == currentBookings.length) {
			return available = false;
		} else {
			currentBookings[bookingArray] = carBookings;
			bookingArray += 1;
			return available = true;
		}
	}
	
	/*
	 * ALGORITHM
	 * BEGIN
	 *     PARSE first and last name into memory
	 *         IF both first and last name is found
	 *             CONFIRM booking is found
	 *             CALCULATE the total amount of money
	 *             SHOW to user the total money
	 *             STORE present booking into past bookings
	 *             SWITCH current slot in memory with dummy object
	 *      IF booking cannot be found
	 *          SHOW user booking was not found
	 *      ELSE
	 *      	SHOW booking was completed
	 * END
	 */
	public void bookingComplete(String firstName, String lastName) {
		Booking temp = null;
		DateTime dateDummy = new DateTime();
		for (int i = 0; i < bookingArray; i++) {
			if (currentBookings[i].getFirstName().equalsIgnoreCase(firstName) && 
				currentBookings[i].getLastName().equalsIgnoreCase(lastName)) {
				temp = currentBookings[i];
				
				System.out.print(String.format("%-32s", "Enter Kilometers:"));
				double inputKilometers = input.nextInt();
				double totalFee = currentBookings[i].calculateTotal(inputKilometers); 
				
				System.out.println("\n" + "Thank you for riding with MiRide.");
				System.out.println("$" + (float)totalFee + 
								   " has been deducted from your account." + "\n");
				
				pastBookings[oldBookingArray] = currentBookings[i];
				currentBookings[i] = new Booking("noFirstName", "noLastName", 
												 dateDummy, 1, this);
			}
		}
		if (temp == null) {
			System.out.println("Booking not found in system");
		} else {
			bookingArray--;
			oldBookingArray++;
		}
	}
	
	/*
	 * ALGORITHM
	 * BEGIN
	 *     ITERATE loop 
	 *         IF the parsed first and last name exist within the system
	 *             STORE value temporarily
	 *             CALCULATE trip fee to user
	 *             DISPLAY trip fee to user
	 *             STORE finished booking into past bookings
	 *             CREATE dummy car object into finished booking slot
	 * END
	 */
	public void seedDataBooking(String firstName, String lastName, 
								double kilometersInput) {
		DateTime dateDummy = new DateTime();
		Booking temp = null;
		for (int i = 0; i < bookingArray; i++) {
			if (currentBookings[i].getFirstName().equalsIgnoreCase(firstName) && 
				currentBookings[i].getLastName().equalsIgnoreCase(lastName)) {
				temp = currentBookings[i];
				
				double totalFee = currentBookings[i].calculateTotal(kilometersInput); 
				
				temp.setTripFee(totalFee);
				
				pastBookings[i] = currentBookings[i];
				currentBookings[i] = new Booking("noFirstName", "noLastName", 
													dateDummy, 1, this);
			}
		}
	}

	public String getDetails() {
		return "RegNo:        " + getRegNo() + "\n" + 
				"Make & Model: " + make + " " + model + "\n" + 
				"Driver Name:  " + driverName + "\n" + 
				"Capacity:     " + passengerCapacity + "\n" + 
				"Available:    " + (available ? "YES" : "NO");
	}

	public String toString() {
		return getRegNo() + ":" + 
				make + ":" + 
				model + ":" + 
				driverName + ":" + 
				passengerCapacity + ":" + 
				available;
	}
	
	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	public int getPassengerCapacity() {
		return passengerCapacity;
	}
	
	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}
	
	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}

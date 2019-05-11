package main;

import system.Booking;
import system.Car;
import utilities.DateTime;

/*
 * Class:           MiRidesSystem
 * Description:     The class represents the collection
 * 					of data that is parsed by the user 
 * 					to be utilized later on.
 * Author:			Peter Bui - s3786794
 */

public class MiRidesSystem {
	private Car[] carCollection = new Car[15];
	private Booking[] bookingCollection = new Booking[100];
	private String regNo;
	private String make;
	private String model;
	private String driverName;
	private int passengerCapacity;
	private DateTime currentDate = new DateTime(-1);
	private int arrayCar = 0;
	private int arrayBooking = 0;

	/*
	 * ALGORITHM
	 * BEGIN
	 *     FOR int variable less than car collection memory
	 *         STORE dummy car object 
	 * END
	 */
	public void carCollectionPrinter() {
		/* ensures there isn't a null pointer error within
		 * the car collection, so dummy objects are created */
		for (int j = 0; j < carCollection.length; j++) {
			carCollection[j] = new Car("noRegNo", "noMake", 
									   "noModel", "noDriverName", 1);
		}
	}

	public void bookingCollectionPrinter() {
		/* ensures there isn't a null pointer error within
		 * the booking collection, so dummy objects are created */
		Car car = new Car("noRegNo", "noMake", "noModel", "noDriverName", 1);
		for (int l = 0; l < getBookingCollection().length; l++) {
			getBookingCollection()[l] = 
				new Booking("noFirstName", "noLastName", currentDate, 1, car);
		}
	}
	
	/* method is responsible for checking if there is
	 * a regNo in the carCollection */
	public boolean regNoCheck(String regNo) {
		Car temp = null;
		for (int h = 0; h < carCollection.length; h++) {
			if (carCollection[h].getRegNo().equals(regNo.toUpperCase())) {
				temp = carCollection[h];
			}
		}
		if (temp == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/* Method for seeding data into the system */
	public void seedDataMethod() {
		Car carNotBook1 = new Car("QUE747", "Ford", "Falcon", "Tom Hank", 5);
		carCollection[getArrayCar()] = carNotBook1;
		setArrayCar(getArrayCar() + 1);
		
		Car carNotBook2 = new Car("MAC205", "BMW", "Coupe", "Sam Hill", 4);
		carCollection[getArrayCar()] = carNotBook2;
		setArrayCar(getArrayCar() + 1);

		Car carNotComplete1 = new Car("MLB005", "Nissan", "Sedan", "John Page", 4);
		carCollection[getArrayCar()] = carNotComplete1;
		DateTime firstDate = new DateTime(3);
		carCollection[getArrayCar()].book("Marsh", "Wake", firstDate, 2);
		getBookingCollection()[getArrayBooking()] = new Booking("Marsh", "Wake", firstDate, 
																2, carNotComplete1);
		setArrayCar(getArrayCar() + 1);
		setArrayBooking(getArrayBooking() + 1);
		
		Car carNotComplete2 = new Car("MAC205", "Holden", "Commodore", "Billy Rogers", 4);
		carCollection[getArrayCar()] = carNotComplete2;
		DateTime secondDate = new DateTime(4);
		carCollection[getArrayCar()].book("Steven", "Stone", secondDate, 3);
		getBookingCollection()[getArrayBooking()] = new Booking("Steven", "Stone", secondDate, 
																3, carNotComplete2);
		setArrayCar(getArrayCar() + 1);
		setArrayBooking(getArrayBooking() + 1);

		Car carBookComplete1 = new Car("YUH358", "Toyota", "Tarago", "Tommy Oliver", 7);
		carCollection[getArrayCar()] = carBookComplete1;
		DateTime thirdDate = new DateTime(5);
		carCollection[getArrayCar()].book("Silver", "Surfer", thirdDate, 5);
		carBookComplete1.seedDataBooking("Silver", "Surfer", 30);
		setArrayCar(getArrayCar() + 1);
		setArrayBooking(getArrayBooking() + 1);
		
		Car carBookComplete2 = new Car("RIC654", "Honda", "Fit", "Blake Jones", 3);
		carCollection[getArrayCar()] = carBookComplete2;
		DateTime fourthDate = new DateTime(6);
		carCollection[getArrayCar()].book("Jimmy", "Neutron", fourthDate, 3);
		setArrayCar(getArrayCar() + 1);
		setArrayBooking(getArrayBooking() + 1);
		
		System.out.println("Data seeded");
	}
	
	public String getRegNo() {
		return regNo;
	}
	
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	
	public Car[] getCarCollection() {
		return carCollection;
	}
	
	public void setCarCollection(Car[] carCollection) {
		this.carCollection = carCollection;
	}

	public Booking[] getBookingCollection() {
		return bookingCollection;
	}

	public void setBookingCollection(Booking[] bookingCollection) {
		this.bookingCollection = bookingCollection;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public int getArrayCar() {
		return arrayCar;
	}

	public void setArrayCar(int arrayCar) {
		this.arrayCar = arrayCar;
	}

	public int getArrayBooking() {
		return arrayBooking;
	}

	public void setArrayBooking(int arrayBooking) {
		this.arrayBooking = arrayBooking;
	}
}

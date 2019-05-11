package main;

import system.Car;
import system.Booking;
import utilities.DateTime;
import java.util.Scanner;
import main.MiRidesSystem;

/*
 * Class:           Menu
 * Description:     The class represents a menu for a user
 * 					to interact and parse data into.
 * Author:			Peter Bui - s3786794
 */

public class Menu {
	/*
	 * ALGORITHM
	 * BEGIN
	 *     IF creating car
	 *         ASKS for registration number
	 *         IF registration number doesn't exist in the system
	 *            ASKS for make, model, driver's name and passenger capacity of car
	 *            CHECKS for passenger capacity and car storage memory
	 *            IF details pass all checks
	 *                CREATES a new car to be used by users
	 *     IF booking car
	 *         ASKS for a date
	 *             CHECKS for cars that are available on that date
	 *             CHECKS if date is able to be booked for a car
	 *             IF passes all checks
	 *                 DISPLAYS list of available cars
	 *                 PARSED car chosen by user
	 *                 PARSED relevant details for booking by user
	 *                 CHECKS if details can be used
	 *                 CHECKS if chosen car can still take in bookings
	 *                 IF details passes all checks
	 *                     BOOKING is stored in system
	 *     IF completing booking
	 *         PARSES either a registration number or date entered by the user
	 *         PARSES first and last 
	 *         IF a registration number is entered
	 *             CHECKS if registration number is usable
	 *             CHECKS if first and last names exist in booking collection
	 *             GETS registration number from booking collection through first and last name
	 *             IF entered and searched registration numbers match
	 *                 CALCULATE total trip fee
	 *                 DISPLAY that booking has been completed with total trip fee
	 *                 STORE dummy booking in place of completed booking
	 *         ELSE IF a date is entered
	 *             CHECKS if date exist in the booking collection
	 *             IF date exists in the collection
	 *                 CHECKS if entered first and last name appears in that specific booking
	 *                 IF they both appear
	 *                     GET registration number from booking id
	 *                     SEARCH for car that has registration number
	 *                     CALCULATE total trip fee
	 *                     DISPLAY that booking has been completed with total trip fee
	 *                	   STORE dummy booking in place of completed booking
	 *     IF displaying all cars
	 *         CHECKS if car collection is not null
	 *         IF car collection is not null
	 *             DISPLAY list of cars to user
	 *     IF searching for a specific car
	 *         PARSES a registration number entered by the user
	 *         CHECKS if registration number exists within system
	 *         IF registration number exists
	 *             DISPLAY searched car to the user
	 *     IF searching for available cars
	 *         NOT yet implemented
	 *     IF seeding data into system
	 *         CREATE 6 new hard-coded cars into system
	 *     IF exiting system
	 *         EXITS the program
	 * END
	 */
	public void menuStart() {
		Scanner input = new Scanner(System.in);
		MiRidesSystem miRidesSystem = new MiRidesSystem();
		
		char returnMenu = 'y';
		int carCreationArray = -1;
		
		DateTime dateDummy = new DateTime(-1);
		
		/* ensures that the carCollections and 
		 * bookingCollections are not null */
		miRidesSystem.carCollectionPrinter();
		miRidesSystem.bookingCollectionPrinter();
		
		while (returnMenu != 'n') {
			System.out.println("*** MiRides System Menu ***");
			System.out.println(String.format("%-31s %s", "Create Car", "CC"));
			System.out.println(String.format("%-31s %s", "Book Car", "BC"));
			System.out.println(String.format("%-31s %s", "Complete Booking", "CB"));
			System.out.println(String.format("%-31s %s", "Display ALL Cars", "DA"));
			System.out.println(String.format("%-31s %s", "Search Specific Car", "SS"));
			System.out.println(String.format("%-31s %s", "Search available cars", "SA"));
			System.out.println(String.format("%-31s %s", "Seed Data", "SD"));
			System.out.println(String.format("%-31s %s", "Exit Program", "EX"));
			System.out.print(String.format("%-32s", "Enter input:"));
			String doubleInput = input.nextLine();
			carCreationArray++;
					
			/* Handles the creation of a car object */
			if (doubleInput.equalsIgnoreCase("CC")) {
				System.out.print(String.format("%-32s", "Vehicle Registration Number:"));
				miRidesSystem.setRegNo(input.nextLine());
				if (miRidesSystem.getRegNo().length() != 6 || 
					!miRidesSystem.getRegNo().substring(0, 3).matches("[a-zA-Z]+")|| 
					!miRidesSystem.getRegNo().substring(3, 6).matches("[0-9]+")) {
					System.out.println("Error - Invalid vehicle registration number" + "\n");
					carCreationArray--;
					returnMenu = 'y';
				} else if (miRidesSystem.regNoCheck(miRidesSystem.getRegNo()) == false) {
					System.out.println("Error - RegNo already in system" + "\n");
					carCreationArray--;
					returnMenu = 'y';
				} else {
					System.out.print(String.format("%-32s", "Make of the Car:"));
					miRidesSystem.setMake(input.nextLine());	
					
					System.out.print(String.format("%-32s", "Model of the Car:"));
					miRidesSystem.setModel(input.nextLine());
					
					System.out.print(String.format("%-32s", "Driver's Name:"));
					miRidesSystem.setDriverName(input.nextLine());
					
					System.out.print(String.format("%-32s", "Amount of Passengers:"));
					miRidesSystem.setPassengerCapacity(input.nextInt());
					
					if (miRidesSystem.getPassengerCapacity() < 0 || 
						miRidesSystem.getPassengerCapacity() > 10) {
						System.out.println("Error - Invalid passenger amount" + "\n");
						System.out.print("Would you like to return to the menu(y/n)?: ");
						returnMenu = input.next().charAt(0);
						if (returnMenu == 'y') {
							doubleInput = input.nextLine();
						} else {
							System.out.println("Exiting System");
							System.exit(0);
						}
					} else if (carCreationArray == miRidesSystem.getCarCollection().length) {
						System.out.println("Not enough memory to store additional cars" + "\n");
						carCreationArray--;
						System.out.print("Would you like to return to the menu(y/n)?: ");
						returnMenu = input.next().charAt(0);
						if (returnMenu != 'y') {
							doubleInput = input.nextLine();
						} else {
							System.out.println("Exiting System");
							System.exit(0);
						}
					} else {
						Car car = new Car(miRidesSystem.getRegNo(), miRidesSystem.getMake(), 
										  miRidesSystem.getModel(), miRidesSystem.getDriverName(), 
										  miRidesSystem.getPassengerCapacity()); 
						miRidesSystem.getCarCollection()[carCreationArray] = car;
						miRidesSystem.setArrayCar(miRidesSystem.getArrayCar() + 1);
						String printRegNo = miRidesSystem.getCarCollection()
											[carCreationArray].getRegNo();
						System.out.println("\n" + "New car successfully added: " + printRegNo);
						System.out.print("Would you like to return to the menu(y/n)?: ");
						returnMenu = input.next().charAt(0);
						if (returnMenu == 'y') {
							doubleInput = input.nextLine();
						} else {
							System.out.println("Exiting System");
							System.exit(0);
						}
					}
				}	
			/* Handles the creation of a booking object to be used by a car object */
			} else if (doubleInput.equalsIgnoreCase("BC")){
				System.out.print(String.format("%-32s", "Booking date(dd/mm/yyyy): "));
				String dateInput = input.nextLine();
				if (!dateInput.contains("/")) {
					System.out.println("Error - Invalid input" + "\n");
					carCreationArray--;
					returnMenu = 'y';
				} else {
					String[] dateCheckAvailable = dateInput.split("/");
					int day = Integer.parseInt(dateCheckAvailable[0]);
					int month = Integer.parseInt(dateCheckAvailable[1]);
					int year = Integer.parseInt(dateCheckAvailable[2]);
					
					DateTime bookingDate = new DateTime(day, month, year);
					String bookingString = bookingDate.toString();	
					
					for (int i = 0; i < miRidesSystem.getArrayBooking(); i++) {
						String bookingCollectSystem = miRidesSystem.getBookingCollection()[i].
													  getPickUpDate().toString();
						if (bookingString.equals(bookingCollectSystem)) {
							Booking book = miRidesSystem.getBookingCollection()[i];
							String regNoId = book.getId().substring(0, 6);
							for (int l = 0; l < miRidesSystem.getArrayCar(); l++) {
								if (miRidesSystem.getCarCollection()[l].getRegNo().
									equalsIgnoreCase(regNoId)) {
									miRidesSystem.getCarCollection()[l].setAvailable(false);
								} else {
									miRidesSystem.getCarCollection()[l].setAvailable(true);
								}
							}
						}
					}
					DateTime currentDate = new DateTime();
					int difference = DateTime.diffDays(bookingDate, currentDate);
					if (difference > 8 || difference < 0) {
						System.out.println("Error - Invalid date" + "\n");
						carCreationArray--;
						System.out.print("Would you like to return to the menu(y/n)?: ");
						returnMenu = input.next().charAt(0);
						if (returnMenu == 'y') {
							doubleInput = input.nextLine();
						} else {
							System.out.println("Exiting System");
							System.exit(0);
						}
					} else {
						System.out.println("List of Available Cars:" + "\n");
						for (int i = 0; i < carCreationArray; i++) {
							if (miRidesSystem.getCarCollection()[i].getAvailable() == true) {
								System.out.println(i + 1 + ". " + miRidesSystem.
												   getCarCollection()[i].getRegNo());
							} else if (i + 1 == carCreationArray) {
								System.out.println("Error - Car not available.");
							}
						}
						System.out.println("\n" + "If no cars available, "
										   + "press n to return to menu.");
						System.out.print("Otherwise, press y to continue booking. ");
						char takeReturn = input.next().charAt(0);
						if (takeReturn == 'n') {
							carCreationArray--;
							returnMenu = 'y';
							doubleInput = input.nextLine();
						} else if (takeReturn != 'n') {
							System.out.print("\n" + String.format("%-32s", 
											 "Vehicle Registration Number:"));
							input.nextLine();
							String carSelection = input.nextLine();
							for (int i = 0; i < carCreationArray; i++) {
								if (miRidesSystem.getCarCollection()[i].
									getRegNo().equals(carSelection.toUpperCase())) {
									Car car = miRidesSystem.getCarCollection()[i];
									System.out.print(String.format("%-32s","First name:"));
									String firstName = input.nextLine();
									if (firstName.length() < 3) {
										System.out.println("Error - Required min 3 characters." + "\n");
										carCreationArray--;
										returnMenu = 'y';
									} else {
										System.out.print(String.format("%-32s", "Last name:"));
										String lastName = input.nextLine();
										if (lastName.length() < 3) {
											System.out.println("Error - Required min 3 characters." + "\n");
											carCreationArray--;
											returnMenu = 'y';
										} else {
											System.out.print(String.format("%-32s", "Number "
																		   + "of Passengers:"));
											int numPassengers = input.nextInt();
											if (numPassengers == 0 || miRidesSystem.getCarCollection()[i].
																	  getPassengerCapacity() < numPassengers) {
												System.out.println("Error - Number of passengers "
																	+ "is not valid." + "\n");
												carCreationArray--;
												returnMenu = 'y';
											} else {
												Booking booking = new Booking(firstName, lastName, 
																			  bookingDate, numPassengers, car);
												car.book(firstName, lastName, bookingDate, numPassengers);
												if (miRidesSystem.getCarCollection()[i].getAvailable() == false) {
													System.out.println("Error - Car has too many bookings currently.");
													carCreationArray--;
													System.out.print("Would you like to return to the menu(y/n)?: ");
													returnMenu = input.next().charAt(0);
													if (returnMenu == 'y') {
														doubleInput = input.nextLine();
													} else {
														System.out.println("Exiting System");
														System.exit(0);
													}
												} else if (miRidesSystem.getCarCollection()[i].getAvailable() == true) {
													miRidesSystem.getBookingCollection()[i] = 
															new Booking(firstName, lastName, bookingDate, numPassengers, car);
													System.out.println("\n" + "Thank you for booking.");
													System.out.println(miRidesSystem.getCarCollection()[i].getDriverName() + 
																	   " will pick you up on " + bookingDate.getFormattedDate());
													System.out.println("Your booking reference is: " + booking.getId() + "\n");
													miRidesSystem.setArrayBooking(miRidesSystem.getArrayBooking() + 1);
													carCreationArray--;
													System.out.print("Would you like to return to the menu(y/n)?: ");
													returnMenu = input.next().charAt(0);
													if (returnMenu == 'y') {
														doubleInput = input.nextLine();
													} else {
														System.out.println("Exiting System");
														System.exit(0);
													}
												}
											}
										} 
									} 
								} else if (i == carCreationArray) {
									System.out.println("Not Found");
									carCreationArray--;
									System.out.print("Would you like to return to the menu(y/n)?: ");
									returnMenu = input.next().charAt(0);
									if (returnMenu == 'y') {
										doubleInput = input.nextLine();
									} else {
										System.out.println("Exiting System");
										System.exit(0);
									}
								}
							}
						}
					}
				}	
			/* Handles the completion of a booking object */
			} else if (doubleInput.equalsIgnoreCase("CB")) {
				System.out.print(String.format("%-32s", "Enter Registration "
											   + "Number/Booking Date: "));
				String regDateInput = input.nextLine();
	
				System.out.print(String.format("%-32s", "Enter first name: "));
				String firstName = input.nextLine();
				
				System.out.print(String.format("%-32s", "Enter last name: "));
				String lastName = input.nextLine();
				
				if (regDateInput.length() == 6 && regDateInput.substring(0, 3).matches("[a-zA-Z]+")
						&& regDateInput.substring(3, 6).matches("[0-9]+")) {
					for (int i = 0; i < carCreationArray; i++) {
						if (miRidesSystem.getCarCollection()[i].getRegNo().
							equals(regDateInput.toUpperCase())) {
							for (int l = 0; l < miRidesSystem.getBookingCollection().length; l++) {
								if (miRidesSystem.getBookingCollection()[l].
										getFirstName().equalsIgnoreCase(firstName) &&
									miRidesSystem.getBookingCollection()[l].
										getLastName().equalsIgnoreCase(lastName)) {
									Booking book = miRidesSystem.getBookingCollection()[l];
									String bookingRegNo = book.getId().substring(0, 6);
									if (regDateInput.equalsIgnoreCase(bookingRegNo)) {
										Car car = miRidesSystem.getCarCollection()[i];
										miRidesSystem.getBookingCollection()[l] = 
											new Booking("noFirstName", "noLastName", dateDummy, 1, car);
										car.bookingComplete(firstName, lastName);	
									} else if (!regDateInput.equalsIgnoreCase(bookingRegNo)) {
										System.out.println("Error - Cannot find booking");
									}
								} else if (l == miRidesSystem.getBookingCollection().length - 1) {
									System.out.println("Error - Cannot find booking");
								}
							}
						} else if (i == carCreationArray - 1) {
							System.out.println("Error - Cannot find booking");
						}
					}
				} else if (regDateInput.contains("/")){
					String[] dateSplit = regDateInput.split("/");
					int day = Integer.parseInt(dateSplit[0]);
					int month = Integer.parseInt(dateSplit[1]);
					int year = Integer.parseInt(dateSplit[2]);	
					
					DateTime userDateInput = new DateTime(day, month, year);
					String dateInput = userDateInput.toString();
					
					for (int m = 0; m < miRidesSystem.getBookingCollection().length; m++) {
						String dateCollection = 
							miRidesSystem.getBookingCollection()[m].getPickUpDate().toString();
						if (dateInput.equals(dateCollection)) {
							Booking book = miRidesSystem.getBookingCollection()[m];
							String regNoId = book.getId().substring(0, 6);
							for (int n = 0; n < carCreationArray; n++) {
								if (miRidesSystem.getCarCollection()[n].
									getRegNo().equalsIgnoreCase(regNoId)) {
									Car car = miRidesSystem.getCarCollection()[n];
									miRidesSystem.getBookingCollection()[m] = 
										new Booking("noFirstName", "noLastName", dateDummy, 1, car);
									car.bookingComplete(firstName, lastName);
								} else if (n == carCreationArray - 1) {
									System.out.println("Error - Cannot find booking");
								}
							}
						} else if (m == miRidesSystem.getBookingCollection().length - 1) {
							System.out.println("Error - Cannot find booking");
						}
					}
				}
				carCreationArray--;
				System.out.print("Would you like to return to the menu(y/n): ");
				returnMenu = input.next().charAt(0);
				if (returnMenu == 'y') {
					doubleInput = input.nextLine();
				} else {
					System.out.println("Exiting System");
					System.exit(0);
				}
			/* Handles displaying all cars to the user upon selection this option */
			} else if (doubleInput.equalsIgnoreCase("DA")) {
				if (miRidesSystem.getCarCollection() == null) {
					System.out.println("Error - No cars within the system" + "\n");
					carCreationArray--;
					returnMenu = 'y';
				} else {
					for (int i = 0; i < carCreationArray; i++) {
						System.out.println(miRidesSystem.getCarCollection()[i].
										   getDetails() + "\n");
					}
					carCreationArray--;
					System.out.print("Would you like to return to the menu(y/n)?: ");
					returnMenu = input.next().charAt(0);
					if (returnMenu == 'y') {
						doubleInput = input.nextLine();
					} else {
						System.out.println("Exiting System");
						System.exit(0);
					}
				}
			/* Handles the search of a specific car requested by the user */
			} else if (doubleInput.equalsIgnoreCase("SS")) {
				System.out.print(String.format("%-32s", "Vehicle Registration Number:"));
				String id = input.nextLine();
				for (int i = 0; i < miRidesSystem.getCarCollection().length; i++) {
					if (miRidesSystem.getCarCollection()[i].
						getRegNo().equals(id.toUpperCase())) {
						System.out.println("Found Matching Registration Number");
						System.out.println(miRidesSystem.getCarCollection()[i].
										   getDetails() + "\n");
						break;
					} else if (i == miRidesSystem.getCarCollection().length - 1) {
						System.out.println("Error - Registration number not found");
					}
				}
				carCreationArray--;
				System.out.print("Would you like to return to the menu(y/n)?: ");
				returnMenu = input.next().charAt(0);
				if (returnMenu == 'y') {
					doubleInput = input.nextLine();
				} else {
					System.out.println("Exiting System");
					System.exit(0);
				}
			/* Currently isn't implemented */
			} else if (doubleInput.equalsIgnoreCase("SA")){
				System.out.println("\n" + "Not yet implemented" + "\n");
				carCreationArray--;
				System.out.print("Would you like to return to the menu(y/n)?: ");
				returnMenu = input.next().charAt(0);
				if (returnMenu == 'y') {
					doubleInput = input.nextLine();
				} else {
					System.out.println("Exiting System");
					System.exit(0);
				}
			/* Handles the seeding of additional car and booking objects in the system*/
			} else if (doubleInput.equalsIgnoreCase("SD")) {
				miRidesSystem.seedDataMethod();
				carCreationArray += 5;
				System.out.print("Would you like to return to the menu(y/n)?: ");
				returnMenu = input.next().charAt(0);
				if (returnMenu == 'y') {
					doubleInput = input.nextLine();
				} else {
					System.out.println("Exiting System");
					System.exit(0);
				}
			/* Handles the exit of the program */
			} else if (doubleInput.equalsIgnoreCase("EX")) {
				System.out.println("Exiting System");
				System.exit(0);
			} else {
				System.out.println("Invalid input" + "\n" + "Exiting program");
				System.exit(0);
			}
		}
		input.close();	
	}
}

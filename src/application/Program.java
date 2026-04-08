package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);

		try {
			System.out.print("Room number: ");
			int number = sc.nextInt();
			sc.nextLine();
			System.out.print("Check-in date (dd/MM/yyyy): ");
			LocalDate checkIn = LocalDate.parse(sc.nextLine(), fmt);
			System.out.print("Check-out date (dd/MM/yyyy): ");
			LocalDate checkOut = LocalDate.parse(sc.nextLine(), fmt);

			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println("Reservation: " + reservation);

			System.out.println();
			System.out.println("Enter data to update the reservation: ");
			System.out.print("Check-in date (dd/MM/yyyy): ");
			checkIn = LocalDate.parse(sc.nextLine(), fmt);
			System.out.print("Check-out date (dd/MM/yyyy): ");
			checkOut = LocalDate.parse(sc.nextLine(), fmt);

			reservation.updateDates(checkIn, checkOut);
			System.out.println("Reservation: " + reservation);
		} catch (DomainException e) {
			System.out.println("Error in reservation: " + e.getMessage());
		}
		catch(RuntimeException e) {
			System.out.println("Unexpected error");
		}
		sc.close();
	}

}

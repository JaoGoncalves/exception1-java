package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import model.exceptions.DomainException;

public class Reservation {
	private static final DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	// Attributes
	private Integer roomNumber;
	private LocalDate checkIn;
	private LocalDate checkOut;

	// Constructors
	public Reservation() {
	}

	public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) {
		if (!checkOut.isAfter(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	// Getters and Setters
	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	// Methods
	public long duration() {
		long diff = ChronoUnit.DAYS.between(checkIn, checkOut);
		return diff;
	}

	public void updateDates(LocalDate checkIn, LocalDate checkOut) {
		LocalDate now = LocalDate.now();
		if (checkIn.isBefore(now) || checkOut.isBefore(now)) {
			throw new DomainException("Reservation dates for update must be futures dates");
		}
		if (!checkOut.isAfter(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public String toString() {
		return "Room " + roomNumber + ", check-in: " + fmt1.format(checkIn) + ", check-out: " + fmt1.format(checkOut)
				+ ", " + duration() + " nights";
	}
}

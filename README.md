# Bus Ticket Booking System 🚍

A Java-based Command Line Interface (CLI) application for managing bus ticket reservations between Anuppur and Bhopal.

## Features ✨

- **User Authentication**:
  - Separate registration and login for users and administrators
  - Secure password management

- **Ticket Booking**:
  - View available seats with tiered pricing (₹1100, ₹1200, ₹1500)
  - Book seats with automatic timestamp
  - Prevent double-booking of seats

- **Admin Dashboard**:
  - View all booked seats with passenger details
  - Monitor booking times and payments

- **Route Information**:
  - Fixed route: Anuppur to Bhopal (600km)
  - Clear display of trip details

## Technologies Used 💻

- Java 8+
- Java Collections Framework
- Java Time API for timestamps
- Scanner for user input

## How to Run 🚀

1. Ensure you have Java JDK installed (version 8 or higher)
2. Clone this repository
3. Compile the Java file:
   ```bash
   javac BusTicketBooking.java

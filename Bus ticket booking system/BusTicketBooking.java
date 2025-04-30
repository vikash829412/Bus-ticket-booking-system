import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BusTicketBooking {
    private Map<String, String> admins = new HashMap<>();
    private Map<String, Map<String, String>> users = new HashMap<>();
    private Map<Integer, Map<String, Object>> bookedSeats = new HashMap<>();
    private Map<Integer, String> availableSeats = new HashMap<>();
    private String loggedInUser = null;
    private String loggedInAdmin = null;
    private final String source = "Anuppur";
    private final String destination = "Bhopal";
    private final int distance = 600;
    private Scanner scanner = new Scanner(System.in);

    public BusTicketBooking() {
        // Initialize available seats (1-30)
        for (int i = 1; i <= 30; i++) {
            availableSeats.put(i, null);
        }
    }

    public void welcomeScreen() {
        System.out.println("\n🚍🚍🚍🚍🚍🚍🚍🚍 Welcome to Chartered Bus Service! 🚍🚍🚍🚍🚍🚍🚍🚍");
        while (true) {
            System.out.println("\n1️⃣ Admin Registration");
            System.out.println("2️⃣ User Registration");
            System.out.println("3️⃣ Exit");
            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    registerAdmin();
                    break;
                case "2":
                    registerUser();
                    break;
                case "3":
                    System.out.println("\nThank you for using Chartered Bus Service!");
                    return;
                default:
                    System.out.println("\n❌ Invalid choice. Try again. ❌");
            }
        }
    }

    private void registerAdmin() {
        System.out.println("\n🔹 **************************Admin Registration************************** 🔹");
        System.out.print("Enter Admin ID: ");
        String adminId = scanner.nextLine();
        System.out.print("Create Password: ");
        String password = scanner.nextLine();

        if (admins.containsKey(adminId)) {
            System.out.println("\n❌ Admin ID already exists! ❌");
            return;
        }

        admins.put(adminId, password);
        System.out.println("\n✅ Admin registered successfully! ✅");
        loginAdmin();
    }

    private void registerUser() {
        System.out.println("\n🔹 **************************User Registration************************** 🔹");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your mobile number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter your age: ");
        String age = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Choose a User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Create a password: ");
        String password = scanner.nextLine();

        if (users.containsKey(userId)) {
            System.out.println("\n❌ User ID already exists! ❌");
            return;
        }

        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("name", name);
        userDetails.put("phone", phone);
        userDetails.put("age", age);
        userDetails.put("email", email);
        userDetails.put("password", password);

        users.put(userId, userDetails);
        System.out.println("\n✅ User registered successfully! ✅");
        loginUser();
    }

    private void loginAdmin() {
        System.out.println("\n🔹 **************************Admin Login************************** 🔹");
        System.out.print("Enter Admin ID: ");
        String adminId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (admins.containsKey(adminId) && admins.get(adminId).equals(password)) {
            loggedInAdmin = adminId;
            System.out.println("\n✅ Welcome, Admin " + adminId + "! ✅");
            adminMenu();
        } else {
            System.out.println("\n❌ Incorrect Admin ID or Password. ❌");
        }
    }

    private void loginUser() {
        System.out.println("\n🔹 **************************User Login************************** 🔹");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (users.containsKey(userId) && users.get(userId).get("password").equals(password)) {
            loggedInUser = userId;
            System.out.println("\n✅ Welcome, " + users.get(userId).get("name") + "! ✅");
            showTripDetails();
            userMenu();
        } else {
            System.out.println("\n❌ Incorrect User ID or Password. ❌");
        }
    }

    private void showTripDetails() {
        System.out.println("\n🚌 **************************Bus Route Details**************************");
        System.out.println("📍 Source: " + source);
        System.out.println("🎯 Destination: " + destination);
        System.out.println("📏 Distance: " + distance + " km");
    }

    private void adminMenu() {
        while (loggedInAdmin != null) {
            System.out.println("\n🔹🔹🔹🔹🔹🔹🔹 Admin Panel: 🔹🔹🔹🔹🔹🔹🔹");
            System.out.println("1️⃣ View Booked Seats");
            System.out.println("2️⃣ Logout");
            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewBookedSeats();
                    break;
                case "2":
                    logoutAdmin();
                    return;
                default:
                    System.out.println("\n❌ Invalid choice. Try again. ❌");
            }
        }
    }

    private void userMenu() {
        while (loggedInUser != null) {
            System.out.println("\n🔹🔹🔹🔹🔹🔹🔹 User Menu: 🔹🔹🔹🔹🔹🔹🔹");
            System.out.println("1️⃣ View Available Seats");
            System.out.println("2️⃣ Book a Seat");
            System.out.println("3️⃣ Logout");
            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAvailableSeats();
                    break;
                case "2":
                    bookSeat();
                    break;
                case "3":
                    logoutUser();
                    return;
                default:
                    System.out.println("\n❌ Invalid choice. Try again. ❌");
            }
        }
    }

    private void viewBookedSeats() {
        System.out.println("\n📌 **************************Booked Seats Details:************************** 📌");
        if (bookedSeats.isEmpty()) {
            System.out.println("ℹ️ No seats booked yet.");
        } else {
            for (Map.Entry<Integer, Map<String, Object>> entry : bookedSeats.entrySet()) {
                System.out.println("Seat: " + entry.getKey());
                System.out.println("Name: " + entry.getValue().get("user_name"));
                System.out.println("Source: " + source);
                System.out.println("Destination: " + destination);
                System.out.println("Booking Time: " + entry.getValue().get("booking_time"));
                System.out.println("Payment: ₹" + entry.getValue().get("price"));
                System.out.println("-------------------------------");
            }
        }
    }

    private void viewAvailableSeats() {
        System.out.println("\n🪑 **************************Available Seats & Prices:************************** 🪑");
        boolean seatsAvailable = false;
        
        for (int seat = 1; seat <= 30; seat++) {
            if (availableSeats.get(seat) == null) {
                int price = 1100;
                if (seat >= 11 && seat <= 20) {
                    price = 1200;
                } else if (seat >= 21 && seat <= 30) {
                    price = 1500;
                }
                System.out.println("Seat " + seat + " → ₹" + price);
                seatsAvailable = true;
            }
        }
        
        if (!seatsAvailable) {
            System.out.println("ℹ️ No seats available.");
        }
    }

    private void bookSeat() {
        viewAvailableSeats();
        System.out.print("\nEnter the seat number you want to book: ");
        int seat = Integer.parseInt(scanner.nextLine());

        if (seat < 1 || seat > 30 || availableSeats.get(seat) != null) {
            System.out.println("\n❌ Seat not available. ❌");
            return;
        }

        int price = 1100;
        if (seat >= 11 && seat <= 20) {
            price = 1200;
        } else if (seat >= 21 && seat <= 30) {
            price = 1500;
        }

        String bookingTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yy , HH:mm:ss"));
        availableSeats.put(seat, loggedInUser);

        Map<String, Object> bookingDetails = new HashMap<>();
        bookingDetails.put("user_id", loggedInUser);
        bookingDetails.put("user_name", users.get(loggedInUser).get("name"));
        bookingDetails.put("booking_time", bookingTime);
        bookingDetails.put("price", price);

        bookedSeats.put(seat, bookingDetails);
        System.out.println("\n✅ Seat " + seat + " booked successfully at " + bookingTime + "! ✅");
    }

    private void logoutUser() {
        System.out.println("\n👋 " + users.get(loggedInUser).get("name") + " logged out successfully. 👋");
        loggedInUser = null;
    }

    private void logoutAdmin() {
        System.out.println("\n👋 Admin " + loggedInAdmin + " logged out successfully. 👋");
        loggedInAdmin = null;
    }

    public static void main(String[] args) {
        BusTicketBooking bus = new BusTicketBooking();
        bus.welcomeScreen();
    }
}
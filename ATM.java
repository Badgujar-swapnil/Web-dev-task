import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// User class to represent each user of the ATM
class User {
    private int userID;
    private int userPIN;
    private double accountBalance;

    // Constructor
    public User(int userID, int userPIN, double accountBalance) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.accountBalance = accountBalance;
    }

    // Getters
    public int getUserID() {
        return userID;
    }

    public int getUserPIN() {
        return userPIN;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    // Method to withdraw money
    public boolean withdraw(double amount) {
        if (amount > accountBalance) {
            System.out.println("Insufficient funds");
            return false;
        }
        accountBalance -= amount;
        System.out.println("Withdrawal successful. Remaining balance: $" + accountBalance);
        return true;
    }

    // Method to deposit money
    public void deposit(double amount) {
        accountBalance += amount;
        System.out.println("Deposit successful. New balance: $" + accountBalance);
    }
}

// ATM class that encapsulates ATM functionalities
public class ATM {
    private Map<Integer, User> users; // Mapping userID to User object

    // Constructor
    public ATM() {
        users = new HashMap<>();
        // Sample user data
        users.put(123456, new User(123456, 1234, 1000.0));
        users.put(789012, new User(789012, 5678, 2000.0));
    }

    // Method for user authentication
    public User authenticate(int userID, int userPIN) {
        User user = users.get(userID);
        if (user != null && user.getUserPIN() == userPIN) {
            return user;
        }
        return null; // Authentication failed
    }

    // Method to perform ATM functionalities
    public void operateATM(User user) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        double amount;
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Your current balance is: $" + user.getAccountBalance());
                    break;
                case 2:
                    System.out.print("Enter the amount to withdraw: $");
                    amount = scanner.nextDouble();
                    user.withdraw(amount);
                    break;
                case 3:
                    System.out.print("Enter the amount to deposit: $");
                    amount = scanner.nextDouble();
                    user.deposit(amount);
                    break;
                case 4:
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);
        int userID, userPIN;
        User user;
        while (true) {
            System.out.println("Welcome to the ATM interface!");
            System.out.print("Enter your user ID: ");
            userID = scanner.nextInt();
            System.out.print("Enter your PIN: ");
            userPIN = scanner.nextInt();
            user = atm.authenticate(userID, userPIN);
            if (user != null) {
                System.out.println("Authentication successful!");
                atm.operateATM(user);
                break;
            } else {
                System.out.println("Authentication failed. Please try again.");
            }
        }
    }
}

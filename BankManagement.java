import java.util.Scanner;

// BankAccount class to handle individual accounts
class BankAccount {
    private String accountHolderName;
    private String accountNumber;
    private double balance;
    private int age;
    private String accountType;

    // Constructor to initialize account details
    public BankAccount(String accountHolderName, String accountNumber, double initialBalance, int age) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.age = age;
        this.accountType = (age < 18) ? "Minor" : "Major"; // Set account type based on age
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Current balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Current balance: $" + balance);
        } else {
            System.out.println("Insufficient balance or invalid withdrawal amount.");
        }
    }

    // Method to display account details
    public void displayAccountDetails() {
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Age: " + age);
        System.out.println("Account Type: " + accountType);
        System.out.println("Current Balance: $" + balance);
    }

    // Method to get account number
    public String getAccountNumber() {
        return accountNumber;
    }
}

// Main class to handle the system
public class BankManagement {
    private static Scanner scanner = new Scanner(System.in);
    private static BankAccount[] accounts = new BankAccount[10];
    private static int accountCount = 0;

    public static void main(String[] args) {
        int choice;
        System.out.println("\n--- Bank Management System ---");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. View Account Details");
        System.out.println("5. Exit");

        do {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    viewAccountDetails();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    // Method to create a new account
    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.next();
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();

        accounts[accountCount] = new BankAccount(name, accountNumber, initialDeposit, age);
        accountCount++;
        System.out.println("Account created successfully as a " + ((age < 18) ? "Minor" : "Major") + " account.");
    }

    // Method to deposit money
    private static void deposit() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        BankAccount account = findAccount(accountNumber);

        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to withdraw money
    private static void withdraw() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        BankAccount account = findAccount(accountNumber);

        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to view account details
    private static void viewAccountDetails() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        BankAccount account = findAccount(accountNumber);

        if (account != null) {
            account.displayAccountDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to find account by account number
    private static BankAccount findAccount(String accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;
    }
}

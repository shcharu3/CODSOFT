import java.util.Scanner;

// Represents user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// Represents ATM machine interface
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\nWelcome to MyBank ATM");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void run() {
        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using MyBank ATM!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void checkBalance() {
        System.out.printf("Your current balance: ₹%.2f%n", account.getBalance());
    }

    private void deposit() {
        System.out.print("Enter deposit amount: ₹");
        double amount = scanner.nextDouble();
        
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter positive value.");
            return;
        }
        
        account.deposit(amount);
        System.out.printf("₹%.2f deposited successfully.%n", amount);
        System.out.printf("New balance: ₹%.2f%n", account.getBalance());
    }

    private void withdraw() {
        System.out.print("Enter withdrawal amount: ₹");
        double amount = scanner.nextDouble();
        
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter positive value.");
            return;
        }
        
        if (account.withdraw(amount)) {
            System.out.printf("₹%.2f withdrawn successfully.%n", amount);
            System.out.printf("Remaining balance: ₹%.2f%n", account.getBalance());
        } else {
            System.out.println("Withdrawal failed. Insufficient balance or invalid amount.");
        }
    }
}

// Main class to run the ATM
public class ATMSystem {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(5000); // Initial balance ₹5000
        ATM atm = new ATM(userAccount);
        atm.run();
    }
}

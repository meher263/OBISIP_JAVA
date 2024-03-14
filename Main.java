import java.util.Scanner;

class User {
    private String userId;
    private String userPin;

    public User(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }
}

class Account {
    private double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void transfer(Account receiverAccount, double amount) {
        if (amount <= balance) {
            balance -= amount;
            receiverAccount.deposit(amount);
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Insufficient funds for transfer!");
        }
    }
}

class ATM {
    private User user;
    private Account account;

    public ATM(User user, Account account) {
        this.user = user;
        this.account = account;
    }

    public void showOptions() {
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Check Balance");
        System.out.println("6. Quit");
    }

    public void checkBalance() {
        System.out.println("Current Balance: $" + account.getBalance());
    }

    public void processTransaction(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                System.out.println("Transaction History: Not implemented in this demo.");
                break;
            case 2:
                System.out.print("Enter the amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                account.withdraw(withdrawAmount);
                System.out.println("Withdrawal successful!");
                break;
            case 3:
                System.out.print("Enter the amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                account.deposit(depositAmount);
                System.out.println("Deposit successful!");
                break;
            case 4:
                System.out.print("Enter the user ID of the receiver: ");
                String receiverUserId = scanner.next();
                System.out.print("Enter the amount to transfer: ");
                double transferAmount = scanner.nextDouble();

                // In a real-world scenario, you would fetch the receiver's account based on the user ID.
                Account receiverAccount = new Account(0);
                account.transfer(receiverAccount, transferAmount);
                break;
            case 5:
                checkBalance();
                break;
            case 6:
                System.out.println("Exiting ATM. Thank you!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Demo User and Account
        User user = new User("123456", "7890");
        Account account = new Account(0.0);
        ATM atm = new ATM(user, account);

        Scanner scanner = new Scanner(System.in);

        // Authentication
        System.out.print("Enter User ID: ");
        String enteredUserId = scanner.next();
        System.out.print("Enter User PIN: ");
        String enteredUserPin = scanner.next();

        if (enteredUserId.equals(user.getUserId()) && enteredUserPin.equals(user.getUserPin())) {
            System.out.println("Authentication successful! Welcome to the ATM.");

            while (true) {
                atm.showOptions();
                System.out.print("Enter your choice (1-6): ");
                int choice = scanner.nextInt();

                atm.processTransaction(choice, scanner);
            }
        } else {
            System.out.println("Authentication failed. Exiting.");
        }
    }
}
// Interface definition
interface BankInterface {
    double getBalance(); // Abstract method to get balance
    double getInterestRate(); // Abstract method to get interest rate
}

// BankA class implementing the BankInterface
class BankA implements BankInterface {
    private double balance;

    public BankA(double balance) {
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterestRate() {
        return 7.0; // 7% interest rate
    }
}

// BankB class implementing the BankInterface
class BankB implements BankInterface {
    private double balance;

    public BankB(double balance) {
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterestRate() {
        return 7.4; // 7.4% interest rate
    }
}

// BankC class implementing the BankInterface
class BankC implements BankInterface {
    private double balance;

    public BankC(double balance) {
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterestRate() {
        return 7.9; // 7.9% interest rate
    }
}

public class Interface1 {
    public static void main(String[] args) {
        // Instantiate bank objects with deposit amounts
        BankA bankA = new BankA(10000);
        BankB bankB = new BankB(150000);
        BankC bankC = new BankC(200000);

        // Display balance and interest rate for each bank
        System.out.println("Bank A Balance: " + bankA.getBalance() + ", Interest Rate: " + bankA.getInterestRate() + "%");
        System.out.println("Bank B Balance: " + bankB.getBalance() + ", Interest Rate: " + bankB.getInterestRate() + "%");
        System.out.println("Bank C Balance: " + bankC.getBalance() + ", Interest Rate: " + bankC.getInterestRate() + "%");
    }
}
package banking;

public class SavingsAccount extends BankAccount {
	
	private double interestRate;

	/**
	 * @param interestRate the monthly interest rate as a fraction, NOT a percentage.
	 */
	public SavingsAccount(int accountNumber, String accountHolder,
						  double openingBalance, double interestRate) {
		super(accountNumber, accountHolder, openingBalance);
		this.interestRate = interestRate;
	}
	
	/**
	 * Add interest earned to the account balance.
	 */
	public void addInterest() {
		deposit(balance * interestRate);
	}

	@Override
	public void deductFees() {
		// Fixed R50 fee.
		withdraw(50);
	}

	@Override
	public boolean withdraw(double amount) {
		// Cancel withdrawal if balance would fall below R500. 
		if (balance - amount < 500)
			return false;
		balance -= amount;
		return true;
	}

}

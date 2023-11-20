package banking;

public class CurrentAccount extends BankAccount {

	public CurrentAccount(int accountNumber, String accountHolder,
			double openingBalance) {
		super(accountNumber, accountHolder, openingBalance);
	}

	@Override
	//this is called at the end of the month
	public void deductFees() {
		int chargeableTransactions = 0;
		for (int i = 0; i < transactionCount; i++)
			if (transactions.get(i).transactionType == Transaction.DEPOSIT
					|| transactions.get(i).transactionType == Transaction.WITHDRAW)
				chargeableTransactions++;
		// Charges: R1.80 per transaction after the first 5.
		withdraw(1.80 * Math.max(0, chargeableTransactions - 5));
	}

	@Override
	public boolean withdraw(double amount) {
		// Cancel withdrawal if balance would fall below R0. 
		if (balance < amount)
			return false;
		balance -= amount;
		return true;
	}

}

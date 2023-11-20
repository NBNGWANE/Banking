/**
 * 
 */
package banking;

import java.util.ArrayList;

/**
 * The BankAccount class represents a bank account and is meant mainly
 * to be sub-classed
 */
public abstract class BankAccount {
	private int accountNumber;
	private String accountHolder;
	protected double balance = 0;
	protected ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	protected int transactionCount = 0;
	
	public BankAccount(int accountNumber, String accountHolder, double openingBalance){
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.balance = openingBalance;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public int getTransactionCount() {
		return transactionCount;
	}
	
	public double getBalance(){
		return balance;
	}
	
	
	public void deposit(double amount){
		balance += amount;
	}
	
	//Transfer amount from this account to account b
	public void transferTo (BankAccount b, double amount){
		balance -= amount;
		b.balance += amount;
	}
	
	public abstract void deductFees();
	
	public abstract boolean withdraw (double amount);
	
	public void processTransaction(Transaction t){
		
		this.transactions.add(t); //add this transaction to the list of transactions
		transactionCount ++;
		switch (t.transactionType){
			case Transaction.WITHDRAW:
				if (! withdraw(t.amount))   //withdraw an amount - cancel the transaction if its 
											//not possible to do the withdrawal 
					t.cancelled = true;
				break;
			case Transaction.DEPOSIT:
				deposit(t.amount); 
				break;
			case Transaction.TRANSFER:
				transferTo(t.toAccount, t.amount); 
				break;
			default: System.err.println("Wrong transaction type");
		}
	}
	
	public String toString(){
		return accountNumber + "has a balance of : " + balance;
	}
	
	public void printStatement(){
		System.out.println("Statement for account number " + accountNumber);
		System.out.println("Account holder: " + accountHolder);
		System.out.printf("Balance: R %.2f%n", balance);
		System.out.println(transactionCount + " transactions this month:");
		for (int i = 0; i < transactionCount; i++)
			if (transactions.get(i).isCancelled())
				System.out.println("  " + transactions.get(i) + " [CANCELLED]");
			else
				System.out.println("  " + transactions.get(i));
		System.out.println();
	}
	
}

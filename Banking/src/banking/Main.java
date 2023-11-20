package banking;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

    public static void createAccounts(String filename) throws IOException {
        // Create accounts.
        Scanner accountFile = new Scanner(new File(filename));
        // Skip first line.
        accountFile.nextLine();
        while (accountFile.hasNextLine()) {

            //read a line and split the contents of the line
            //add each token to an array of String
            String[] line = accountFile.nextLine().split("\t+");

            // Get rid of extra spaces.
            for (int i = 0; i < line.length; i++)
                line[i] = line[i].trim();

            int acctNo = Integer.parseInt(line[0]);
            double initialBalance = Double.parseDouble(line[3]);
            BankAccount acct = null;
            if (line[1].equalsIgnoreCase("current"))
                acct = new CurrentAccount(acctNo, line[2], initialBalance);
            else if (line[1].equalsIgnoreCase("savings"))
                acct = new SavingsAccount(acctNo, line[2],
                        initialBalance, 0.015);

            accounts.add(acct);
        }
        accountFile.close();
    }

    //find and return the account specified by accountNumber
    public static BankAccount getAccount (int accountNumber){

        boolean found = false;
        int i = 0; //index
        BankAccount account = null;

        //search through the arrayList until you find
        while(!found || i < accounts.size()){
            if (accounts.get(i).getAccountNumber() == accountNumber){
                found = true;
                account = accounts.get(i);
                i++;
            }
            else
                i++;
        }

        return account;

    }
    public static void main(String[] args) throws IOException{
        createAccounts("accounts.txt");

        // Perform transactions.
        Scanner transactionFile = new Scanner(new File("transactions.txt"));
        // Skip first line.
        transactionFile.nextLine();
        while (transactionFile.hasNextLine()) {
            String[] line = transactionFile.nextLine().split("\\s+");
            BankAccount acct = getAccount(Integer.parseInt(line[3]));

            Transaction trans = null;
            int day = Integer.parseInt(line[0]);
            int month = Integer.parseInt(line[1]);
            int year = Integer.parseInt(line[2]);
            double amount = Double.parseDouble(line[5]);
            if (line[4].equalsIgnoreCase("transfer"))
                trans = new Transaction(day, month, year, Transaction.TRANSFER,
                        getAccount(Integer.parseInt(line[6])),
                        amount);
            else if (line[4].equalsIgnoreCase("withdraw"))
                trans = new Transaction(day, month, year, Transaction.WITHDRAW,
                        null, amount);
            else if (line[4].equalsIgnoreCase("deposit"))
                trans = new Transaction(day, month, year, Transaction.DEPOSIT,
                        null, amount);

            acct.processTransaction(trans);
        }
        transactionFile.close();

        // Add interest, and deduct fees.
        for (BankAccount acct : accounts) {
            if (acct instanceof SavingsAccount)
                ((SavingsAccount)acct).addInterest();
            acct.deductFees();
        }

        // Display statements.
        System.out.println("Account statements:");
        System.out.println();
        for (BankAccount acct : accounts)
            acct.printStatement();

    }


}

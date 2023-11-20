# Banking
Create a set of classes to implement the banking application described below.
This bank provides two kinds of bank accounts: savings accounts and current accounts. All accounts:
1. have an account number, an account holder and a balance,
2. accept deposits,
3. provide a method to query the balance,
4. a method to transfer money from one account to the other, and
5. a method to print the account number, and the current balance.
6. pay a service fee.
7. allow the account holder is allowed to transfer an amount of money from one account to another account
(held at the same bank).
8. At the end of every month, the bank issues a statement for each bank account. The statement lists the
transactions completed during the month.
The differences between the two types of accounts are as follows:
• Only savings account earns interest. At the end of every month, the bank adds interest only to savings
accounts. The interest equals 1.5% of the month-ending balance for the account for that month.
• – All savings accounts pay a fixed monthly fee of R50.
– All current accounts pay a monthly service fee which is calculated as follows: The first five transactions
(a transaction for a current account is considered to be a deposit or a withdrawal) are free; therafter,
the holder of the account pays R1.80 per additional transaction. This fee is deducted at the end of
the month.
• All bank accounts allow withdrawals, provided that the balance never falls below zero for current accounts
and R500 for savings accounts. If there are sufficient funds, the withdrawal proceeds. Otherwise, the bank
cancels the withdrawal.

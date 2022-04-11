package it.unina.spme.testing.bank;

public class ItalianBankTransactionLedger implements BankTransactionLedger{
    public void write() {
    }

    public void write(BankAccount bankAccount, String operation, double amount) {
        System.out.println(bankAccount.getHolder() + " " + operation + " " + amount);
    }
}

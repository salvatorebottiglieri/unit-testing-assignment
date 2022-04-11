package it.unina.spme.testing.bank;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.hamcrest.MockitoHamcrest;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;



public class BankAccountTest {

     ItalianBankTransactionLedger bankTransactionLedgerMock;
     ItalianAntiMoneyLaunderingPolicy antiMoneyLaunderingPolicyMock;
     BankAccount bankAccount;

    @BeforeEach
     void init(){
        antiMoneyLaunderingPolicyMock = mock(ItalianAntiMoneyLaunderingPolicy.class);
        bankTransactionLedgerMock = mock(ItalianBankTransactionLedger.class);
        bankAccount = new BankAccount("Luigi", 1000.0,
                bankTransactionLedgerMock,antiMoneyLaunderingPolicyMock);

        when(antiMoneyLaunderingPolicyMock.isLegalDeposit(MockitoHamcrest.doubleThat(lessThanOrEqualTo(5000.0))))
                 .thenReturn(true);
        when(antiMoneyLaunderingPolicyMock.isLegalDeposit(MockitoHamcrest.doubleThat(greaterThan(5000.0))))
                 .thenReturn(false);
        doNothing().when(bankTransactionLedgerMock)
                .write(ArgumentMatchers.any(BankAccount.class),anyString(),anyDouble());
    }

    @Test
    public void shouldDepositPositiveAmount(){
        bankAccount.deposit(1000.0);

        assertEquals(2000.0, bankAccount.getAmount(), 0.001);
    }

    @Test
    public void shouldNotExceedAntiMoneyLaunderingQuotas(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> bankAccount.deposit(100000.0));

        assertEquals("Illegal amount (see Anti-Money Laundering policies!)", e.getMessage());
    }

    @Test
    public void shouldNotAllowDepositsSmallerThanFiveEuros(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> bankAccount.deposit(5.0));
        assertEquals("Cannot deposit less than 5 €!", e.getMessage());
    }

    @Test
    public void shouldNotAllowWithdrawsBiggerThanAmount(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> bankAccount.withdraw(2000.0));

        assertEquals("Insufficient funds!",e.getMessage());
    }

    @Test
    public void shouldNotAllowWithdrawsNonPositiveAmount(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> bankAccount.withdraw(0));

        assertEquals("Cannot withdraw non positive amount!",e.getMessage());
    }

    @Test
    public void shouldProperlyUpdateAmountAfterWithdraws(){
        bankAccount.withdraw(500.0);

        assertEquals(500.0,bankAccount.getAmount(),00.1);
    }

    @Test
    public void constructorShouldProperlySetAmount(){
        assertEquals(1000.0, bankAccount.getAmount(),00.1);
    }

    @Test
    public void constructorShouldProperlySetHolder(){
        assertEquals("Luigi",bankAccount.getHolder());
    }

}

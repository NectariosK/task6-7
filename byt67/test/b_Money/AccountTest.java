package b_Money;

import org.junit.Before;


import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(10000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("1", 10, 10, new Money(100, SEK), SweBank, "Alice");
		testAccount.addTimedPayment("1", 10, 10, new Money(100, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("1"));
		testAccount.removeTimedPayment("1");
		assertFalse(testAccount.timedPaymentExists("1"));
		testAccount.removeTimedPayment("1");
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("1", 2, 0, new Money(10000, SEK), SweBank, "Alice");
        for (int i = 0; i < 10; i++) {
            testAccount.tick();
        }
        assertTrue(new Money(9960000, SEK).equals(testAccount.getBalance()));
	}
	//withdraw and deposit test
	@Test
	public void testAddWithdraw() {
		 testAccount.withdraw(new Money(5000000, SEK));
		 	assertTrue(new Money(5000000, SEK).equals(testAccount.getBalance()));
	     testAccount.withdraw(new Money(990000000, SEK));
	       assertFalse(new Money(5000000, SEK).equals(testAccount.getBalance()));
	}
	
	@Test
	public void testGetBalance() {
      assertTrue(new Money(10000000, SEK).equals(testAccount.getBalance()));
	}
}
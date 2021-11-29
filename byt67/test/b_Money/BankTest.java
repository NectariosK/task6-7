package b_Money;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("DanskeBank",DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals("DKK",DanskeBank.getCurrency().getName());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("Andrei");
		
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(70000000, SEK));
		SweBank.deposit("Bob", new Money(70000000, SEK));
		assertEquals(140000000,SweBank.getBalance("Bob"),0);
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.withdraw("Bob", new Money(700, SEK));
		assertEquals(-700, SweBank.getBalance("Bob"), 0);

	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(0,SweBank.getBalance("Bob"),0);
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(120, SEK));
		SweBank.transfer("Ulrika", "Bob", new Money(12, SEK));    	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Bob", "111", 4, 7, new Money(900, SEK), SweBank, "Ulrika"); 
		SweBank.tick(); 
		
		assertEquals(0, SweBank.getBalance("Bob"), 0);
		assertEquals(0, SweBank.getBalance("Ulrika"), 0);
	}
}
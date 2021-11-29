package b_Money;


import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	//Test methods that return amount
	@Test
	public void testGetAmount() {
		Assert.assertEquals(2000,EUR20.getAmount(),0);
	}
	
	@Test
	public void testGetCurrency() {
		Assert.assertEquals(EUR, EUR20.getCurrency());
	}
	
	@Test
	public void testToString() {
		
		Assert.assertEquals(" ", "0 EUR", EUR0.toString());
	}
	
	@Test
	public void testUniversalValue() {
		Assert.assertEquals((int)(1500),EUR10.universalValue(),0);
	}
	
	@Test
	public void testEqualsMoney() {
		Assert.assertTrue(EUR20.equals(SEK200));
	}

	@Test
	public void testAdd() {
		Money Add = EUR20.add(EUR20);
		int sum = (int)((2000) + (2000 * 1.5 / 1.5));
		Assert.assertEquals(sum, Add.getAmount(), 0);
	}
	@Test
	public void testSub() {
		Money subbed = EUR20.sub(EUR10);
		int res = (int) (2000 - 1000 * 1.5 / 1.5);
		Assert.assertEquals(res, subbed.getAmount(), 0);
	}

	@Test
	public void testIsZero() {
		Assert.assertTrue(EUR0.isZero());
	}

	@Test
	public void testNegate() {
		Assert.assertEquals(-1000, EUR10.negate().getAmount(), 0);
	}

	@Test
	public void testCompareTo() {
		Assert.assertEquals(-1, EUR10.compareTo(EUR20));
	}
}
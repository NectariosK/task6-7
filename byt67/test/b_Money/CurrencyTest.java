package b_Money;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}
	//Testing the methods which return the name of the currency
	@Test
	public void testGetName() {
		Assert.assertEquals("SEK", SEK.getName());
		Assert.assertEquals("DKK", DKK.getName());
		Assert.assertEquals("EUR", EUR.getName());
	}
	//rate of the currency returned
	@Test
	public void testGetRate() {
		Assert.assertEquals(0.15,SEK.getRate(),0);
		Assert.assertEquals(0.20,DKK.getRate(),0);
		Assert.assertEquals(1.5,EUR.getRate(),0);
	}
	//rate of currency with defined value being set
	@Test
	public void testSetRate() {
		SEK.setRate(0.69);
		Assert.assertEquals(0.69,SEK.getRate(),0);
		
		
	}
	//global value of the currency returned
	@Test
	public void testUniversalValue() {
		Assert.assertEquals(15,SEK.universalValue(100),0);
		Assert.assertEquals(20,DKK.universalValue(100),0);
		Assert.assertEquals(150,EUR.universalValue(100),0);

	}
	//currency conversion
	@Test
	public void testValueInThisCurrency() {
		assertEquals((int)(100 * 1.5 / 0.20), DKK.valueInThisCurrency(100, EUR), 0);


	}

}
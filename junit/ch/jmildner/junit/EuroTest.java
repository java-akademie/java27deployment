package ch.jmildner.junit;

import junit.framework.TestCase;

public class EuroTest extends TestCase
{


	@Override
	public void setUp()
	{
		System.out.println("setUp");
	}



	@Override
	public void tearDown()
	{
		System.out.println("tearDown\n");
	}



	public void testAdding()
	{
		Euro e150 = new Euro(1.50);
		Euro e220 = new Euro(2.20);
		System.out.println((e150.add(e220)).getAmount());
		double erg = 3.7;
		assertTrue("Ergebnis sollte " + erg + " sein",
				erg == (e150.add(e220)).getAmount());
	}


	public void testAmount()
	{
		Euro two = new Euro(2.0);
		System.out.println(two);
		assertTrue(2.00 == two.getAmount());
	}


	public void testMult()
	{
		Euro e120 = new Euro(12.0);
		double erg = 14400;
		double ist = (e120.mult(e120)).getAmount();
		assertTrue(
				"Ergebnis sollte " + erg + " sein" + " ist aber " + ist,
				erg == (e120.mult(e120)).getAmount());
	}


	public void testNew()
	{
		Euro euro = new Euro();
		System.out.println(euro);
		double erg = 0.0;
		assertTrue("Ergebnis sollte " + erg + " sein",
				erg == euro.getAmount());
	}


	public void testRounding()
	{
		Euro two = new Euro(1.997);
		assertTrue(2.00 == two.getAmount());
	}
}

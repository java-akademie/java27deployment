package ch.jmildner.junit;

import junit.framework.TestCase;

public class WaehrungJUnitTest extends TestCase
{


	@Override
	public void setUp()
	{
		// System.out.println("setUp");
	}



	@Override
	public void tearDown()
	{
		// System.out.println("tearDown\n");
	}



	public void test1()
	{
		Waehrung w = null;

		w = new WaehrungImpl();
		assertTrue("Waehrung darf nicht null sein", w != null);

		w = new WaehrungImpl(12.5);
		assertTrue("Waehrung darf nicht null sein", w != null);

		w = new WaehrungImpl(new WaehrungImpl(125.0));
		assertTrue("Waehrung darf nicht null sein", w != null);
	}


	public void testAmount1()
	{
		Waehrung w = null;
		double dbetrag = 0.0;
		double ebetrag = 0.0;

		dbetrag = 15.32111111111111;
		ebetrag = 15.32;
		w = new WaehrungImpl(dbetrag);
		assertTrue(
				"Betrag muss " + ebetrag + " und nicht "
						+ w.getAmount() + " sein!",
				w.getAmount() == ebetrag);
	}


	public void testAmount2()
	{
		Waehrung w = null;
		double dbetrag = 0.0;
		double ebetrag = 0.0;

		dbetrag = 15.32111111111111;
		ebetrag = 15.32;
		w = new WaehrungImpl(new WaehrungImpl(dbetrag));
		assertTrue(
				"Betrag muss " + ebetrag + " und nicht "
						+ w.getAmount() + " sein!",
				w.getAmount() == ebetrag);
	}


	public void testDiv()
	{
		double b1 = 144.55;
		double b2 = 12.33;
		double soll = 11.72;

		Waehrung w1 = new WaehrungImpl(b1);
		Waehrung w2 = new WaehrungImpl(b2);

		double erg = w1.div(w2);

		assertTrue(
				"Erg muss  " + soll + " und nicht " + erg + " sein!",
				erg == soll);
	}


	public void testRounding()
	{
		Waehrung two = new WaehrungImpl(1.997);
		assertTrue(2.00 == two.getAmount());
	}
}

package ch.jmildner.junit;

public class WaehrungImpl implements Waehrung
{
	private long cents;


	public WaehrungImpl()
	{
		cents = 0;
	}


	public WaehrungImpl(double d)
	{
		cents = Math.round(d * 100);
	}


	public WaehrungImpl(Waehrung waehrung)
	{
		cents = (long) (waehrung.getAmount() * 100.0);
	}


	@Override
	public Waehrung add(Waehrung e)
	{

		return null;
	}


	@Override
	public Waehrung div(double d) throws WaehrungException
	{
		long c = (long) (Math.round(d * 100));
		if (c == 0)
			throw new WaehrungException(
					"Division durch null nicht erlaubt");
		return null;
	}


	@Override
	public double div(Waehrung e)
	{
		long c = (long) (Math.round((e.getAmount() * 100)));

		if (c == 0)
			throw new WaehrungException(
					"Division durch null nicht erlaubt");

		double erg = (double) cents / (double) c;

		erg = Math.round(erg * 100);
		return erg / 100;
	}


	@Override
	public double getAmount()
	{
		return cents / 100.0;
	}


	@Override
	public Waehrung mult(Waehrung e)
	{

		return null;
	}


	@Override
	public Waehrung sub(Waehrung e)
	{

		return null;
	}
}

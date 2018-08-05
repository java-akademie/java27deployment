package ch.jmildner.junit;


public class Euro
{
	private final long cents;


	public Euro()
	{
		cents = 0;
	}


	public Euro(double euro)
	{
		this.cents = Math.round(euro * 100.0);
	}


	public Euro(long cents)
	{
		this.cents = cents;
	}


	public Euro add(Euro other)
	{
		return new Euro(this.cents + other.cents);
	}


	public double getAmount()
	{
		return cents / 100.0;
	}


	public Euro mult(Euro other)
	{
		return new Euro(this.cents * other.cents);
	}


	@Override
	public String toString()
	{
		return cents + "";
	}
}

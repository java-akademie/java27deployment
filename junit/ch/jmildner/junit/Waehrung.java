package ch.jmildner.junit;

public interface Waehrung
{
	Waehrung add(Waehrung e);


	// Waehrung();
	// Waehrung(long cents);
	// Waehrung(double Waehrung);


	Waehrung div(double d) throws WaehrungException;


	double div(Waehrung e) throws WaehrungException;


	double getAmount();


	Waehrung mult(Waehrung e);


	Waehrung sub(Waehrung e);


}

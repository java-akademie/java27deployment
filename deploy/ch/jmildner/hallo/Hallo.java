package ch.jmildner.hallo;

import ch.java_akademie.tools.MyTools;


public class Hallo
{
	public static void main(String[] args)
	{
		String name = MyTools.getString("bitte name eingeben > ");
		int a = MyTools.getInteger("bitte a eingeben > ");
		int b = MyTools.getInteger("bitte b eingeben > ");
		System.out.printf("hallo %s, %d : %d = %f  \n", name, a, b,
				Rechner.div(a, b));
	}
}

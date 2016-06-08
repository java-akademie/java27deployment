package ch.jmildner.doku;

import ch.java_akademie.tools.MyTools;

//import ch.jmildner.tools.MyTools;

/**
 * <b>Uebungsprojekt</b>
 * 
 * <br/>
 * Kapitel: Dokumentation <br/>
 * 
 * <hr/>
 * 
 * Diese Klasse dient einer Uebung zu <code>javadoc</code>
 * 
 * <hr/>
 * 
 * weitere Beschreibungen und Hinweise
 * 
 * <hr/>
 * Liste:
 * 
 * <br/>
 * 
 * hier kann eine Aufzahelung oder Liste kommen
 * 
 * Die Packagedokumentation/Classsummary endet mit einem Punkt- nach
 * diesem Punkt sollte aber ein &lt;p&gt; stehen.
 * <p>
 * 
 * Alles was nach dem Punkt kommt, wird dann in der Classuebersicht
 * ausgegeben. Weitere Punkte sind dann nicht mehr schaedlich.
 * 
 * <pre>
 * 
 *                   Einrueckung
 *                     x1=xx
 *                     x2=yy
 *                     x3=zz
 * 
 * </pre>
 * 
 * @author Johann Mildner, Basel
 * @version 1.0, 07.01.2004
 */
public class DokuTest
{
	/**
	 * die main-Methode dient lediglich waehrend der Entwicklungsphase
	 * dem Testen dieser Klasse
	 * <hr>
	 * das Arugment (String[] args) wird nicht benutzt
	 * 
	 * @param args
	 *            wird nicht benutzt
	 */
	public static void main(String[] args)
	{
		new DokuTest();
		MyTools.pause();
	}

	/**
	 * 
	 * hier kommen die Membervariablen
	 * 
	 */
	int xxx;

	int yyy;

	int zzz;


	/**
	 * dieser Konstruktor steuert den Programmablauf auch hier gilt die
	 * Regel dass der Text bis zum Punkt in der Uebersicht ausgegeben
	 * wird; der Text nach dem Punkt kommt dann in der
	 * Detailbeschreibung &lt;p /&gt; sollte dem Punkt folgen.
	 * <p />
	 * das Programm ist als herkoemmliches Konsolprogramm zu sehen
	 * <p />
	 * die Programmsteuerung beinhaltet:
	 * 
	 * <pre>
	 * super();
	 * 
	 * vorlauf();
	 * hptvera();
	 * nchlauf();
	 * </pre>
	 */
	public DokuTest()
	{
		super();

		vorlauf();
		hauptvera();
		nachlauf();
	}


	/**
	 * @param a
	 *            Summand 1
	 * @param b
	 *            Summand 2
	 * 
	 * @return - das Ergebnis der Addition a + b
	 */
	public int add(int a, int b)
	{
		return a + b;
	}


	/**
	 * hauptverarbeitung
	 */
	public void hauptvera()
	{
		System.out.println("hauptvera");
	}


	/**
	 * nachlauf
	 */
	public void nachlauf()
	{
		System.out.println("nachlauf");
	}


	/**
	 * vorlauf
	 */
	public void vorlauf()
	{
		System.out.println("vorlauf");
	}
}

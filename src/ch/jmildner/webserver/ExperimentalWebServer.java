package ch.jmildner.webserver;

/* ExperimentalWebServer.java */
// aus ws1
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Die Thread-Klasse fuer die Client-Verbindung.
 */
class BrowserClientThread extends Thread
{
	static final String[][] mimetypes = { { "html", "text/html" },
			{ "htm", "text/html" }, { "txt", "text/plain" },
			{ "gif", "image/gif" }, { "jpg", "image/jpeg" },
			{ "jpeg", "image/jpeg" },
			{ "jnlp", "application/x-java-jnlp-file" } };

	private Socket socket;
	private int id;
	private PrintStream out;
	private InputStream in;
	private String cmd;
	private String url;
	private String httpversion;


	/**
	 * Erzeugt einen neuen Client-Thread mit der angegebenen id und dem
	 * angegebenen Socket.
	 */
	public BrowserClientThread(int id, Socket socket)
	{
		this.id = id;
		this.socket = socket;
	}


	/**
	 * Request bearbeiten und Antwort erzeugen.
	 */
	private void createResponse()
	{
		if (cmd.equals("GET") || cmd.equals("HEAD"))
		{
			if (!url.startsWith("/"))
			{
				httpError(400, "Bad Request");
			}
			else
			{
				// MIME-Typ aus Dateierweiterung bestimmen
				String mimestring = "application/octet-stream";
				for (int i = 0; i < mimetypes.length; ++i)
				{
					if (url.endsWith(mimetypes[i][0]))
					{
						mimestring = mimetypes[i][1];
						break;
					}
				}
				// URL in lokalen Dateinamen konvertieren
				String fsep = System.getProperty("file.separator", "/");
				StringBuffer sb = new StringBuffer(url.length());
				for (int i = 1; i < url.length(); ++i)
				{
					char c = url.charAt(i);
					if (c == '/')
					{
						sb.append(fsep);
					}
					else
					{
						sb.append(c);
					}
				}
				try
				{
					System.out.println(sb + " .....................");
					FileInputStream is = new FileInputStream(
							sb.toString());

					// HTTP-Header senden
					out.print("HTTP/1.0 200 OK\r\n");
					System.out.println("> HTTP/1.0 200 OK");
					out.print("Server: ExperimentalWebServer 0.5\r\n");
					System.out
							.println("> Server: ExperimentalWebServer 0.5");
					out.print("Content-type: " + mimestring
							+ "\r\n\r\n");
					System.out.println("> Content-type: " + mimestring);
					if (cmd.equals("GET"))
					{
						// Dateiinhalt senden
						byte[] buf = new byte[256];
						int len;
						while ((len = is.read(buf)) != -1)
						{
							out.write(buf, 0, len);
						}
					}
					is.close();
				}
				catch (FileNotFoundException e)
				{
					httpError(404, "Error Reading File");
				}
				catch (IOException e)
				{
					httpError(404, "Not Found");
				}
				catch (Exception e)
				{
					httpError(404, "Unknown exception");
				}
			}
		}
		else
		{
			httpError(501, "Not implemented");
		}
	}


	/**
	 * Eine Fehlerseite an den Browser senden.
	 */
	private void httpError(int code, String description)
	{
		System.out.println("> ***" + code + ": " + description + "***");
		out.print("HTTP/1.0 " + code + " " + description + "\r\n");
		out.print("Content-type: text/html\r\n\r\n");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>ExperimentalWebServer-Error</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>HTTP/1.0 " + code + "</h1>");
		out.println("<h3>" + description + "</h3>");
		out.println("</body>");
		out.println("</html>");
	}


	/**
	 * Liest den naechsten HTTP-Request vom Browser ein.
	 */
	private void readRequest() throws IOException
	{
		// Request-Zeilen lesen
		Vector<StringBuffer> request = new Vector<StringBuffer>(10);
		StringBuffer sb = new StringBuffer(100);
		int c;
		while ((c = in.read()) != -1)
		{
			if (c == '\r')
			{
				// ignore
			}
			else
				if (c == '\n')
				{ // line terminator
					if (sb.length() <= 0)
					{
						break;
					}
					else
					{
						request.addElement(sb);
						sb = new StringBuffer(100);
					}
				}
				else
				{
					sb.append((char) c);
				}
		}
		// Request-Zeilen auf der Konsole ausgeben
		Enumeration<StringBuffer> e = request.elements();
		while (e.hasMoreElements())
		{
			sb = e.nextElement();
			System.out.println("< " + sb.toString());
		}
		// Kommando, URL und HTTP-Version extrahieren
		String s = request.elementAt(0).toString();
		cmd = "";
		url = "";
		httpversion = "";
		int pos = s.indexOf(' ');
		if (pos != -1)
		{
			cmd = s.substring(0, pos).toUpperCase();
			s = s.substring(pos + 1);
			// URL
			pos = s.indexOf(' ');
			if (pos != -1)
			{
				url = s.substring(0, pos);
				s = s.substring(pos + 1);
				// HTTP-Version
				pos = s.indexOf('\r');
				if (pos != -1)
				{
					httpversion = s.substring(0, pos);
					System.out.println("................... "
							+ httpversion);
				}
				else
				{
					httpversion = s;
					System.out.println("................... "
							+ httpversion);
				}
			}
			else
			{
				url = s;
			}
		}
	}


	/**
	 * Hauptschleife fuer den Thread.
	 */
	@Override
	public void run()
	{
		try
		{
			System.out.println(id + ": Incoming call...");
			out = new PrintStream(socket.getOutputStream());
			in = socket.getInputStream();
			readRequest();
			createResponse();
			socket.close();
			System.out.println(id + ": Closed.");
		}
		catch (IOException e)
		{
			System.out.println(id + ": " + e.toString());
			System.out.println(id + ": Aborted.");
		}
	}
}



/**
 * <code>ExperimentalWebSertver</code>
 * 
 * Ein ganz einfacher Web-Server auf TCP und einem beliebigen Port. Der
 * Server ist in der Lage, Seitenanforderungen lokal zu dem Verzeichnis,
 * aus dem er gestartet wurde, zu bearbeiten. Wurde der Server z.B. im
 * Verzeichnis c:\tmp gestartet, so wuerde eine Seitenanforderung
 * http://localhost:80/test/index.html die Datei c:\tmp\test\index.html
 * laden. CGIs, SSIs, Servlets oder aehnliches wird nicht unterstuetzt.
 * <p>
 * Die Dateitypen .htm, .html, .gif, .jpg und .jpeg .jnlp werden erkannt
 * und mit korrekten MIME-Headern uebertragen, alle anderen Dateien
 * werden als "application/octet-stream" uebertragen. Jeder Request wird
 * durch einen eigenen Client-Thread bearbeitet, nach uebertragung der
 * Antwort schliesst der Server den Socket. Antworten werden mit
 * HTTP/1.0-Header gesendet.
 * 
 * aus Handbuch der Java-Programmierung von Guido Krueger
 * 
 * die Motivation fuer dieses Programm war es, eigene Klassem mittels
 * Webstart zu startet.
 */
public class ExperimentalWebServer
{
	public static void main(String[] args) throws Exception
	{

		int port = 8080;
		System.out.println("Listening to port " + port);
		int calls = 0;
	
		@SuppressWarnings("resource")
		ServerSocket httpd = new ServerSocket(port);
		while (true)
		{
			Socket socket = httpd.accept();
			System.out.println("nach accept ........");
			(new BrowserClientThread(++calls, socket)).start();
		}

	}
}

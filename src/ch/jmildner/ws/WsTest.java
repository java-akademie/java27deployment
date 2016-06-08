package ch.jmildner.ws;
//aus ws2
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WsTest extends Frame
{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args)
	{
		new WsTest();
	}
	private Button b = new Button("klickMich");
	private TextField tf1 = new TextField("", 30);


	private TextField tf2 = new TextField("", 30);


	public WsTest()
	{
		super("KlickMich");
		makeTheLayout();
		addTheListener();
		anzeigen();
	}


	private void addTheListener()
	{
		b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (tf1.getText().equals(""))
				{
					tf1.setText("Danke dass Du mich geklickt hast");
					tf2.setText("");
				}
				else
				{
					tf2.setText("Danke dass Du mich geklickt hast");
					tf1.setText("");
				}
			}
		});
	}


	private void anzeigen()
	{
		setLocation(200, 100);
		pack();
		setVisible(true);

		/*
		 * der folgende Block ist noetig um das Programm durch anklicken
		 * des X in der linken oberen Ecke beenden zu koennen
		 */
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});
	}


	private void makeTheLayout()
	{
		setLayout(new FlowLayout());

		add(tf1);
		add(b);
		add(tf2);
	}
}

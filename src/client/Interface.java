package client;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Interface extends JFrame  {
	private static final long serialVersionUID = 1L;
	public static JPanel p;

	public Interface(){
		setResizable(false);
		
		p = new JPanel();
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(p);
		p.setLayout(null);
	}
}

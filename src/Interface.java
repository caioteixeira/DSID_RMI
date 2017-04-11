import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Interface extends JFrame  {
	private static final long serialVersionUID = 1L;
	public JPanel p;

	public Interface(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 380); // setBounds(x, y, largura, altura)
		setResizable(false);
		
		p = new JPanel();
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(p);
		p.setLayout(null);
	}
}

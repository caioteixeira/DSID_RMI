package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SubPart extends Interface {
	private static final long serialVersionUID = 1L;

	public SubPart(String host, int cod) {
		addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	Interface conn = new Connect(host);
				conn.setVisible(true);
				dispose();
		    }
		});
		setBounds(100, 100, 420, 190); // setBounds(x, y, largura, altura)
		setTitle(host);
		
		JLabel lqtd = new JLabel("Quantas peças serão adicionadas?");
		lqtd.setFont(new Font("Tahoma", Font.BOLD, 16));
		lqtd.setBounds(65, 0, 380, 40);
		lqtd.setVisible(true);
		p.add(lqtd);
		
		JLabel lqtd2 = new JLabel("Quantidade: ");
		lqtd2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lqtd2.setBounds(15, 50, 380, 40);
		lqtd2.setVisible(true);
		p.add(lqtd2);
		
		JTextField qtd = new JTextField();
		qtd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		qtd.setBackground(Color.WHITE);
		qtd.setBounds(110, 55, 50, 30); // setBounds(x, y, width, height)
		p.add(qtd);
		
		JButton addSubPart = new JButton("Adicionar Subpeça");
		addSubPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					MainClient.subParts.put(cod, Integer.parseInt(qtd.getText()));
					Interface conn = new Connect(host);
					conn.setVisible(true);
					dispose();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Quantidade inválida!" ,"Erro",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		addSubPart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		addSubPart.setBounds(15, 110, 150, 30);
		p.add(addSubPart);
	}
}

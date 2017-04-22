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

import server.IPart;

public class DetailsPart extends Details {
	private static final long serialVersionUID = 1L;
	
	public DetailsPart(String host, IPart part){
		super(part);
		addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	Interface conn = new Connect(host);
				conn.setVisible(true);
				dispose();
		    }
		});
		
		JButton createPart = new JButton("Adicionar como Sub-peça");
		createPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SubPart sp = new SubPart(host, part);
				sp.setVisible(true);
				dispose();
			}
		});
		
		createPart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		createPart.setBounds(15, 235, 200, 30);
		p.add(createPart);
		
		JButton det = new JButton("Detalhes");
		det.setFont(new Font("Tahoma", Font.PLAIN, 12));
		det.setBounds(315, 235, 225, 30);
		det.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(currentSubPart != null){
						Interface detp = new DetailsSubPart(currentSubPart);
						detp.setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "Selecione uma Sub-peça." ,"Erro",JOptionPane.INFORMATION_MESSAGE);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		p.add(det);
	}
}

class SubPart extends Interface {
	private static final long serialVersionUID = 1L;

	public SubPart(String host, IPart part) {
		addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	Interface det = new DetailsPart(host, part);
				det.setVisible(true);
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
		
		JButton addSubPart = new JButton("Adicionar Sub-peça");
		addSubPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					MainClient.subParts.put(part, Integer.parseInt(qtd.getText()));
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
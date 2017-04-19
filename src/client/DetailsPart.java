package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import server.IPart;

public class DetailsPart extends Interface {
	private static final long serialVersionUID = 1L;
	public DetailsPart(String host, IPart part){
		setBounds(100, 100, 550, 305); // setBounds(x, y, largura, altura)
		setTitle("Servidor: " + host);
		
		JLabel lcode = new JLabel("Código: ");
		lcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lcode.setBounds(15, 13, 300, 40);
		lcode.setVisible(true);
		p.add(lcode);
		
		JTextField code = new JTextField();
		code.setFont(new Font("Tahoma", Font.PLAIN, 14));
		code.setEditable(false);
		code.setBackground(Color.WHITE);
		code.setBorder(null);
		JScrollPane paneCode = new JScrollPane(code);
		paneCode.setBounds(100, 20, 200, 30); // setBounds(x, y, width, height)
		p.add(paneCode);
		
		JLabel lname = new JLabel("Nome: ");
		lname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lname.setBounds(15, 63, 300, 40);
		lname.setVisible(true);
		p.add(lname);
		
		JTextField name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		name.setEditable(false);
		name.setBackground(Color.WHITE);
		name.setBorder(null);
		JScrollPane paneName = new JScrollPane(name);
		paneName.setBounds(100, 70, 200, 30); // setBounds(x, y, width, height)
		p.add(paneName);
		
		JLabel ldesc = new JLabel("Descrição: ");
		ldesc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ldesc.setBounds(15, 113, 300, 40);
		ldesc.setVisible(true);
		p.add(ldesc);
		
		JTextArea desc = new JTextArea();
		desc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		desc.setColumns(20);
		desc.setEditable(false);
		desc.setLineWrap(true);
		
		JScrollPane paneDesc = new JScrollPane(desc);
		paneDesc.setBounds(100, 120, 200, 100);
		p.add(paneDesc);
		
		JButton createPart = new JButton("Adicionar como Subpeça");
		createPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		createPart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		createPart.setBounds(15, 235, 200, 30);
		p.add(createPart);
		
		JLabel titleSubParts = new JLabel("SUBPEÇAS");
		titleSubParts.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleSubParts.setBounds(395, 0, 300, 40);
		titleSubParts.setVisible(true);
		p.add(titleSubParts);
		
		String[][] data = new String[1][2];
		String[] cols = {"NOME","QTD"};
		
		JTable table = new JTable(data, cols){
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int col){ 
				return false; 
			} 
		};
		
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		table.setBackground(Color.WHITE);
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		
		JScrollPane tab = new JScrollPane();
		tab.setVisible(true);
		tab = new JScrollPane(table);
		tab.setBounds(315, 35, 225, 135);
		p.add(tab);
	}
}

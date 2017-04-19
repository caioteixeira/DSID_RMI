package client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewPart extends Interface {
	private static final long serialVersionUID = 1L;
	public NewPart(String host, ArrayList<String> subParts){
		setBounds(100, 100, 510, 250); // setBounds(x, y, largura, altura)
		setTitle("Servidor: " + host);
		
		JLabel lname = new JLabel("Nome: ");
		lname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lname.setBounds(15, 13, 300, 40);
		lname.setVisible(true);
		p.add(lname);
		
		JTextField name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		name.setBounds(100, 20, 200, 30); // setBounds(x, y, width, height)
		p.add(name);
		
		JLabel ldesc = new JLabel("Descrição: ");
		ldesc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ldesc.setBounds(15, 63, 300, 40);
		ldesc.setVisible(true);
		p.add(ldesc);
		
		JTextArea desc = new JTextArea();
		desc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		desc.setColumns(20);
		desc.setLineWrap(true);
		
		JScrollPane paneDesc = new JScrollPane(desc);
		paneDesc.setBounds(100, 70, 200, 100);
		p.add(paneDesc);
		
		JButton createPart = new JButton("Criar Peça");
		createPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		createPart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		createPart.setBounds(15, 180, 100, 30);
		p.add(createPart);
		
		JLabel titleSubParts = new JLabel("SUBPEÇAS ATUAIS");
		titleSubParts.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleSubParts.setBounds(345, 0, 300, 40);
		titleSubParts.setVisible(true);
		p.add(titleSubParts);
		
		JList<Object> listSubParts = new JList<Object>(subParts.toArray());
		listSubParts.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listSubParts.setLayoutOrientation(JList.VERTICAL);
		
		JScrollPane paneList = new JScrollPane(listSubParts);
		paneList.setBounds(315, 35, 180, 135);
		p.add(paneList);
		
		JButton clearList = new JButton("Apagar Subpeças");
		clearList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		clearList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		clearList.setBounds(315, 180, 180, 30);
		p.add(clearList);
	}
}

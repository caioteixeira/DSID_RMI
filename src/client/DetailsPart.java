package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Dictionary;
import java.util.Enumeration;

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
	private String[][] data = null;
	private String[] cols = {"NOME","QTD"};
	
	public DetailsPart(String host, IPart part){
		addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	Interface conn = new Connect(host);
				conn.setVisible(true);
				dispose();
		    }
		});
		
		setBounds(100, 100, 550, 305); // setBounds(x, y, largura, altura)
		setTitle("Servidor: " + host);
		
		fillTable(part.getListSubParts());
		
		JLabel lcode = new JLabel("Código: ");
		lcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lcode.setBounds(15, 13, 300, 40);
		lcode.setVisible(true);
		p.add(lcode);
		
		JTextField code = new JTextField(Integer.toString(part.getCod()));
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
		
		JTextField name = new JTextField(part.getName());
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
		
		JTextArea desc = new JTextArea(part.getDesc());
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
				SubPart sp = new SubPart(host, part.getCod());
				sp.setVisible(true);
				dispose();
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
		tab.setBounds(315, 35, 225, 185);
		p.add(tab);
	}
	
	public void fillTable(Dictionary<Integer, Integer> parts){
		if(parts == null || parts.isEmpty()){
			data = new String[0][0];
			return;
		}
		data = new String[parts.size()][2];
		int i = 0;
		Enumeration<Integer> keys = parts.keys();
		while(keys.hasMoreElements()){
			int cod = keys.nextElement();
			data[i][0] = Connect.getPart(cod).getName();
			data[i][1] = parts.get(cod).toString();
			i++;
		}
	}
}

package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import server.IPart;
import server.IPartRepository;

public class DetailsSubPart extends Interface {
	private static final long serialVersionUID = 1L;
	private String[][] data = null;
	private String[] cols = {"NOME","QTD", "COD"};
	private IPart currentPart = null;
	
	public DetailsSubPart(String host, IPart part, IPartRepository pr){
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
				SsubPart sp = new SsubPart(host, part);
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
		
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		table.setBackground(Color.WHITE);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	try{
	        		currentPart = pr.getPart(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 2).toString()));
	        	}catch(Exception e){
	        		e.printStackTrace();
	        	}
	        }
	    });
		table.removeColumn(table.getColumnModel().getColumn(2));
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		JScrollPane  tab = new JScrollPane();
		tab.setVisible(true);
		tab = new JScrollPane(table);
		tab.setBounds(315, 35, 225, 185);
		p.add(tab);
		
		JButton det = new JButton("Detalhes");
		det.setFont(new Font("Tahoma", Font.PLAIN, 12));
		det.setBounds(315, 235, 225, 30);
		det.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(currentPart != null){
						Interface detp = new DetailsSubPart(host, currentPart, pr);
						detp.setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "Selecione uma Subpeça." ,"Erro",JOptionPane.INFORMATION_MESSAGE);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		p.add(det);
	}
	
	public void fillTable(Dictionary<IPart, Integer> parts){
		if(parts == null || parts.isEmpty()){
			data = new String[0][0];
			return;
		}
		data = new String[parts.size()][3];
		int i = 0;
		Enumeration<IPart> eParts = parts.keys();
		while(eParts.hasMoreElements()){
			IPart p = eParts.nextElement();
			data[i][0] = p.getName();
			data[i][1] = parts.get(p).toString();
			data[i][2] = Integer.toString(p.getCod());
			i++;
		}
	}
}

class SsubPart extends Interface {
	private static final long serialVersionUID = 1L;

	public SsubPart(String host, IPart part) {
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
					MainClient.subParts.put(part, Integer.parseInt(qtd.getText()));
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

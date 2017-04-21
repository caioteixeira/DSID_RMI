package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import server.IPart;
import server.IPartRepository;

public class NewPart extends Interface {
	private static final long serialVersionUID = 1L;
	private static String[][] datasp;
	public NewPart(String host, IPartRepository pr){
		addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	Interface conn = new Connect(host);
				conn.setVisible(true);
				dispose();
		    }
		});
		setBounds(100, 100, 510, 250); // setBounds(x, y, largura, altura)
		setTitle("Servidor: " + host);
		fillTableSubPart();
		
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
				try{
					if(name.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Preencha o nome." ,"Erro",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					pr.insertPart(name.getText(), desc.getText(), MainClient.subParts);
					MainClient.subParts.clear();
					Interface conn = new Connect(host);
					conn.setVisible(true);
					dispose();
				}catch(Exception e){
					e.printStackTrace();
				}
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
		
		String[] subCols = new String[] {"Nome","QTD"}; 
		JTable listSubParts = new JTable(datasp, subCols){
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int col){ 
				return false; 
			} 
		};
		
		listSubParts.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listSubParts.setBorder(new LineBorder(Color.LIGHT_GRAY));
		listSubParts.setBackground(Color.WHITE);
		listSubParts.getColumnModel().getColumn(1).setPreferredWidth(15);
		
		JScrollPane paneList = new JScrollPane(listSubParts);
		paneList.setBounds(315, 35, 180, 135);
		p.add(paneList);
		
		JButton clearList = new JButton("Apagar Subpeças");
		clearList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainClient.subParts.clear();
				Interface np = new NewPart(host, pr);
				np.setVisible(true);
				dispose();
			}
		});
		
		clearList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		clearList.setBounds(315, 180, 180, 30);
		p.add(clearList);
	}
	
	private static void fillTableSubPart(){
		datasp = new String[MainClient.subParts.size()][2];
		int i = 0;
		Enumeration<IPart> keys = MainClient.subParts.keys();
		while(keys.hasMoreElements()){
			IPart p = keys.nextElement();
			datasp[i][0] = p.getName();
			datasp[i][1] = MainClient.subParts.get(p).toString();
			i++;
		}
	}
}

package client;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Home extends Interface {
	private static final long serialVersionUID = 1L;
	private static JScrollPane tab;
	private static String[][] data = new String[13][1];
	private String[] cols = {"SERVIDOR"};
	private String select = null;
	
	public Home(){
		setBounds(100, 100, 350, 380); // setBounds(x, y, largura, altura)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextField ip = new JTextField("Digite o IP do Registry");
		ip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ip.setBounds(25, 20, 150, 30); // setBounds(x, y, largura, altura)
		ip.setForeground(Color.GRAY);
		ip.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (ip.getText().equals("Digite o IP do Registry")) {
		        	ip.setText("");
		        	ip.setForeground(Color.BLACK);
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (ip.getText().isEmpty()) {
		        	ip.setForeground(Color.GRAY);
		        	ip.setText("Digite o NOME ou IP");
		        }
		    }
		    });
		p.add(ip);
		
		JButton add = new JButton("Acessar Registry");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String serv = ip.getText();
				if(CheckServer.isServer(serv)){
					insertRegistry(serv);
				}else{
					JOptionPane.showMessageDialog(null, "Registry não encontrado!" ,"Erro",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		add.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add.setBounds(180, 20, 135, 30);

		SwingUtilities.invokeLater(new Runnable(){public void run(){add.requestFocus();}});
		p.add(add);
		
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
	        	if(table.getValueAt(table.getSelectedRow(), 0) != null){
		        	select = table.getValueAt(table.getSelectedRow(), 0).toString();
	        	}
	        }
	    });
		
		tab = new JScrollPane(table);
		tab.setBounds(5, 70, 335, 231);
		p.add(tab);
		
		JButton con = new JButton("Acessar Repositório");
		con.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(select == null || select.equals("")){
					JOptionPane.showMessageDialog(null, "Selecione um repositório válido!" ,"Erro",JOptionPane.INFORMATION_MESSAGE);
				}else{
					Connect con = new Connect(select);
					con.setVisible(true);
					dispose();
				}
			}
		});
		
		con.setFont(new Font("Tahoma", Font.PLAIN, 12));
		con.setBounds(10, 310, 150, 30);
		p.add(con);
	}
	
	public static void insertRegistry(String host){
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(host);
			String[] names = registry.list();
			MainClient.registry = registry;
			
			MainClient.servs.clear();
			
			for(int i = 0; i < names.length; i++)
			{
				String name = names[i];
				
				MainClient.servs.add(name);
			}
			
			updateTable();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateTable(){
		String[] temp = new String[MainClient.servs.size()];
		MainClient.servs.toArray(temp);
		if(temp.length > 13){
			data = new String[temp.length][1];
		}
		for (int i = 0; i < temp.length; i++) {
			data[i][0] = temp[i];
		}
		tab.repaint();
	}
}

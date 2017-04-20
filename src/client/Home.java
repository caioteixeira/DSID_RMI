package client;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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
		
		JTextField ip = new JTextField("Digite o NOME ou IP");
		ip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ip.setBounds(25, 20, 150, 30); // setBounds(x, y, largura, altura)
		ip.setForeground(Color.GRAY);
		ip.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (ip.getText().equals("Digite o NOME ou IP")) {
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
		
		JButton add = new JButton("Adicionar Servidor");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String serv = ip.getText();
				if(CheckServer.isServer(serv)){
					insertServ(serv);
				}else{
					JOptionPane.showMessageDialog(null, "Servidor inválido!" ,"Erro",JOptionPane.INFORMATION_MESSAGE);
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
		
		JButton con = new JButton("Acessar Servidor");
		con.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(select == null || select.equals("")){
					JOptionPane.showMessageDialog(null, "Selecione um servidor válido!" ,"Erro",JOptionPane.INFORMATION_MESSAGE);
				}else{
					Connect con = new Connect(select);
					con.setVisible(true);
					dispose();
				}
			}
		});
		
		con.setFont(new Font("Tahoma", Font.PLAIN, 12));
		con.setBounds(10, 310, 135, 30);
		p.add(con);
	}
	
	public static void insertServ(String nome){
		if(MainClient.servs.contains(nome.toUpperCase())) return;
		MainClient.servs.add(nome.toUpperCase());
		updateTable();
	}
	
	public static void insertServ(String nome, String ip){
		if(MainClient.servs.contains(nome.toUpperCase())) return;
		if(MainClient.servs.contains(ip.toUpperCase())) return;
		MainClient.servs.add(nome.toUpperCase());
		updateTable();
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

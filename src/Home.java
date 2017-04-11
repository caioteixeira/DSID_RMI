import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
	private JLabel load;
	private static JScrollPane tab;
	private boolean lock = false;
	private static ArrayList<String> servs = new ArrayList<String>();
	private SearchServers servers;
	private static String[][] dados = new String[13][1];
	private String[] colunas = {"SERVIDOR"};
	private static String OS = System.getProperty("os.name").toLowerCase();
	private String select = null;
	
	public Home(){
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
				if(SearchServers.isServer(serv)){
					insereServ(serv);
					lock = false;
				}else{
					JOptionPane.showMessageDialog(null, "Servidor inv�lido!" ,"Erro",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		add.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add.setBounds(180, 20, 135, 30);

		SwingUtilities.invokeLater(new Runnable(){public void run(){add.requestFocus();}});
		p.add(add);
		
		JTable table = new JTable(dados, colunas){
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
		
		tab = new JScrollPane();
		tab.setVisible(true);
		tab = new JScrollPane(table);
		tab.setBounds(5, 70, 335, 231);
		p.add(tab);
		
		JButton con = new JButton("Acessar Servidor");
		con.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(select == null || select.equals("")){
					JOptionPane.showMessageDialog(null, "Selecione um servidor v�lido!" ,"Erro",JOptionPane.INFORMATION_MESSAGE);
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
		
		if(OS.indexOf("win") >= 0){
			JButton busca = new JButton("Procurar na LAN");
			busca.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!lock){
						load.setVisible(true);
						busca.setText("Parar");
						servers = new SearchServers();
						Thread thSearch = new Thread(servers);
						thSearch.start();
						lock = true;
					}else{
						if(servers != null) servers.stop();
						load.setVisible(false);
						busca.setText("Procurar na LAN");
						lock = false;
					}
				}
			});
			
			busca.setFont(new Font("Tahoma", Font.PLAIN, 12));
			busca.setBounds(160, 310, 130, 30);
			p.add(busca);

			try{
				load = new JLabel(new ImageIcon(getClass().getResource("/load-30.gif")));
				load.setBounds(300, 310, 30, 30);
				load.setVisible(false);
				p.add(load);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static void insereServ(String nome){
		if(servs.contains(nome.toUpperCase())) return;
		servs.add(nome.toUpperCase());
		updateTable();
	}
	
	public static void insereServ(String nome, String ip){
		if(servs.contains(nome.toUpperCase())) return;
		if(servs.contains(ip.toUpperCase())) return;
		servs.add(nome.toUpperCase());
		updateTable();
	}
	
	public static void updateTable(){
		String[] temp = new String[servs.size()];
		servs.toArray(temp);
		if(temp.length > 13){
			dados = new String[temp.length][1];
		}
		for (int i = 0; i < temp.length; i++) {
			dados[i][0] = temp[i];
		}
		tab.repaint();
	}
}

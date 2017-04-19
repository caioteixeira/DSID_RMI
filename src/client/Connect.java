package client;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import server.IPartRepository;

public class Connect extends Interface{
	private static final long serialVersionUID = 1L;
	private static JScrollPane tab;
	private static String[][] data = new String[13][2];
	private String[] cols = {"NOME","DESCRIÇÃO"};
	
	public Connect(String host, ArrayList<String> subParts){
		try{
			addWindowListener(new WindowAdapter()
			{
			    public void windowClosing(WindowEvent e)
			    {
			    	Interface home = new Home();
					home.setVisible(true);
					dispose();
			    }
			});
			
			Registry registry = LocateRegistry.getRegistry(host);
			IPartRepository pr = (IPartRepository) registry.lookup("IPartRepository");
			
			setBounds(100, 100, 550, 380);
			setTitle("Servidor: " + host);
			
			JTextField search = new JTextField("Digite o código");
			search.setFont(new Font("Tahoma", Font.PLAIN, 14));
			search.setBounds(25, 20, 150, 30); // setBounds(x, y, width, height)
			search.setForeground(Color.GRAY);
			search.addFocusListener(new FocusListener() {
			    @Override
			    public void focusGained(FocusEvent e) {
			        if (search.getText().equals("Digite o código")) {
			        	search.setText("");
			        	search.setForeground(Color.BLACK);
			        }
			    }
			    @Override
			    public void focusLost(FocusEvent e) {
			        if (search.getText().isEmpty()) {
			        	search.setForeground(Color.GRAY);
			        	search.setText("Digite o código");
			        }
			    }
			    });
			p.add(search);
			
			JButton srcButton = new JButton("Buscar Peça");
			srcButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
				}
			});
			
			srcButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			srcButton.setBounds(180, 20, 135, 30);

			SwingUtilities.invokeLater(new Runnable(){public void run(){srcButton.requestFocus();}});
			p.add(srcButton);
			
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
		        	
		        }
		    });
			
			tab = new JScrollPane();
			tab.setVisible(true);
			tab = new JScrollPane(table);
			tab.setBounds(5, 70, 335, 231);
			p.add(tab);
			

			JButton det = new JButton("Detalhes");
			det.setFont(new Font("Tahoma", Font.PLAIN, 12));
			det.setBounds(10, 310, 125, 30);
			det.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try{
					Interface newp = new DetailsPart(host, pr.getPart(1));
					newp.setVisible(true);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			});
			p.add(det);
			
			JButton newPart = new JButton("Nova Peça");
			newPart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Interface newp = new NewPart(host, subParts);
					newp.setVisible(true);
				}
			});
			
			newPart.setFont(new Font("Tahoma", Font.PLAIN, 12));
			newPart.setBounds(160, 310, 130, 30);
			p.add(newPart);
			
			JLabel titleSubParts = new JLabel("SUBPEÇAS ATUAIS");
			titleSubParts.setFont(new Font("Tahoma", Font.BOLD, 12));
			titleSubParts.setBounds(390, 25, 300, 40);
			titleSubParts.setVisible(true);
			p.add(titleSubParts);
			
			JList<Object> listSubParts = new JList<Object>(subParts.toArray());
			listSubParts.setFont(new Font("Tahoma", Font.PLAIN, 12));
			listSubParts.setLayoutOrientation(JList.VERTICAL);
			
			JScrollPane paneList = new JScrollPane(listSubParts);
			paneList.setBounds(360, 70, 170, 231);
			p.add(paneList);
			
			JButton clearList = new JButton("Apagar Subpeças");
			clearList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
				}
			});
			
			clearList.setFont(new Font("Tahoma", Font.PLAIN, 12));
			clearList.setBounds(360, 310, 170, 30);
			p.add(clearList);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

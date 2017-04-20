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
import java.util.Enumeration;

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

import server.IPart;
import server.IPartRepository;

public class Connect extends Interface{
	private static final long serialVersionUID = 1L;
	private static JScrollPane tab;
	private static String[][] datap = null;
	private static String[][] datasp = null;
	private String[] cols = {"NOME","DESCRIÇÃO", "CÓDIGO"};
	private IPart currentPart = null;
	private static int i = 0;
	private static JScrollPane paneList;
	
	public Connect(String host){
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
			fillTablePart(pr.getAllParts());
			fillTableSubPart();
			
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
					try{
						IPart part = pr.getPart(Integer.parseInt(search.getText()));
						if(part != null){
							Interface psearch = new DetailsPart(host, part);
							psearch.setVisible(true);
						}else{
							JOptionPane.showMessageDialog(null, "Peça não encontrada!" ,"Erro",JOptionPane.INFORMATION_MESSAGE);
						}
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Busca inválida!" ,"Erro",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			
			srcButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			srcButton.setBounds(180, 20, 135, 30);

			SwingUtilities.invokeLater(new Runnable(){public void run(){srcButton.requestFocus();}});
			p.add(srcButton);
			
			JTable table = new JTable(datap, cols){
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
						if(currentPart != null){
							Interface detp = new DetailsPart(host, currentPart);
							detp.setVisible(true);
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "Selecione uma peça." ,"Erro",JOptionPane.INFORMATION_MESSAGE);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			});
			p.add(det);
			
			JButton newPart = new JButton("Nova Peça");
			newPart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Interface newp = new NewPart(host, pr);
					newp.setVisible(true);
					dispose();
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
			
			paneList = new JScrollPane(listSubParts);
			paneList.setBounds(360, 70, 170, 231);
			p.add(paneList);
			
			JButton clearList = new JButton("Apagar Subpeças");
			clearList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainClient.subParts.clear();
					Interface con = new Connect(host);
					con.setVisible(true);
					dispose();
				}
			});
			
			clearList.setFont(new Font("Tahoma", Font.PLAIN, 12));
			clearList.setBounds(360, 310, 170, 30);
			p.add(clearList);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void fillTablePart(ArrayList<IPart> parts){
		datap = new String[parts.size()][3];
		for (int i = 0; i < parts.size(); i++) {
			datap[i][0] = parts.get(i).getName();
			datap[i][1] = parts.get(i).getDesc();
			datap[i][2] = Integer.toString(parts.get(i).getCod());
		}
	}
	
	private static void fillTableSubPart(){
		datasp = new String[MainClient.subParts.size()][2];
		int i = 0;
		Enumeration<Integer> keys = MainClient.subParts.keys();
		while(keys.hasMoreElements()){
			int cod = keys.nextElement();
			datasp[i][0] = Connect.getPart(cod).getName();
			datasp[i][1] = MainClient.subParts.get(cod).toString();
			i++;
		}
	}
	
	public String[] getAllNames(){
		String[] names = new String[MainClient.subParts.size()];
		int i = 0;
		Enumeration<Integer> keys = MainClient.subParts.keys();
		while(keys.hasMoreElements()){
			int cod = keys.nextElement();
			names[i] = Connect.getPart(cod).getName();
			i++;
		}
		return names;
	}
	
	public static IPart getPart(int cod){
		try{
			while(i < MainClient.servs.size()) {
				Registry registry = LocateRegistry.getRegistry(MainClient.servs.get(i));
				IPartRepository pr = (IPartRepository) registry.lookup("IPartRepository");
				IPart p = pr.getPart(cod);
				if(p != null){
					i = 0;
					return p;
				}
				i++;
			}
		}catch(Exception e){
			i++;
			return getPart(cod);
		}
		i = 0;
		return null;
	}
}

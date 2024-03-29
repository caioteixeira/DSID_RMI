package client;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	private String[] cols = {"NOME","DESCRI��O", "C�DIGO"};
	private IPart currentPart = null;
	private static JScrollPane paneList;
	
	public Connect(String name){
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
			
			Registry registry = MainClient.registry;
			if(registry == null)
			{
				return;
			}
			IPartRepository pr = (IPartRepository) registry.lookup(name);
			fillTablePart(pr.getAllParts());
			fillTableSubPart();
			
			setBounds(100, 100, 550, 380);
			setTitle("Servidor: " + name);
			
			JTextField search = new JTextField("Digite o c�digo");
			search.setFont(new Font("Tahoma", Font.PLAIN, 14));
			search.setBounds(25, 20, 150, 30); // setBounds(x, y, width, height)
			search.setForeground(Color.GRAY);
			search.addFocusListener(new FocusListener() {
			    @Override
			    public void focusGained(FocusEvent e) {
			        if (search.getText().equals("Digite o c�digo")) {
			        	search.setText("");
			        	search.setForeground(Color.BLACK);
			        }
			    }
			    @Override
			    public void focusLost(FocusEvent e) {
			        if (search.getText().isEmpty()) {
			        	search.setForeground(Color.GRAY);
			        	search.setText("Digite o c�digo");
			        }
			    }
			    });
			p.add(search);
			
			JButton srcButton = new JButton("Buscar Pe�a");
			srcButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try{
						IPart part = pr.getPart(Integer.parseInt(search.getText()));
						if(part != null){
							Interface psearch = new DetailsPart(name, part);
							psearch.setVisible(true);
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "Pe�a n�o encontrada!" ,"Erro",JOptionPane.INFORMATION_MESSAGE);
						}
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Busca inv�lida!" ,"Erro",JOptionPane.INFORMATION_MESSAGE);
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
			tab.setBounds(5, 70, 335, 216);
			p.add(tab);
			
			JLabel totalParts = new JLabel("Quantidade de Pe�as: " + datap.length);
			totalParts.setFont(new Font("Tahoma", Font.BOLD, 12));
			totalParts.setBounds(5, 286, 300, 20);
			totalParts.setVisible(true);
			p.add(totalParts);
			

			JButton det = new JButton("Detalhes");
			det.setFont(new Font("Tahoma", Font.PLAIN, 12));
			det.setBounds(10, 310, 125, 30);
			det.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try{
						if(currentPart != null){
							Interface detp = new DetailsPart(name, currentPart);
							detp.setVisible(true);
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "Selecione uma pe�a." ,"Erro",JOptionPane.INFORMATION_MESSAGE);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			});
			p.add(det);
			
			JButton newPart = new JButton("Nova Pe�a");
			newPart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Interface newp = new NewPart(name, pr);
					newp.setVisible(true);
					dispose();
				}
			});
			
			newPart.setFont(new Font("Tahoma", Font.PLAIN, 12));
			newPart.setBounds(160, 310, 130, 30);
			p.add(newPart);
			
			JLabel titleSubParts = new JLabel("SUB-PE�AS ATUAIS");
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
			paneList.setBounds(360, 70, 170, 216);
			p.add(paneList);
			
			JLabel totalSubParts = new JLabel("Total de Sub-pe�as: " + MainClient.total());
			totalSubParts.setFont(new Font("Tahoma", Font.BOLD, 12));
			totalSubParts.setBounds(360, 286, 300, 20);
			totalSubParts.setVisible(true);
			p.add(totalSubParts);
			
			JButton clearList = new JButton("Apagar Sub-pe�as");
			clearList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainClient.subParts.clear();
					Interface con = new Connect(name);
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
		Enumeration<IPart> keys = MainClient.subParts.keys();
		while(keys.hasMoreElements()){
			IPart p = keys.nextElement();
			datasp[i][0] = p.getName();
			datasp[i][1] = MainClient.subParts.get(p).toString();
			i++;
		}
	}
	
	public String[] getAllNames(){
		String[] names = new String[MainClient.subParts.size()];
		int i = 0;
		Enumeration<IPart> keys = MainClient.subParts.keys();
		while(keys.hasMoreElements()){
			IPart p = keys.nextElement();
			names[i] = p.getName();
			i++;
		}
		return names;
	}
}

package client;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Naming;

import javax.swing.JLabel;

import server.PartRepository;

public class Connect extends Interface{
	private static final long serialVersionUID = 1L;

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
			PartRepository pr = (PartRepository) Naming.lookup("rmi://"+host+"/PartService");
			
			JLabel teste = new JLabel(pr.teste());
			teste.setFont(new Font("Tahoma", Font.PLAIN, 18));
			teste.setBounds(118, 11, 174, 22);
			p.add(teste);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
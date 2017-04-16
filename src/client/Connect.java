package client;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JLabel;

import server.IPartRepository;

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
			
			Registry registry = LocateRegistry.getRegistry(host);
			IPartRepository pr = (IPartRepository) registry.lookup("IPartRepository");
			
			
			JLabel teste = new JLabel(pr.getPart(1).getName());
			teste.setFont(new Font("Tahoma", Font.PLAIN, 18));
			teste.setBounds(118, 11, 174, 22);
			p.add(teste);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

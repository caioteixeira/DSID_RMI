import java.awt.Font;
import java.rmi.Naming;

import javax.swing.JLabel;

public class Connect extends Interface{
	private static final long serialVersionUID = 1L;

	public Connect(String host){
		try{
			PartRepositor pr = (PartRepositor) Naming.lookup("rmi://"+host+"/PartService");
			
			JLabel teste = new JLabel(pr.teste());
			teste.setFont(new Font("Tahoma", Font.PLAIN, 18));
			teste.setBounds(118, 11, 174, 22);
			p.add(teste);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

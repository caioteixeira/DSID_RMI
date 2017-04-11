import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;

public class MainServer {
	public MainServer(){
		try{
			System.setProperty("java.rmi.server.hostname","192.168.1.104");
			LocateRegistry.createRegistry(1099);
			PartRepository pr = new PartRepository();
			Naming.bind("PartService", (Remote)pr);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new MainServer();
	}

}

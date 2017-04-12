package server;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;

public class MainServer {
	
	public MainServer(){
		try{
			System.setProperty("java.rmi.server.hostname","127.0.0.1");
			LocateRegistry.createRegistry(1099);
			PartRep pr = new PartRep();
			Naming.bind("PartService", (Remote)pr);
		}catch(ExportException e){
			System.exit(0);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new MainServer();
	}

}

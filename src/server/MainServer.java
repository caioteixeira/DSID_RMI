package server;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

public class MainServer 
{
	public static void RunServer()
	{
		try
		{
//			System.setProperty("java.rmi.server.hostname","127.0.0.1");
//			LocateRegistry.createRegistry(1099);
			PartRepository pr = new PartRepository();
			IPartRepository repo = (IPartRepository) UnicastRemoteObject.exportObject(pr, 0);
			
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("IPartRepository", repo);
			
//			Naming.bind("PartService", (Remote)pr);
            System.err.println("Server ready");
		}
		catch(Exception e)
		{
            System.err.println("Server exception: " + e.toString());

			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		RunServer();
	}
}

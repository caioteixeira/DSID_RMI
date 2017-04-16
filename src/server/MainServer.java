package server;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MainServer 
{
	public static void RunServer()
	{
		try
		{
			PartRepository pr = new PartRepository();
			IPartRepository repo = (IPartRepository) UnicastRemoteObject.exportObject(pr, 0);
			
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.bind("IPartRepository", repo);
			
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

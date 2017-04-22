package client;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CheckServer{
	
	public static boolean isServer(String host)
	{
		try
		{			
			System.out.println("IsServer: " + host);
			Registry registry = LocateRegistry.getRegistry(host);
			registry.list();
			return true;
		}
		catch(Exception e){     
			return false;
		}
	}
}

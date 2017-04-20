package client;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import server.IPartRepository;

public class CheckServer{
	
	public static boolean isServer(String host)
	{
		try
		{			
			System.out.println("IsServer: " + host);
			Registry registry = LocateRegistry.getRegistry(host);
			IPartRepository repo = (IPartRepository) registry.lookup("IPartRepository");
			String response = repo.test();
			System.out.println("Response: " + response);
			
			return true;
		}
		catch(Exception e){     
			return false;
		}
	}
}
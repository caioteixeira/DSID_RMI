package server;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MainServer 
{
	public static void RunServer(String serverName, String registryHost)
	{
		try
		{
			Registry registry;
			try
			{
				registry = LocateRegistry.getRegistry(registryHost, 1099);
				registry.list();
			}
			catch(Exception e)
			{
				System.out.println("Could not locate remote registry, creating a new local one!");	
				registry = LocateRegistry.createRegistry(1099);
			}
						
			PartRepository pr = new PartRepository();
			IPartRepository repo = (IPartRepository) UnicastRemoteObject.exportObject(pr, 0);
			
			try
			{
				registry.bind(serverName, repo);
			}
			catch (AlreadyBoundException e)
			{
				System.out.println("An existent repository already uses the same name, please use a different name!");
				System.exit(0);
			}
			
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
		String serverName = "IPartRepository";
		String registryHost = "127.0.0.1";
		
		if(args.length >= 1)
		{
			serverName = args[0];
		}
		
		if(args.length >= 2)
		{
			registryHost = args[1];
		}
		
		RunServer(serverName, registryHost);
	}
}

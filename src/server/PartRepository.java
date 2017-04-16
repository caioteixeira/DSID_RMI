package server;
import java.rmi.RemoteException;

public class PartRepository implements IPartRepository {
	
	public PartRepository() throws RemoteException 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String teste() throws RemoteException
	{
		return "teste";
	}

	@Override
	public void insertPart(IPart p) throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPart getPart(int cod) throws RemoteException
	{
		return new Part();
	}

	@Override
	public IPart[] getAllParts() throws RemoteException
	{
		// TODO Auto-generated method stub
		return null;
	}

}


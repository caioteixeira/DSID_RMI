package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PartRepository extends UnicastRemoteObject implements IPartRepository {
	
	private static final long serialVersionUID = 1L;
	
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
	public void insertPart(Part p) throws RemoteException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Part getPart(int cod) throws RemoteException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Part[] getAllParts() throws RemoteException
	{
		// TODO Auto-generated method stub
		return null;
	}

}


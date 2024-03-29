package server;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class PartRepository implements IPartRepository {
	
	Dictionary<Integer, IPart> parts;
	private int cod = 1;
	
	public PartRepository() throws RemoteException 
	{
		super();
		parts = new Hashtable<Integer, IPart>();
	}
	
	public String test() throws RemoteException
	{
		return "teste";
	}

	@Override
	public void insertPart(String name, String desc, Dictionary<IPart, Integer> subparts, String host) throws RemoteException
	{
		Part p = new Part(cod, name, desc, subparts, host);
		parts.put(cod, p);
		cod++;
	}

	@Override
	public IPart getPart(int cod) throws RemoteException
	{
		return parts.get(cod);
	}

	@Override
	public ArrayList<IPart> getAllParts() throws RemoteException
	{
		Enumeration<Integer> keys = parts.keys();
		ArrayList<IPart> partList = new ArrayList<IPart>();
		while(keys.hasMoreElements())
		{
			partList.add(parts.get(keys.nextElement()));
		}
		return partList;
	}

}


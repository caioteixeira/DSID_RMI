package server;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

public class PartRepository implements IPartRepository {
	
	Dictionary<Integer, IPart> parts;
	
	public PartRepository() throws RemoteException 
	{
		super();
		parts = new Hashtable<Integer, IPart>();
	}
	
	public String teste() throws RemoteException
	{
		return "teste";
	}

	@Override
	public void insertPart(int cod, String name, String desc, int[] subparts) throws RemoteException
	{
		Part p = new Part(cod, name, desc, subparts);
		
		parts.put(cod, p);
	}

	@Override
	public IPart getPart(int cod) throws RemoteException
	{
		return parts.get(cod);
	}

	@Override
	public IPart[] getAllParts() throws RemoteException
	{
		Enumeration<Integer> keys = parts.keys();
		ArrayList<IPart> partList = new ArrayList<IPart>();
		while(keys.hasMoreElements())
		{
			partList.add(parts.get(keys.nextElement()));
		}
		return (IPart[]) partList.toArray();
	}

}


package server;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Dictionary;

public interface IPartRepository extends Remote 
{
	public String test() throws RemoteException;
	public void insertPart(String name, String desc, Dictionary<IPart, Integer> subparts) throws RemoteException;
	public IPart getPart(int cod) throws RemoteException;
	public ArrayList<IPart> getAllParts() throws RemoteException;
}

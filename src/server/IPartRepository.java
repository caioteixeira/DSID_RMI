package server;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPartRepository extends Remote 
{
	public String teste() throws RemoteException;
	public void insertPart(int cod, String name, String desc, int[] subparts) throws RemoteException;
	public IPart getPart(int cod) throws RemoteException;
	public IPart[] getAllParts() throws RemoteException;
}

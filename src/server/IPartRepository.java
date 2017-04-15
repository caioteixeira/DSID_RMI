package server;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPartRepository extends Remote 
{
	public String teste() throws RemoteException;
	public void insertPart(Part p) throws RemoteException;
	public Part getPart(int cod) throws RemoteException;
	public Part[] getAllParts() throws RemoteException;
}

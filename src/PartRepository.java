import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PartRepository extends UnicastRemoteObject implements PartRepositor {
	
	private static final long serialVersionUID = 1L;
	
	protected PartRepository() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String teste() throws RemoteException{
		return "teste";
	}

}


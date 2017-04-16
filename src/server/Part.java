package server;

import java.rmi.RemoteException;

public class Part implements IPart {
	private static final long serialVersionUID = 1L;

	public Part() throws RemoteException 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getCod() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "parte";
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPart[][] getListSubParts() {
		// TODO Auto-generated method stub
		return null;
	}

}

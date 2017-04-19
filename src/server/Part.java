package server;

import java.rmi.RemoteException;

public class Part implements IPart {
	private static final long serialVersionUID = 1L;

	int cod;
	String name;
	String desc;
	int[] subParts;
	
	public Part(int cod, String name, String desc, int[] subParts) throws RemoteException 
	{
		super();
		
		this.cod = cod;
		this.name = name;
		this.desc = desc;
		this.subParts = subParts;
	}
	@Override
	public int getCod() {
		return this.cod;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDesc() {
		return this.desc;
	}

	@Override
	public IPart[][] getListSubParts() {
		// TODO Auto-generated method stub
		return null;
	}

}

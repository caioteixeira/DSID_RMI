package server;

import java.rmi.RemoteException;
import java.util.Dictionary;

public class Part implements IPart {
	private static final long serialVersionUID = 1L;
	private String host;

	int cod;
	String name;
	String desc;
	Dictionary<IPart, Integer> subParts;
	
	public Part(int cod, String name, String desc, Dictionary<IPart, Integer> subParts, String host) throws RemoteException 
	{
		super();
		
		this.cod = cod;
		this.name = name;
		this.desc = desc;
		this.subParts = subParts;
		this.host = host;
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
	public String getHost() {
		return this.host;
	}
	
	@Override
	public Dictionary<IPart, Integer> getListSubParts() {
		
		return subParts;
	}

}

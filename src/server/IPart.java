package server;

import java.io.Serializable;

public interface IPart extends Serializable 
{
	public int getCod();
	public String getName();
	public String getDesc();
	public IPart[][] getListSubParts();
}

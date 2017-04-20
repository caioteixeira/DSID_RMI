package server;

import java.io.Serializable;
import java.util.Dictionary;

public interface IPart extends Serializable 
{
	public int getCod();
	public String getName();
	public String getDesc();
	public Dictionary<Integer, Integer> getListSubParts();
}

package client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import server.IPartRepository;

public class SearchServers implements Runnable{
	
	private boolean run = true;

	private String getSubNet()
	{
		try{
			Process result = Runtime.getRuntime().exec("ipconfig");
	
	        BufferedReader output = new BufferedReader(new InputStreamReader(result.getInputStream()));
	        String thisLine = output.readLine();
	        int leng = thisLine.length();
	       
	        while(true){
	        	if(thisLine != null && leng > 1){
		        	if(thisLine.contains("Gateway") && Character.isDigit(thisLine.charAt(leng-1))){
		        		break;
		        	}
	        	}
	        	thisLine = output.readLine();
	    		leng = thisLine.length();
	        }
	        output.close();
	        return buildSubnetString(thisLine);
	        
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	private String buildSubnetString(String s)
	{
		char[] c1 = s.toCharArray();
		int cont = 0;
		int i = c1.length - 1;
		int j = 0;
		while (i > 0) {
			if(c1[i] == '.'){
				cont++;
				if(cont == 1){
					j = i;
				}
			}
			i--;
			if(cont >= 3) break;
		}
		while(Character.isDigit(s.charAt(i))){
			i--;
		}
		return s.substring(i+1, j);
	}
	
	private void findServers()
	{
		String subnet = getSubNet();
		try{
		   int timeout=100;
		   for (int i=1;i<255;i++){
			   if(!run) return;
		       String host=subnet + "." + i;
		       if (InetAddress.getByName(host).isReachable(timeout)){
		           if(isServer(host)){
		        	   Home.insereServ(InetAddress.getByName(host).getHostName(), host);
		        	   Home.updateTable();
		           }
		       }
		   	}
		   
		   subnet = subnet.substring(0, subnet.length() - 2);
		   
		   for (int i=0;i<255;i++){
		       String host=subnet + "." + i;
		       for(int j=1; j<255; j++){
				   if(!run) return;
		    	   host=host + "." + j;
			       if (InetAddress.getByName(host).isReachable(timeout)){
			           if(isServer(host)){
			        	   Home.insereServ(InetAddress.getByName(host).getHostName(), host);
			        	   Home.updateTable();
			           }
			       }
		       }
		   	}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static boolean isServer(String host)
	{
		try
		{			
			System.out.println("IsServer: " + host);
			Registry registry = LocateRegistry.getRegistry(host);
			IPartRepository repo = (IPartRepository) registry.lookup("IPartRepository");
			String response = repo.teste();
			System.out.println("Response: " + response);
			
			return true;
		}
		catch(Exception e){     
			return false;
		}
	}

	@Override
	public void run() 
	{
		while(run)
		{
			findServers();
		}
	}
	
	public void stop()
	{
		run = false;
	}
}

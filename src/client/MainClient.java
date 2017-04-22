package client;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.rmi.registry.Registry;

import server.IPart;

public class MainClient {
	public static ArrayList<String> servs = new ArrayList<String>();
	public static Hashtable<IPart, Integer> subParts = new Hashtable<IPart, Integer>();
	public static Registry registry;
	public static String registryHost;
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static int total(){
		int total = 0;
		Enumeration<IPart> eParts = MainClient.subParts.keys();
		while(eParts.hasMoreElements()){
			total+= MainClient.subParts.get(eParts.nextElement());
		}
		return total;
	}
}

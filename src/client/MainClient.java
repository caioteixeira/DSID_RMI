package client;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Hashtable;

import server.IPart;

public class MainClient {
	public static ArrayList<String> servs = new ArrayList<String>();
	public static Hashtable<IPart, Integer> subParts = new Hashtable<IPart, Integer>();
	
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
}

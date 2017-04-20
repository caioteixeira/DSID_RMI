package client;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Hashtable;

public class MainClient {
	public static ArrayList<String> servs = new ArrayList<String>();
	public static Hashtable<Integer, Integer> subParts = new Hashtable<Integer, Integer>();
	private static int ind = 0;
	
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
	
	public static int getCod(){
		return ++ind;
	}
}

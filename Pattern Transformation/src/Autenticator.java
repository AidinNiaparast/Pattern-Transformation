import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Autenticator {
	public static boolean registerCheck(String username , String password) throws FileNotFoundException {
		if(username==""||username ==null||password==""||password==null)
			return false;
		File f = new File("C:\\Users\\Aidin\\eclipse-workspace\\project4\\usersDB.txt");
		Scanner sc = new Scanner(f);
		ArrayList<String> users = new ArrayList<>();
		while(sc.hasNextLine()){
			users.add(sc.nextLine());
		}
		sc.close();
		for(String s : users){
			String[] thisuser = s.split(" ");
			if(thisuser[0].equals(username)){
				return false;
			}
		}
		return true;
	}

	public static boolean loginCheck(String username, String password) throws FileNotFoundException {
		
		File f = new File("C:\\Users\\Aidin\\eclipse-workspace\\project4\\usersDB.txt");
		Scanner sc = new Scanner(f);
		ArrayList<String> users = new ArrayList<>();
		while(sc.hasNextLine()){
			users.add(sc.nextLine());
		}
		sc.close();
		for(String s : users){
			String[] thisuser = s.split(" ");
			if(thisuser[0].equals(username) && thisuser[1].equals(password)){
				return true;
			}
		}
		return false;
		
	}

	
	
}

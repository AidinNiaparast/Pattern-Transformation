import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class game {
	public String[][] currentState=new String[5][5];
	public String[][] finalState=new String[5][5];
	public int moves=0;
	public long startTime,finishTime;
	public boolean over=false;
	public int level;
	
	public game(int level,String[][] start,String[][] end) {
		this.currentState=start;
		this.finalState=end;
		this.level = level;
		this.startTime = System.currentTimeMillis();
	}

    private	void rightShift(int row) {
    	//System.out.println("Right Shift");
		String temp=currentState[row][4];
		for(int i=3;i>=0;i--)
			currentState[row][i+1]=currentState[row][i];
		currentState[row][0]=temp;
	}
	private void leftShift(int row) {
		//System.out.println("Left Shift");
		String temp=currentState[row][0];
		for(int i=0;i<4;i++)
			currentState[row][i]=currentState[row][i+1];
		currentState[row][4]=temp;
	}
	private void upShift(int col) {
		//System.out.println("Up Shift");
		String temp=currentState[0][col];
		for(int i=0;i<4;i++)
			currentState[i][col]=currentState[i+1][col];
		currentState[4][col]=temp;
	}
	private void downShift(int col) {
		//System.out.println("Down Shift");
		String temp=currentState[4][col];	
		for(int i=3;i>=0;i--) 
			currentState[i+1][col]=currentState[i][col];
		currentState[0][col]=temp;
	}
	
	public void move(String move) {
		switch(move.charAt(0)) {
			case 'U':
				upShift(Integer.parseInt(move.substring(1))-1);
				break;
			case 'D':
				downShift(Integer.parseInt(move.substring(1))-1);
				break;
			case 'L':
				leftShift(Integer.parseInt(move.substring(1))-1);
				break;
			case 'R':
				rightShift(Integer.parseInt(move.substring(1))-1);
				break;	
		}
		if(overcheck()) {
			over=true;
			finishTime=System.currentTimeMillis();
		}
		moves++;
	}
	
	
	private boolean overcheck() {
		for(int i=0 ; i<currentState.length ; i++)
			for(int j=0 ; j<currentState.length ; j++)
				if(!currentState[i][j].equals(finalState[i][j]))
					return false;
		
		return true;
	}

	/*public static String toString(int[][] table){
		String toReturn = "";
		
		for(int[] i:table)
			for(int j : i)
				toReturn+=j+",";
		
		return toReturn;
	}*/

	public void updateLeaderBoeard(String username) throws FileNotFoundException {
		File f = new File("C:\\Users\\Aidin\\eclipse-workspace\\project4\\leaderBoardDB.txt");
		Scanner sc = new Scanner(f);
		ArrayList<String> records = new ArrayList<>();
		
		
		int i=1;
		//for(int i=1;i<=15;i++) {
		while(sc.hasNextLine()) {
			if(i==level) {
				String s=sc.nextLine();
				s+=username+","+moves+","+(finishTime-startTime)+" ";
				records.add(s);
			}
			else
				records.add(sc.nextLine());
			i++;
		}
		
		sc.close();
		
		PrintWriter pw = new PrintWriter(f);
		for(String s : records)
			pw.println(s);
		pw.flush();
		pw.close();
	}
	
}

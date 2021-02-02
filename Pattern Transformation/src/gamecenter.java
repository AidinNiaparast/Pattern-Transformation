

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class gamecenter
 */
@WebServlet("/gamecenter")
public class gamecenter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static HashMap<String , game> games = new HashMap<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gamecenter() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getSession().getAttribute("username")==null){
			response.sendRedirect("accessdenied.html");
			return;
		}
		
		String move = request.getParameter("move");
		String username = (String) request.getSession().getAttribute("username");
		game g = games.get(username);
		g.move(move);
		String[][] result=g.currentState;
		
		
		if(g.over){
			//long finishTime = System.currentTimeMillis();
			String newEntry = "";
			String old="";
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
					old=s;
					break;
				}
			}
			String[] thisEntry = old.split(" ");
			//long TotalTime = finishTime-g.startTime;
			if(g.level>Integer.parseInt(thisEntry[2])){
				thisEntry[2]=String.valueOf(g.level);
				for(String i:thisEntry)
					newEntry+=i+" ";
				newEntry+= g.moves+","+(g.finishTime-g.startTime)+" ";
			}else{
				String[] record = thisEntry[g.level+2].split(",");
				String newrec = "";
				if(g.moves<Integer.parseInt(record[0]))
					newrec+=g.moves+",";
				else
					newrec+=record[0]+",";
				if(g.finishTime<Integer.parseInt(record[1]))
					newrec+=g.finishTime+" ";
				else
					newrec+=record[1]+" ";
				thisEntry[g.level+2]=newrec;
				for(String i:thisEntry)
					newEntry+=i+" ";
			}
			users.remove(old);
			users.add(newEntry);
			PrintWriter pw = new PrintWriter(f);
			for(String s : users)
				pw.println(s);
			pw.flush();
			pw.close();
			g.updateLeaderBoeard(username);
			//game.updateLeaderBoeard(g.moves,TotalTime,username , g.level);
			request.getSession().removeAttribute("hasGame");
			response.sendRedirect("win.html");
			return;
		}else{
			//TODO
			request.setAttribute("result", result);
			request.setAttribute("end", g.finalState);
			request.setAttribute("game", g);
			RequestDispatcher view = request.getRequestDispatcher("gamepage.jsp");
			view.forward(request, response);
			return;
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

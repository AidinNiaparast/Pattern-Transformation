

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class newgame
 */
@WebServlet("/newgame")
public class newgame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newgame() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
			
		if(request.getSession().getAttribute("username")==null){
			response.sendRedirect("accessdenied.html");
			return;
		}
		
		/*if(request.getSession().getAttribute("hasGame")!=null){
			response.sendRedirect("error.html");
			return;
		}*/
		
		
		int level =  request.getParameter("level")!=null?Integer.parseInt(request.getParameter("level")):Integer.parseInt(String.valueOf(request.getAttribute("level")));
		System.out.println(level);
		String username = (String)request.getSession().getAttribute("username");

		
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
				if(Integer.parseInt(thisuser[2])+1<level){
					RequestDispatcher view = request.getRequestDispatcher("locked.html");
					view.forward(request, response);
					return;
				}
				break;
			}
		}
		
		
		
		
		f = new File("C:\\Users\\Aidin\\eclipse-workspace\\project4\\levelsDB.txt");
		sc = new Scanner(f);
		for(int i=0 ; i<level-1 ; i++){
			if(sc.hasNextLine()){
				sc.nextLine();
			}
			else{
				response.sendRedirect("error.html");
				sc.close();
				return;
			}
		}
		String thislevel="";
		if(sc.hasNextLine())
			thislevel = sc.nextLine();
		else{
			response.sendRedirect("error.html");
			sc.close();
			return;
		}
		System.out.println(thislevel);
		String[] tables = thislevel.split(",");
		String[][] start = new String[5][5];
		String[][] end = new String[5][5];
		for(int i=0 ; i<5 ; i++)
			for(int j=0 ; j<5 ;j++)
				start[i][j] = tables[5*i + j];
		
		for(int i=0 ; i<5 ; i++)
			for(int j=0 ; j<5 ;j++)
				end[i][j] = tables[25 + 5*i + j];
		
		
		game g =new game(level,start,end);
		synchronized (gamecenter.games) {
			gamecenter.games.put(username, g);
		}
		request.getSession().setAttribute("hasGame", level);
		request.setAttribute("result", start);
		request.setAttribute("end", end);
		request.setAttribute("game", g);
		RequestDispatcher view = request.getRequestDispatcher("gamepage.jsp");
		view.forward(request, response);
		sc.close();
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

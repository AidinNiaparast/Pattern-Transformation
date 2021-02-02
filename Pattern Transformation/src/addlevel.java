

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
 * Servlet implementation class addlevel
 */
@WebServlet("/addlevel")
public class addlevel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addlevel() {
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
		
		String newlevel = (String)request.getParameter("level");
		
		File f = new File("C:\\Users\\Aidin\\eclipse-workspace\\project4\\levelsDB.txt");
		Scanner sc = new Scanner(f);
		ArrayList<String> levels = new ArrayList<>();
		while(sc.hasNextLine())
			levels.add(sc.nextLine());
		levels.add(newlevel);
		PrintWriter pw = new PrintWriter(f);
		for(String i : levels)
			pw.println(i);
		pw.flush();
		pw.close();
		
		f = new File("C:\\Users\\Aidin\\eclipse-workspace\\project4\\leaderboardDB.txt");
		sc = new Scanner(f);
		ArrayList<String> board = new ArrayList<>();
		while(sc.hasNextLine())
			board.add(sc.nextLine());
		board.add(" ");
		pw = new PrintWriter(f);
		for(String i : levels)
			pw.println(i);
		pw.flush();
		pw.close();
		
		RequestDispatcher view = request.getRequestDispatcher("adminpanel.jsp");
		view.forward(request, response);
		sc.close();
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

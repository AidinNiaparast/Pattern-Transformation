import java.io.File;
import java.io.FileWriter;
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
 * Servlet implementation class register_check
 */
@WebServlet("/register_check")
public class register_check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register_check() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(Autenticator.registerCheck(username, password)){
			File f = new File("C:\\Users\\Aidin\\eclipse-workspace\\project4\\usersDB.txt");
			Scanner sc = new Scanner(f);
			ArrayList<String> users = new ArrayList<>();
			while(sc.hasNextLine()){
				users.add(sc.nextLine());
			}
			sc.close();
			PrintWriter pw = new PrintWriter(f);
			for(String s : users)
				pw.println(s);
			pw.println(username+" "+password+" 0 ");
			pw.flush();
			pw.close();
			System.out.println("added "+username+" "+ password);
			request.getSession().setAttribute("username", username);
			
			RequestDispatcher view = request.getRequestDispatcher("userpanel.jsp");
			view.forward(request, response);
		}else{
			RequestDispatcher view = request.getRequestDispatcher("registerAgain.html");
			view.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

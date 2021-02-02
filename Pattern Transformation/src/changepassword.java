

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
 * Servlet implementation class changepassword
 */
@WebServlet("/changepassword")
public class changepassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changepassword() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		if(request.getSession().getAttribute("username")==null)
			response.sendRedirect("accessdenied.html");
		
		String username = (String) request.getSession().getAttribute("username");
		String newpass = request.getParameter("newpass");
		String password = request.getParameter("oldpass");
		boolean done=false;
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
				thisuser[1] = newpass;
				users.remove(s);
				String user = new String("");
				for(String s2 : thisuser)
					user+= s2+" ";
				users.add(user);
				done=true;
				break;
			}
		}
		
		PrintWriter pw = new PrintWriter(f);
		for(String s : users)
			pw.println(s);
		pw.flush();
		if(!done){
			response.sendRedirect("wrongpasswordforchange.jsp");
		}
		RequestDispatcher view = request.getRequestDispatcher("seccesschangepassword.jsp");
		view.forward(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

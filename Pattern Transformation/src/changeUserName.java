

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
 * Servlet implementation class changeUserName
 */
@WebServlet("/changeUserName")
public class changeUserName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeUserName() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getSession().getAttribute("username")==null) {
			response.sendRedirect("accessdenied.html");
			return;
		}
		
		String username = (String) request.getSession().getAttribute("username");
		String newUsername = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(Autenticator.registerCheck(newUsername , password)){
			File f = new File("C:\\Users\\Aidin\\eclipse-workspace\\project4\\usersDB.txt");
			boolean done = false;
			Scanner sc = new Scanner(f);
			ArrayList<String> users = new ArrayList<>();
			while(sc.hasNextLine()){
				users.add(sc.nextLine());
			}
			sc.close();
			if(!Autenticator.registerCheck(newUsername, password))
				response.sendRedirect("unavailableusername.jsp");
			for(String s : users){
				String[] thisuser = s.split(" ");
				if(thisuser[0].equals(username) && thisuser[1].equals(password)){
					thisuser[0] = newUsername;
					users.remove(s);
					String user = new String("");
					for(String s2 : thisuser)
						user+= s2+" ";
					users.add(user);
					done = true;
					break;
				}
			}
			PrintWriter pw = new PrintWriter(f);
			for(String s : users)
				pw.println(s);
			pw.flush();
			if(!done)
				response.sendRedirect("wrongpasswordforchange.jsp");
			request.getSession().setAttribute("username", newUsername);
			pw = response.getWriter();
			pw.print("successfuly changed username!");
			RequestDispatcher view = request.getRequestDispatcher("editUser.jsp");
			view.include(request, response);
		}else{
			PrintWriter pw = response.getWriter();
			pw.print("sorry this one is already taken!");
			RequestDispatcher view = request.getRequestDispatcher("editUser.jsp");
			view.include(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

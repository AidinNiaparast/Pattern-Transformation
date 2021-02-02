

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
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class login_check
 */
@WebServlet("/login_check")
public class login_check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login_check() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		if(request.getSession().getAttribute("username")!=null){
			RequestDispatcher view = request.getRequestDispatcher("userpanel.jsp");
			view.forward(request, response);
			return;
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(Autenticator.loginCheck(username , password)){
			System.out.println("user "+username+" loged in");
			HttpSession s = request.getSession();
			s.setAttribute("username", username);
			if(username.equals("admin")){
				RequestDispatcher view = request.getRequestDispatcher("adminpanel.jsp");
				view.forward(request, response);
				return;
			}
			RequestDispatcher view = request.getRequestDispatcher("userpanel.jsp");
			view.forward(request, response);
			return;
		}else{
//			PrintWriter pw = new PrintWriter(response.getWriter());
			RequestDispatcher view = request.getRequestDispatcher("loginAgain.html");
			view.forward(request, response);
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

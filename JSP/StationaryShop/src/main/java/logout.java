import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;
 
@WebServlet(urlPatterns={"/logout"})
public class logout extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public logout() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//    	PrintWriter out = response.getWriter();
//		out.print("<script type='text/javascript'>function preventBack() {window.history.forward();}setTimeout('preventBack()', 0);window.onunload = function () { null };</script>");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("mob");
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ListVinyl;

/**
 * Servlet implementation class addVinylServlet
 */
@WebServlet("/addVinylServlet")
public class addVinylServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addVinylServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String artist = request.getParameter("artist");
		String album = request.getParameter("album");
		String genre = request.getParameter("genre");
		String rating = request.getParameter("rating");
		
		ListVinyl lv = new ListVinyl(artist, album, genre, rating);
		ListVinylHelper dao = new ListVinylHelper();
		dao.insertVinyl(lv);
		getServletContext().getRequestDispatcher("/addVinyl.html").forward(request, response);
	}

}

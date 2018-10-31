package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ListVinyl;

/**
 * Servlet implementation class editVinylListServlet
 */
@WebServlet("/editVinylListServlet")
public class editVinylListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editVinylListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		ListVinylHelper dao = new ListVinylHelper();
		String act = request.getParameter("doThisToItem");
		
		if(act ==null) {
			//no button has been selected
			getServletContext().getRequestDispatcher("/viewAllVinylServlet").forward(request, response);
		}
		else if (act.equals("Delete Selected Item")) {
			Integer tempId = Integer.parseInt(request.getParameter("id"));
			ListVinyl itemToDelete = dao.searchForItemById(tempId);
			dao.deleteVinyl(itemToDelete);
			
			getServletContext().getRequestDispatcher("/viewAllVinylServlet").forward(request, response);
		}
		else if(act.equals("Edit Selected Item")) {
			Integer tempId = Integer.parseInt(request.getParameter("id"));
			ListVinyl itemToEdit = dao.searchForItemById(tempId);
			request.setAttribute("itemToEdit", itemToEdit);
			getServletContext().getRequestDispatcher("/edit-item.jsp").forward(request, response);
		}
		else if(act.equals("Add New Item")) {
			getServletContext().getRequestDispatcher("/addVinyl.html").forward(request, response);
		}
		
	}

}

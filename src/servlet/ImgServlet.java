package servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EmpDAO;
import bean.IMG;

/**
 * Servlet implementation class ImgServlet
 */
@WebServlet("/Img")
public class ImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,String> map = new HashMap<>();
		String id = request.getParameter("id");
		map.put("imgId", id);
		EmpDAO dao = new EmpDAO();
		IMG img = dao.getImg(map);
		if(img != null) {
			response.setContentType(img.getContent_type());
			response.getOutputStream().write(img.getImg());
		}
	}


}

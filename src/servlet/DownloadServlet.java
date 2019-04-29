package servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.FileDownloadLogic;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/Download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileDownloadLogic logic = new FileDownloadLogic();
		logic.checkWrongInput(request, response);
		logic.execute(request, response);

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"EmpMgr.csv\"");

		String csv = (String) request.getAttribute("csv");

		OutputStream os = response.getOutputStream();
		byte[] downloadData = csv.getBytes();
		os.write(downloadData);
		os.close();
	}
}

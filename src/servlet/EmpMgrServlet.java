package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommonLogic;

@WebServlet("/EmpMgr")
@MultipartConfig(location = "/tmp", maxFileSize = 1000000)
public class EmpMgrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpMgrServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストパラメータ(action：実行するLogicクラスのファイル名)
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		StringBuilder next = new StringBuilder();
		if (action == null) {
			next.append("/index.jsp");
		} else {
			next.append("/WEB-INF/jsp/");
			try {
				//パラメータで渡されたクラスを呼び出す
				Class<?> clazz = Class.forName(action);
				CommonLogic logic = (CommonLogic) clazz.newInstance();
				//logic.checkWrongInputは、不適切な入力があるとき警告文をスコープに保存
				logic.checkWrongInput(request, response);
				//logic.executeの戻り値は、次のフォワード先のファイル名
				next.append(logic.execute(request, response));
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				next.append("Error.jsp");
			}
		}
		request.getRequestDispatcher(next.toString()).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

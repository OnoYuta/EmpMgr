package action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DeptDAO;

public class DeptAddLogic implements CommonLogic{
	private String deptName;
	private DeptDAO dao = new DeptDAO();
	private HashMap<Integer,String> deptMap = dao.getDeptMap();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		if(msg.size()==0) {
			if(dao.addDept(deptName)) {
				msg.add("新規部署を登録しました。");
				request.setAttribute("msg", msg);
				return "Result.jsp";
			}else {
				msg.add("新規部署の登録に失敗しました。");
				request.setAttribute("msg", msg);
				return "Result.jsp";
			}
		}else {
			return "Result.jsp";
		}
	}

	@Override
	public void checkWrongInput(HttpServletRequest request, HttpServletResponse response) {
		msg.clear();
		deptName = request.getParameter("deptName");
		if(deptName == null) {
			msg.add("部署名の取得に失敗しました。");
		}else if(isEmpty(deptName)) {
			msg.add("部署名を入力してください。");
		}else if(deptMap.containsValue(deptName)) {
			msg.add("その部署名は既に使用されています。");
		}
		request.setAttribute("msg", msg);
	}
}
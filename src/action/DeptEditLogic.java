package action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DeptDAO;

public class DeptEditLogic implements CommonLogic{
	private DeptDAO dao = new DeptDAO();
	private Integer deptId = null;
	HashMap<Integer,String> deptMap = dao.getDeptMap();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		if(msg.size() == 0) {
			return "DeptEdit.jsp";
		}else {
			return "Error.jsp";
		}
	}

	@Override
	public void checkWrongInput(HttpServletRequest request, HttpServletResponse response) {
		msg.clear();
		String param = request.getParameter("deptId");
		if(param != null) {
			deptId = Integer.parseInt(param);
			if(deptMap.containsKey(deptId)) {
				request.setAttribute("deptId", deptId);
				request.setAttribute("deptName", deptMap.get(deptId));
			}else {
				msg.add("存在しない部署情報が送信されました。");
				request.setAttribute("msg",msg);
			}
		}

	}
}

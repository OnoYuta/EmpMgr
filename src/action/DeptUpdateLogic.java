package action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DeptDAO;

public class DeptUpdateLogic implements CommonLogic {
	private DeptDAO dao = new DeptDAO();
	private HashMap<Integer, String> deptMap = dao.getDeptMap();
	private Integer deptId;
	private String deptName;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		if (msg.size() == 0) {
			if (dao.updateDept(deptId, deptName)) {
				msg.add("部署情報を更新しました。");
				request.setAttribute("msg", msg);
				return "Result.jsp";
			} else {
				msg.add("部署情報の更新に失敗しました。");
				request.setAttribute("msg", msg);
				return "Result.jsp";
			}
		} else {
			request.setAttribute("msg", msg);
			return "Result.jsp";
		}
	}

	@Override
	public void checkWrongInput(HttpServletRequest request, HttpServletResponse response) {
		msg.clear();

		//deptId
		String param = request.getParameter("deptId");
		if (isNum(param)) {
			deptId = Integer.parseInt(param);
			if (!deptMap.containsKey(deptId)) {
				msg.add("存在しない部署IDが選択されました。");
			}
		} else {
			msg.add("部署IDの値が不適切です。");
		}

		//deptName
		deptName = request.getParameter("deptName");
		if (deptName == null) {
			msg.add("部署名の取得に失敗しました。");
		} else if (isEmpty(deptName)) {
			msg.add("部署名を入力してください。");
		} else if (deptMap.containsValue(deptName)) {
			msg.add("その部署名は既に使用されています。");
		}
	}
}

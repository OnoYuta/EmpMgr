package action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DeptDAO;

public class DeptListLogic implements CommonLogic {
	DeptDAO dao = new DeptDAO();
	HashMap<Integer, String> deptMap = dao.getDeptMap();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("deptMap", deptMap);
		// キーでソートする
		ArrayList<Integer> keyList = new ArrayList<>();
		for (Entry<Integer, String> entry : deptMap.entrySet()) {
			keyList.add(entry.getKey());
		}
		Collections.sort(keyList);
		request.setAttribute("keyList", keyList);
		return "DeptList.jsp";
	}

	@Override
	public void checkWrongInput(HttpServletRequest request, HttpServletResponse response) {
	}

}

package action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EmpDAO;

public class EmpListLogic implements CommonLogic {

	private EmpDAO dao = new EmpDAO();
	private HashMap<Integer, String> empMap;
	private HashMap<String, String> keywords = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
            put("name","%");
            put("deptId","%");
            put("empId","%");
        }
    };

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		empMap = dao.getEmpMap(keywords);
		request.setAttribute("empMap", empMap);
		// キーでソートする
		ArrayList<Integer> keyList = new ArrayList<>();
		for (Entry<Integer, String> entry : empMap.entrySet()) {
			keyList.add(entry.getKey());
		}
		Collections.sort(keyList);
		request.setAttribute("keyList", keyList);

		//CSV出力するときのために、検索条件を保存
		request.setAttribute("keywords", keywords);

		return "EmpList.jsp";
	}

	@Override
	public void checkWrongInput(HttpServletRequest request, HttpServletResponse response) {
		String paramName = request.getParameter("name");
		if (paramName != null) {
			if (!isEmpty(paramName)) {
				keywords.put("name", "%" + paramName + "%");
			}
		}

		String paramDeptId = request.getParameter("deptId");
		if (paramDeptId != null) {
			if (isNum(paramDeptId)) {
				keywords.put("deptId", paramDeptId);
			}
		}

		String paramEmpId = request.getParameter("empId");
		if (paramEmpId != null) {
			if (isNum(paramEmpId)) {
				keywords.put("empId", paramEmpId);
			}
		}

	}

}

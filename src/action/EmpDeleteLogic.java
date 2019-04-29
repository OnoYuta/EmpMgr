package action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EmpDAO;

public class EmpDeleteLogic implements CommonLogic{
	int empId;
	EmpDAO dao = new EmpDAO();
	HashMap<Integer, String> empMap = dao.getEmpMap();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		if(msg.size() == 0) {
			if(dao.deleteEmp(empId)) {
				msg.add("社員情報の削除に成功しました。");
			}else {
				msg.add("社員情報の削除に失敗しました。");
			}
		}
		request.setAttribute("msg", msg);

		//処理後は社員一覧ページに遷移するため、社員一覧をスコープに保存
		empMap = dao.getEmpMap();
		request.setAttribute("empMap", empMap);
		// キーでソートする
		ArrayList<Integer> keyList = new ArrayList<>();
		for (Entry<Integer, String> entry : empMap.entrySet()) {
			keyList.add(entry.getKey());
		}
		Collections.sort(keyList);
		request.setAttribute("keyList", keyList);

		return "EmpList.jsp";
	}

	@Override
	public void checkWrongInput(HttpServletRequest request, HttpServletResponse response) {
		msg.clear();
		String param = request.getParameter("empId");
		if(isNum(param)) {
			empId = Integer.parseInt(param);
			if(!empMap.containsKey(empId)) {
				msg.add("存在しない社員IDが送信されました。");
			}
		}else {
			msg.add("不適切な社員IDが送信されました。");
		}
	}

}

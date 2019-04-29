package action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DeptDAO;

public class DeptDeleteLogic implements CommonLogic {
	private DeptDAO dao = new DeptDAO();
	private HashMap<Integer, String> deptMap = dao.getDeptMap();
	private Integer deptId;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		if (msg.size() == 0) {
			if (dao.deleteDept(deptId)) {
				msg.add("部署情報の削除に成功しました。");
			} else {
				msg.add("部署情報の削除に失敗しました。");
			}
		}

		//処理後は部署一覧のページに遷移するため、部署一覧をスコープに保存
		deptMap = dao.getDeptMap();
		request.setAttribute("deptMap", deptMap);
		// キーでソートする
		ArrayList<Integer> keyList = new ArrayList<>();
		for (Entry<Integer, String> entry : deptMap.entrySet()) {
			keyList.add(entry.getKey());
		}
		Collections.sort(keyList);
		request.setAttribute("keyList", keyList);

		request.setAttribute("msg", msg);
		return "DeptList.jsp";
	}

	@Override
	public void checkWrongInput(HttpServletRequest request, HttpServletResponse response) {
		msg.clear();
		String param = request.getParameter("deptId");
		if (isNum(param)) {
			deptId = Integer.parseInt(param);
			if (!deptMap.containsKey(deptId)) {
				msg.add("存在しない部署が選択されました。");
			}
		} else {
			msg.add("不適切な部署IDが送信されました。");
		}
	}

}

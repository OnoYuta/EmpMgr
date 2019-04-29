package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DeptDAO extends DAO {
	//部署一覧を取得
	public HashMap<Integer, String> getDeptMap() {
		getConn();
		String sql = "SELECT D_ID,D_NAME FROM DEPARTMENT";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			HashMap<Integer, String> deptMap = new HashMap<>();
			while (rs.next()) {
				deptMap.put(rs.getInt("D_ID"), rs.getString("D_NAME"));
			}
			ps.close();
			closeConn();
			return deptMap;
		} catch (SQLException e) {
			e.printStackTrace();
			closeConn();
			return null;
		}
	}

	//部署ID一覧を取得
	public ArrayList<String> findAllDeptId() {
		getConn();
		String sql = "SELECT D_ID FROM DEPARTMENT";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ArrayList<String> deptIdList = new ArrayList<>();
			while (rs.next()) {
				deptIdList.add(rs.getString("D_ID"));
			}
			ps.close();
			closeConn();
			return deptIdList;
		} catch (SQLException e) {
			e.printStackTrace();
			closeConn();
			return null;
		}
	}

	//部署の追加
	public boolean addDept(String deptName) {
		getConn();
		String sql = "INSERT INTO DEPARTMENT(D_NAME) VALUES(?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, deptName);
			int result = ps.executeUpdate();
			ps.close();
			closeConn();
			if (result != 1) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			closeConn();
			return false;
		}
	}

	//部署情報の更新
	public boolean updateDept(int deptId, String deptName) {
		getConn();
		String sql = "UPDATE DEPARTMENT SET D_NAME = ? WHERE D_ID =?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, deptName);
			ps.setInt(2, deptId);
			int result = ps.executeUpdate();
			if (result != 1) {
				return false;
			}
			ps.close();
			closeConn();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	//部署の削除
	public boolean deleteDept(int deptId) {
		getConn();
		String sql = "DELETE FROM DEPARTMENT WHERE D_ID=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, deptId);
			int result = ps.executeUpdate();
			if (result != 1) {
				return false;
			}
			ps.close();
			closeConn();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			closeConn();
			return false;
		}
	}
}

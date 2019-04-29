package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enumeration.Pref;
import enumeration.Sex;

public interface CommonLogic {
	public ArrayList<String> msg = new ArrayList<>();

	public String execute(HttpServletRequest request, HttpServletResponse response);

	public void checkWrongInput(HttpServletRequest request, HttpServletResponse response);

	public default boolean isEmpty(String str) {
		if (str.matches("^[\\ \\t\\n\\x0B\\f\\r　]*$")) {
			return true;
		} else {
			return false;
		}
	}

	public default boolean isNum(String str) {
		if (str != null) {
			if (str.matches("^[0-9]+$")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public default boolean isDate(String str) {
		if (str != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				sdf.parse(str);
				return true;
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}

	public default boolean isSex(String str) {
		if (str != null) {
			for (Sex sex : Sex.values()) {
				if (sex.name().equals(str)) {
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	public default boolean isPostcode(String str) {
		if (str != null) {
			if (str.matches("^\\d{3}-\\d{4}$")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public default boolean isPref(String str) {
		if (str != null) {
			for (Pref pref : Pref.values()) {
				if (pref.name().equals(str)) {
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	public default boolean isImg(String str) {
		if (str != null) {
			if (str.toLowerCase().matches(".*\\.(jpg|jpeg|png)")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}

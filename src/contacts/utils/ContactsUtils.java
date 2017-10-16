package contacts.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactsUtils {

	public static boolean isValidEmailAddress(String email) {

		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\."
				+ "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static Date stringToDate(String pData) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date data = null;

		try {
			data = formatter.parse(pData);
		} catch (ParseException e) {
			System.out.println("Problem z konwersj¹ String na Date");
		}
		return data;
	}

	public static boolean isBlankOrNull(String pStr) {

		boolean isEmpty = false;
		if (pStr.equals(null) || pStr.length() == 0) {
			isEmpty = true;
		}
		return isEmpty;
	}

	public static boolean checkLoginData(String username, String password) {

		boolean isEmpty = false;

		if (ContactsUtils.isBlankOrNull(username) || ContactsUtils.isBlankOrNull(password)) {
			isEmpty = true;
		}

		return isEmpty;
	}

	public static boolean checkRegisterData(String username, String password, String userFirstname,
			String userLastname) {

		boolean isEmpty = false;

		if (ContactsUtils.isBlankOrNull(username) || ContactsUtils.isBlankOrNull(password)
				|| ContactsUtils.isBlankOrNull(userFirstname) || ContactsUtils.isBlankOrNull(userLastname)) {
			isEmpty = true;
		}

		return isEmpty;
	}
}

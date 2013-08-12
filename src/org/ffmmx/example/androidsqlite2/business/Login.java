package org.ffmmx.example.androidsqlite2.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.ffmmx.example.androidsqlite2.common.CommonUtil;
import org.ffmmx.example.androidsqlite2.domain.User;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Login {
	public static boolean login(SQLiteDatabase db, String username,
			String password) throws ParseException {
		Cursor cur = db.query("t_user", new String[] { "id", "username",
				"password", "email", "name", "birth" },
				"username=? and password=?",
				new String[] { username, password }, null, null, null);
		boolean result = false;
		if (cur.moveToFirst()) {
			User currUser = new User();
			currUser.setId(cur.getInt(0));
			currUser.setUsername(cur.getString(1));
			currUser.setPassword(cur.getString(2));
			currUser.setEmail(cur.getString(3));
			currUser.setName(cur.getString(4));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			currUser.setBirth(sdf.parse(cur.getString(5)));
			CommonUtil.currentUser = currUser;

		}

		return result;
	}

	public static boolean isLogon() {
		if (CommonUtil.currentUser == null) {
			return false;
		} else {
			return true;
		}
	}
}

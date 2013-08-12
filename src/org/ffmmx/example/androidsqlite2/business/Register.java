package org.ffmmx.example.androidsqlite2.business;

import java.text.SimpleDateFormat;

import org.ffmmx.example.androidsqlite2.domain.User;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Register {
	public static boolean register(SQLiteDatabase db, User user) {
		boolean result = false;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		ContentValues values = new ContentValues();
		values.put("username", user.getUsername());
		values.put("password", user.getPassword());
		values.put("email", user.getEmail());
		values.put("birth", sdf.format(user.getBirth()));
		values.put("name", user.getName());

		if (!isAvailableUser(db, user.getUsername())) {
			throw new RuntimeException("该用户名已被使用");
		}
		db.beginTransaction();
		long count = db.insert("t_user", null, values);
		db.endTransaction();
		if (count > 0)
			result = true;
		return result;
	}

	public static boolean isAvailableUser(SQLiteDatabase db, String username) {
		boolean result = false;

		long count = 0;
		Cursor cur = db.query("t_user", new String[] { "id" }, "username=?",
				new String[] { username }, null, null, null);
		count = cur.getColumnCount();
		if (count > 0)
			return false;
		else
			return true;
	}
}

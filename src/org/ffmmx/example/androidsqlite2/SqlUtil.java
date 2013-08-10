package org.ffmmx.example.androidsqlite2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlUtil extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "sqlite_test";
	private static final int DATABASE_VERSION = 1;
	private static final String CREATE_SQL = "create table t_note (id integer "
			+ "not null primary key autoincrement,"
			+ "subject varchar(200) not null," + "content varchar(1000),"
			+ "create_time date not null," + "tartget_date date not null,"
			+ "userid integer references t_user(id));";
	private static final String CREATE_SQL_TABLE_USER = "create table t_user("
			+ " id integer not null primary key autoincrement,"
			+ "username varchar(20) not null,"
			+ "password varchar(20) not null," + "name varchar(20) not null,"
			+ "email varchar(50)," + "birth date)";
	private static final String UPGRADE_SQL = "";

	public SqlUtil(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public SqlUtil(Context context, CursorFactory factory) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	public SqlUtil(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_SQL_TABLE_USER);
		db.execSQL(CREATE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL(UPGRADE_SQL);
	}

}

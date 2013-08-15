package org.ffmmx.example.androidsqlite2.activity

import org.ffmmx.example.androidsqlite2.R
import org.ffmmx.example.androidsqlite2.business.Login
import org.ffmmx.example.androidsqlite2.common.Widgets._
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import org.ffmmx.example.androidsqlite2.common.SqlUtil
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast

class LoginActivity extends RichActivity {
	override def onCreate(savedInstanceState: Bundle): Unit = {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)

		if (Login.isLogon()) {
			val intent = new Intent(LoginActivity.this, classOf[NoteActivity])
			startActivity(intent)
			finish()
		}
		val sqlUtil = new SqlUtil(LoginActivity.this)
		val usernameEdit = findViewById(R.id.usernameEdit).asInstanceOf[EditText]
		val passwordEdit = findViewById(R.id.passwordEdit).asInstanceOf[EditText]
		val loginBtn = findViewById(R.id.loginBtn).asInstanceOf[RichButton]
		val registerBtn = findViewById(R.id.registerBtn).asInstanceOf[RichButton]

		loginBtn.onClick({
			val db = sqlUtil.getReadableDatabase()

			try {
				val result = Login.login(db, usernameEdit.getText().toString(),
					passwordEdit.getText().toString())
				if (result) {
					val intent = new Intent(LoginActivity.this,
						classOf[NoteActivity])
					startActivity(intent)
					finish()
				} else {
					Toast.makeText(LoginActivity.this, "用户名不存在或者密码错误",
						Toast.LENGTH_LONG).show()
				}
			} finally {
				db.close()
			}

		})

		registerBtn.onClick({
			val intent = new Intent(LoginActivity.this, classOf[RegisterActivity])
			startActivity(intent)
		})
	}
}
package org.ffmmx.example.androidsqlite2.activity

import android.app.Activity
import android.os.Bundle
import org.ffmmx.example.androidsqlite2.R
import android.widget.EditText
import android.widget.Button
import android.view.View.OnClickListener
import android.view.View
import android.content.Intent

class LoginActivity extends Activity {
  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    val usernameEdit = findViewById(R.id.usernameEdit).asInstanceOf[EditText]
    val passwordEdit = findViewById(R.id.passwordEdit).asInstanceOf[EditText]
    val loginBtn = findViewById(R.id.loginBtn).asInstanceOf[Button]
    val registerBtn = findViewById(R.id.registerBtn).asInstanceOf[Button]

    val clickListener = new OnClickListener() {
      override def onClick(v: View): Unit = {
        v.getId() match {
          case R.id.loginBtn => {
            val intent = new Intent()
            intent.setClass(LoginActivity.this, NoteActivity)
            startActivity(intent)
          }
          case R.id.registerBtn =>
        }
      }
    }
    loginBtn.setOnClickListener(clickListener)
  }

}
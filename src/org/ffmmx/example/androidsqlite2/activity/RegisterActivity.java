package org.ffmmx.example.androidsqlite2.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.ffmmx.example.androidsqlite2.R;
import org.ffmmx.example.androidsqlite2.business.Register;
import org.ffmmx.example.androidsqlite2.common.SqlUtil;
import org.ffmmx.example.androidsqlite2.domain.User;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private EditText usernameEdit, passwordEdit, passwordRepeatEdit, emailEdit,
			birthEdit, nameEdit;
	private Button registerBtn, birthSelectBtn;

	private SQLiteOpenHelper util;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.register);

		util = new SqlUtil(RegisterActivity.this);
		usernameEdit = (EditText) this.findViewById(R.id.regUsernameEdit);
		passwordEdit = (EditText) this.findViewById(R.id.regPasswordEdit);
		passwordRepeatEdit = (EditText) this
				.findViewById(R.id.regPasswordRepeatEdit);
		emailEdit = (EditText) this.findViewById(R.id.regEmailEdit);
		birthEdit = (EditText) this.findViewById(R.id.regBirthEdit);
		nameEdit = (EditText) this.findViewById(R.id.regNameEdit);

		registerBtn = (Button) this.findViewById(R.id.regRegisterBtn);
		birthSelectBtn = (Button) this.findViewById(R.id.regDateSelectBtn);
		RegisterButtonListener btnClickListener = new RegisterButtonListener();
		registerBtn.setOnClickListener(btnClickListener);
		birthSelectBtn.setOnClickListener(btnClickListener);

	}

	class RegisterButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.regRegisterBtn:
				User user = new User();
				user.setUsername(usernameEdit.getText().toString());
				user.setPassword(passwordEdit.getText().toString());
				user.setPasswordRepeat(passwordRepeatEdit.getText().toString());
				user.setEmail(emailEdit.getText().toString());
				user.setName(nameEdit.getText().toString());

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					user.setBirth(sdf.parse(birthEdit.getText().toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					System.exit(-1);
				}

				if (!validate(user)) {
					break;
				}
				SQLiteDatabase db = util.getWritableDatabase();
				boolean result = false;
				try {
					result = Register.register(db, user);
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(RegisterActivity.this, e.getMessage(),
							Toast.LENGTH_LONG).show();
				} finally {
					db.close();
				}
				if (result) {
					finish();
				}
				break;
			case R.id.regDateSelectBtn:
				Calendar now = Calendar.getInstance();
				now.setTime(new Date());
				DatePickerDialog datePickerDialog = new DatePickerDialog(
						RegisterActivity.this, new OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								Calendar selectCal = Calendar.getInstance();
								selectCal.set(year, monthOfYear, dayOfMonth);
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy-MM-dd");
								birthEdit.setText(sdf.format(selectCal
										.getTime()));
							}
						}, now.get(Calendar.YEAR), now.get(Calendar.MONTH),
						now.get(Calendar.DAY_OF_MONTH));
				datePickerDialog.show();
				break;
			default:
				break;
			}
		}
	}

	private boolean validate(User user) {
		if (user.getUsername() == null || "".equals(user.getUsername())) {
			usernameEdit.forceLayout();
			Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_LONG)
					.show();
			;
			return false;
		}
		if (user.getPassword() == null || "".equals(user.getPassword())) {
			passwordEdit.forceLayout();
			Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_LONG)
					.show();
			return false;
		}
		if (user.getPasswordRepeat() == null
				|| "".equals(user.getPasswordRepeat())) {
			passwordRepeatEdit.forceLayout();
			Toast.makeText(RegisterActivity.this, "密码重复不能为空", Toast.LENGTH_LONG)
					.show();
			return false;
		}
		if (!user.getPassword().equals(user.getPasswordRepeat())) {
			passwordRepeatEdit.findFocus();
			Toast.makeText(RegisterActivity.this, "两次输入不一致", Toast.LENGTH_LONG)
					.show();
			;
			return false;
		}
		if (user.getEmail() == null || "".equals(user.getEmail())) {
			emailEdit.forceLayout();
			Toast.makeText(RegisterActivity.this, "邮箱不能为空", Toast.LENGTH_LONG)
					.show();
			return false;
		}
		if (user.getBirth() == null) {
			birthEdit.forceLayout();
			Toast.makeText(RegisterActivity.this, "生日不能为空", Toast.LENGTH_LONG)
					.show();
			return false;
		}
		return true;
	}
}

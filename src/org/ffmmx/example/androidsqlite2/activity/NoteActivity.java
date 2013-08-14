package org.ffmmx.example.androidsqlite2.activity;

import org.ffmmx.example.androidsqlite2.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class NoteActivity extends Activity {
	private EditText subjectEdit, contentEdit, noteTimeEdit;
	private Button dateBtn, addNoteBtn;
	private ListView noteListView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_activity);

		subjectEdit = (EditText) findViewById(R.id.note_subjectEdit);
		contentEdit = (EditText) findViewById(R.id.note_contentEdit);
		dateBtn = (Button) findViewById(R.id.note_dateSelectBtn);
		addNoteBtn = (Button) findViewById(R.id.note_addNoteBtn);
		noteListView = (ListView) findViewById(R.id.note_noteList);

	}

}

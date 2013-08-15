package org.ffmmx.example.androidsqlite2.activity

import android.app.Activity
import android.os.Bundle
import org.ffmmx.example.androidsqlite2.R
import android.widget.EditText
import org.ffmmx.example.androidsqlite2.common.Widgets._

class NoteActivity extends RichActivity {
	override def onCreate(savedInstanceState: Bundle): Unit = {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.note_activity)

		val subjectEdit = getById[EditText](R.id.note_subjectEdit)
		val contentEdit = getById[EditText](R.id.note_contentEdit)
		val noteTimeEdit = getById[EditText](R.id.note_noteTime)
		val dateBtn = getById[RichButton](R.id.note_dateSelectBtn)
		val addNoteBtn = getById[RichButton](R.id.note_addNoteBtn)

	}
}
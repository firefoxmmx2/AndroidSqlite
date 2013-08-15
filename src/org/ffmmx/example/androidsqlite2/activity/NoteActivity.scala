package org.ffmmx.example.androidsqlite2.activity

import android.app.Activity
import android.os.Bundle
import org.ffmmx.example.androidsqlite2.R
import android.widget.EditText
import org.ffmmx.example.androidsqlite2.common.Widgets._
import java.util.Calendar
import java.util.Date
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.widget.DatePicker
import java.text.SimpleDateFormat
import android.widget.ListView
import android.widget.SimpleAdapter
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

class NoteActivity extends RichActivity {
  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.note_activity)

    val subjectEdit = getById[EditText](R.id.note_subjectEdit)
    val contentEdit = getById[EditText](R.id.note_contentEdit)
    val noteTimeEdit = getById[EditText](R.id.note_noteTime)
    val dateBtn = getById[RichButton](R.id.note_dateSelectBtn)
    val addNoteBtn = getById[RichButton](R.id.note_addNoteBtn)
    val noteListView = getById[ListView](R.id.note_noteList)

    dateBtn.onClick({
      val now = Calendar.getInstance()
      now.setTime(new Date)
      val datapickDialog = new DatePickerDialog(NoteActivity.this, new OnDateSetListener() {
        override def onDateSet(view: DatePicker, year: Int,
                               month: Int, day: Int): Unit = {
          val sdf = new SimpleDateFormat("yyyy-MM-dd")
          val selectDateCal = Calendar.getInstance()
          selectDateCal.set(year, month, day)
          noteTimeEdit.setText(sdf.format(selectDateCal.getTime()))
        }
      }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
    })

    //初始化列表
    val noteList = ListBuffer[Map[String, Any]]()

    val simpleAdapter = new SimpleAdapter(NoteActivity.this, noteList.,
      R.layout.note_list,
      Array("itemSubject", "itemContent", "itemNotetime"),
      Array(R.id.notelist_itemSubject, R.id.notelist_itemContent,
        R.id.notelist_itemNotetime))
  }
}
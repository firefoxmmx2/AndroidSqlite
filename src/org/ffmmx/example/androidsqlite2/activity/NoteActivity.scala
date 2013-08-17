package org.ffmmx.example.androidsqlite2.activity

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.HashMap

import scala.collection.mutable.Map

import org.ffmmx.example.androidsqlite2.R
import org.ffmmx.example.androidsqlite2.business.Note
import org.ffmmx.example.androidsqlite2.common.SqlUtil
import org.ffmmx.example.androidsqlite2.common.Widgets._

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleAdapter

class NoteActivity extends RichActivity {
  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.note_activity)

    val sqlUtil = new SqlUtil(NoteActivity.this)
    val subjectEdit = getById[EditText](R.id.note_subjectEdit)
    val contentEdit = getById[EditText](R.id.note_contentEdit)
    val noteTimeEdit = getById[EditText](R.id.note_noteTime)
    val dateBtn = getById[Button](R.id.note_dateSelectBtn)
    val addNoteBtn = getById[Button](R.id.note_addNoteBtn)
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
    val noteListViewData = new java.util.ArrayList[java.util.Map[String, Object]]()
    val db = sqlUtil.getReadableDatabase()
    try {
      val notelist = Note.all(db)
      notelist.foreach(note => {
        val map = new HashMap[String, Object]()
        map.put("itemId", new Integer(note.id))
        map.put("itemSubject", note.subject)
        map.put("itemContent", note.content)
        map.put("itemNotetime", note.noteDate)
        noteListViewData.add(map)
      })
    } catch {
      case t: Throwable => println(t)
    } finally {
      db.close()
    }

    val simpleAdapter = new SimpleAdapter(NoteActivity.this, noteListViewData,
      R.layout.note_list,
      Array("itemSubject", "itemContent", "itemNotetime"),
      Array(R.id.notelist_itemSubject, R.id.notelist_itemContent,
        R.id.notelist_itemNotetime))
  }
}
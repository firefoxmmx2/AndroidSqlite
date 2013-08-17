package org.ffmmx.example.androidsqlite2.business

import android.database.sqlite.SQLiteDatabase
import scala.collection.mutable.ListBuffer
import java.text.SimpleDateFormat
import android.content.ContentValues
import java.util.Date

object Note {
  def all(db: SQLiteDatabase): List[org.ffmmx.example.androidsqlite2.domain.Note] = {
    val cur = db.query("t_note", Array("id", "subject", "content", "tartget_date", "userid"),
      null,
      null,
      null,
      null,
      null)
    val list = ListBuffer[org.ffmmx.example.androidsqlite2.domain.Note]()
    val sdf = new SimpleDateFormat("yyyy-MM-dd")
    while (cur.moveToNext()) {
      list += org.ffmmx.example.androidsqlite2.domain.Note(cur.getInt(0),
        cur.getString(1),
        cur.getString(2),
        sdf.parse(cur.getString(3)),
        cur.getInt(4))
    }
    list.toList
  }

  def add(db: SQLiteDatabase, note: org.ffmmx.example.androidsqlite2.domain.Note) = {
    db.beginTransaction()
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
    val timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val values = new ContentValues
    values.put("subject", note.subject)
    values.put("content", note.content)
    values.put("create_time", timeFormat.format(new Date()))
    values.put("tartget_date", dateFormat.format(note.noteDate))
    values.put("userid", new Integer(note.userid))

    val result = db.insert("t_note", null, values) > 0
    if (result)
      db.setTransactionSuccessful()
    db.endTransaction()
    result
  }
}
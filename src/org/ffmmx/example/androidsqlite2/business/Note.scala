package org.ffmmx.example.androidsqlite2.business

import android.database.sqlite.SQLiteDatabase
import scala.collection.mutable.ListBuffer
import java.text.SimpleDateFormat

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
}
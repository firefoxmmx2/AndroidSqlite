package org.ffmmx.example.androidsqlite2.common

import android.widget.Button
import android.view.View
import android.app.Activity

object Widgets {
  trait RichButton extends Button {
    def onClick(handler: => Any) = {
      this.setOnClickListener(new View.OnClickListener() {
        override def onClick(v: View): Unit = {
          handler
        }
      })
    }
  }

  trait RichActivity extends Activity {
    def getById[T <: View](id: Int): T = {
      this.findViewById(id).asInstanceOf[T]
    }
  }
}
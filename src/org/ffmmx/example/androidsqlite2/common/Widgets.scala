package org.ffmmx.example.androidsqlite2.common

import android.widget.Button
import android.view.View
import android.app.Activity

object Widgets {
  implicit def button2RichButton(button: Button) = {
    new {
      def onClick(handler: => Any) = {
        button.setOnClickListener(new View.OnClickListener() {
          override def onClick(v: View): Unit = {
            handler
          }
        })
      }
    }
  }

  trait RichActivity extends Activity {
    def getById[T <: View](id: Int): T = {
      this.findViewById(id).asInstanceOf[T]
    }
  }
}
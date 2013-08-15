package org.ffmmx.example.androidsqlite2.domain

import java.util.Date

case class Note(id: Int = 0, subject: String, content: String, noteDate: Date, userid: Int) {

}
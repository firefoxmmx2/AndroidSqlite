package org.ffmmx.example.androidsqlite2.domain

import java.util.Date
import scala.beans.BeanProperty

case class User2(@BeanProperty id: Int = 0,
	@BeanProperty username: String,
	@BeanProperty name: String,
	@BeanProperty password: String,
	@BeanProperty passwordRepeat: Option[String],
	@BeanProperty birth: Date) {

}
package org.ffmmx.example.androidsqlite2.domain;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8637338281949641959L;

	private Integer id, userid;
	private String subject, content;
	private Date targetDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

}

package com.accenture.codingtest.springbootcodingtest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TaskStatusMD implements Serializable{


	private static final long serialVersionUID = -6328607138767139580L;
	@Id
	@Column(name = "sid")
	private int sid;

	@Column(name = "status")
	private String status;

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TaskStatusMD(int sid, String status) {
		super();
		this.sid = sid;
		this.status = status;
	}

	public TaskStatusMD() {
		super();
		// TODO Auto-generated constructor stub
	}
}
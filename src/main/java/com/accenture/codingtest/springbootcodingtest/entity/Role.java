package com.accenture.codingtest.springbootcodingtest.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;


public class Role implements Serializable{


		private static final long serialVersionUID = 1898433577523417600L;

		@Id
		@Column(name = "id")
		private UUID id;

		@Column(name = "username")
		private String username;
		

		public UUID getId() {
			return id;
		}

		public void setId(UUID id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public Role(UUID id, String username) {
			super();
			this.id = id;
			this.username = username;
		}

}

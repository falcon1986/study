package com.wwq.tank.entity;

import java.util.UUID;

public class Group {

	private String id;
	
	public Group() {
		id = UUID.randomUUID().toString().replaceAll("-","");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
}

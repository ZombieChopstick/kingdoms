package com.zombiechopstick.kingdoms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import com.zombiechopstick.kingdoms.components.Component;

public class Entity implements Serializable {

	private static final long serialVersionUID = 5430522519570524233L;
	private String uid;
	private ArrayList<Component> components = new ArrayList<Component>();
	
	public Entity(String uid, Component...components) {
		this.setUid(uid);
		for(Component c : components) {
			if(!this.components.contains(c)) {
				this.components.add(c);
			}
		}
	}

	public Entity(UUID uid) {
		this.uid = uid.toString();
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
}
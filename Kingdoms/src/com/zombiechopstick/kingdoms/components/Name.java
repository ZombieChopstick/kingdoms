package com.zombiechopstick.kingdoms.components;

public class Name implements Component {

	private static final long serialVersionUID = 6356251433740641497L;
	private String name;
	
	public Name(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}

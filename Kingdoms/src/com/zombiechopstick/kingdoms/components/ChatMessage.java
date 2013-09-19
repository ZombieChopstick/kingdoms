package com.zombiechopstick.kingdoms.components;

public class ChatMessage implements Component {

	private static final long serialVersionUID = -7450818928166477765L;
	private String message;
	private String owner;
	
	public ChatMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}

}

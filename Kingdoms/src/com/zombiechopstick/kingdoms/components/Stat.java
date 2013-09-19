package com.zombiechopstick.kingdoms.components;

public class Stat implements Component {

	private static final long serialVersionUID = 5422127079928320716L;
	private String name;
	private int value;
	
	public Stat(String name, int intialValue) {
		this.name = name;
		this.value = intialValue;
	}
	
	public void setValue(int newValue) {
		this.value += newValue;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
	public String toString() {
		return "ST(" + name + "," + value + ")";
	}
}

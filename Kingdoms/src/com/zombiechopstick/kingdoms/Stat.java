package com.zombiechopstick.kingdoms;

public class Stat {
	private String name;
	private int value;
	
	public Stat(String name, int intialValue) {
		this.name = name;
		this.value = intialValue;
	}
	
	public void adjustStat(int newValue) {
		this.value += newValue;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
}

package com.zombiechopstick.kingdoms;

public class StatModifier {
	private String stat;
	private int amount;
	
	public StatModifier(String stat, int amount) {
		this.stat = stat;
		this.amount = amount;
	}
	
	public String getStat() {
		return stat;
	}
	
	public int getAmount() {
		return amount;
	}
}
package com.zombiechopstick.kingdoms.components;

public class StatModifier implements Component {

	private static final long serialVersionUID = 2332171764279550626L;
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
	
	public void setStat(String name) {
		stat = name;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
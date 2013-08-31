package com.zombiechopstick.kingdoms;

import java.util.ArrayList;

public class Card {
	private String name;
	private ArrayList<StatModifier> modifiers = new ArrayList<StatModifier>();
	
	public Card(String name, StatModifier...modifiers) {
		this.name = name;
		for(StatModifier mod : modifiers) {
			this.modifiers.add(mod);
		}
	}
	
	public void addStatModifier(StatModifier mod) {
		modifiers.add(mod);
	}
	
	public ArrayList<StatModifier> getStatModifiers() {
		return modifiers;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		String rep = "-Name: " + name + "\n-Modifiers: ";
		for(StatModifier sm : modifiers) {
			rep+=sm.getStat() + " = " + sm.getAmount() + "\n";
		}
		return rep;
	}
	
	public void fuse(Card c) {
		for(StatModifier mod : c.getStatModifiers()) {
			modifiers.add(mod);
		}
		setName(name + " " + c.getName());
	}
}

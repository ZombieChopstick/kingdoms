package com.zombiechopstick.kingdoms.oop;

import java.util.HashMap;

import com.zombiechopstick.kingdoms.components.Stat;

public class Character {
	private String name;
	private HashMap<String,Stat> stats = new HashMap<String,Stat>();
	
	public Character(String name) {
		this.name = name;
		Stat health = new Stat("Health",50);
		Stat attack = new Stat("Attack",20);
		stats.put(health.getName(),health);
		stats.put(attack.getName(),attack);
	}
	
	public void addStat(Stat s) {
		stats.put(s.getName(), s);
	}
	
	public void attack(Character...targets) {
		for(Character enemy : targets) {
			enemy.getStat("Health").setValue(-getStat("Attack").getValue());
		}
	}
	
	public String getName() {
		return name;
	}
	
	public Stat getStat(String name) {
		for(Stat s : stats.values()) {
			if(name.equals(s.getName())) {
				return s;
			}
		}
		return null;
	}
	
	public String toString() {
		String rep = "-Name: " + name + "\n-Stats: ";
		for(Stat s : stats.values()) {
			rep+= "[" + s.getName() + " = " + s.getValue() + "]";
		}
		return rep;
	}
}

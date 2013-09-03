package com.zombiechopstick.kingdoms.oop;

import com.zombiechopstick.kingdoms.components.StatModifier;

public class Tester {
	public static void main(String[] args) {
		System.out.println("#Kingdoms Alpha Build#\n");
		Tester testCase = new Tester();
		testCase.testCase1();
	}
	
	Card potionCard, attackBoostCard;
	Character leader1, leader2;
	
	public Tester() {
		addCards();
		addCharacters();
	}
	
	public void addCards() {
		System.out.println("Adding Cards:");
		potionCard = new Card("Potion",new StatModifier("Health",20));
		attackBoostCard = new Card("Attack Boost",new StatModifier("Attack",10));
		System.out.println(potionCard.toString());
		System.out.println(attackBoostCard.toString());
	}
	
	public void addCharacters() {
		System.out.println("Adding Characters:");
		leader1 = new Character("Leader 1");
		leader2 = new Character("Leader 2");
		System.out.println(leader1.toString());
		System.out.println(leader2.toString());
	}
	
	public void testCase1() {
		System.out.println("==========================================");
		System.out.println("Applying Potion to Leader 1");
		for(StatModifier mod : potionCard.getStatModifiers()) {
			leader1.getStat(mod.getStat()).setValue(mod.getAmount());
		}
		System.out.println(leader1.toString());
		System.out.println("==========================================");
		System.out.println("Applying Potion to Leader 2");
		for(StatModifier mod : potionCard.getStatModifiers()) {
			leader2.getStat(mod.getStat()).setValue(mod.getAmount());
		}
		System.out.println(leader2.toString());
		System.out.println("==========================================");
		System.out.println("Applying Attack Boost to Leader 1");
		for(StatModifier mod : attackBoostCard.getStatModifiers()) {
			leader1.getStat(mod.getStat()).setValue(mod.getAmount());
		}
		System.out.println(leader1.toString());
		System.out.println("==========================================");
		System.out.println("Fusing Potion Card with Attack Boost Card");
		potionCard.fuse(attackBoostCard);
		System.out.println("==========================================");
		System.out.println("Applying " + potionCard.getName() + " to Leader 2");
		for(StatModifier mod : potionCard.getStatModifiers()) {
			leader2.getStat(mod.getStat()).setValue(mod.getAmount());
		}
		System.out.println(leader2.toString());
		System.out.println("==========================================");
		System.out.println("Leader 2 attacks Leader 1");
		leader2.attack(leader1);
		System.out.println(leader1.toString());
		System.out.println(leader2.toString());
	}
}

package com.zombiechopstick.kingdoms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import com.zombiechopstick.kingdoms.components.*;

public class EntityGeneratorSystem {
	
	private EntityManager manager;
	
	public EntityGeneratorSystem(EntityManager manager) {
		this.manager = manager;
	}
	
	public void generateLocalEntities(String player) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(player.replaceAll("[ ]", "") + "_entities.txt"));
			//BufferedReader br = new BufferedReader(new FileReader("entities.txt"));
			List<UUID> entities = new ArrayList<UUID>();
			String entity = null;
			while((entity = br.readLine()) != null) {
				String[] components = entity.split("[|]");
				if(components[0].equals("E")) {
					System.out.println("#New Entity#");
					UUID newEntity = manager.createEntity();
					entities.add(newEntity);
					if(!components[1].equals("")) {
						System.out.println("Name: " + components[1]);
						manager.addComponents(newEntity, new Name(components[1]));
					}
					System.out.println("Components:");
					int count = 0;
					while(count < Integer.parseInt(components[2])) {
						System.out.println(components[3+count]);
						String com = components[3+count].substring(0, 2);
						if(com.equals("SM")) {
							//StatModifier
							String[] args1 = components[3+count].substring(3,components[3+count].length()-1).split("[,]");
							System.out.println(args1[0]);
							System.out.println(args1[1]);
							manager.addComponents(newEntity, new StatModifier(args1[0],Integer.parseInt(args1[1])));
						}
						else if(com.equals("ST")) {
							//Stat
							String[] args1 = components[3+count].substring(3,components[3+count].length()-1).split("[,]");
							System.out.println(args1[0]);
							System.out.println(args1[1]);
							manager.addComponents(newEntity, new Stat(args1[0],Integer.parseInt(args1[1])));
						}
						count++;
					}
					System.out.println("Texture: " + components[3+count]);
					manager.addComponents(newEntity, new Renderable(components[3+count]));
					if(components[4+count].equals("Card")) {
						System.out.println("Dragable Card");
						manager.addComponents(newEntity, new Card(true,Card.PlayState.INHAND));
						manager.addComponents(newEntity, new Dragable());
						//manager.addComponents(newEntity, new Position(0,0));
					}
					System.out.println("Entity Owner: " + components[5+count]);
					manager.addComponents(newEntity, new Owner(components[5+count]));
					manager.addComponents(newEntity, new User());
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generateLocalEntities(List<String> entities) {
		System.out.println(entities.size());
			for(String s : entities) {
				String[] components = s.split("[|]");
				UUID newEntity = UUID.fromString(components[0]);
				manager.addEntity(UUID.fromString(components[0]));
				
				if(!components[1].equals("")) {
					manager.addComponents(newEntity, new Name(components[1]));
				}
				int count = 0;
				while(count < Integer.parseInt(components[2])) {
					String com = components[3+count].substring(0, 2);
					if(com.equals("SM")) {
						//StatModifier
						String[] args1 = components[3+count].substring(3,components[3+count].length()-1).split("[,]");
						manager.addComponents(newEntity, new StatModifier(args1[0],Integer.parseInt(args1[1])));
					}
					else if(com.equals("ST")) {
						//Stat
						String[] args1 = components[3+count].substring(3,components[3+count].length()-1).split("[,]");
						manager.addComponents(newEntity, new Stat(args1[0],Integer.parseInt(args1[1])));
					}
					count++;
				}
				manager.addComponents(newEntity, new Renderable(components[3+count]));
				if(components[4+count].equals("Card")) {
					manager.addComponents(newEntity, new Card(true,Card.PlayState.INHAND));
					manager.addComponents(newEntity, new Dragable());
					//manager.addComponents(newEntity, new Position(0,0));
				}
				System.out.println("Entity Owner: " + components[5+count]);
				manager.addComponents(newEntity, new Owner(components[5+count]));
				manager.addComponents(newEntity, new User());
			}
	}
	
	public List<String> generateRemoteEntities3() {
		List<String> entities = new ArrayList<String>();
		for(UUID uid : manager.getAllEntitiesWithComponent(User.class)) {
			String entity = uid + "";
			Component name = manager.getComponent(uid, Name.class);
			HashSet<Component> stats = (HashSet<Component>) manager.getComponents(uid, Stat.class);
			HashSet<Component> statModifiers = (HashSet<Component>) manager.getComponents(uid, StatModifier.class);
			Component texture = manager.getComponent(uid, Renderable.class);
			Component card = manager.getComponent(uid, Card.class);
			Component owner = manager.getComponent(uid, Owner.class);
			if(name!=null) entity += "|" + name.toString();
			
			if(stats!=null && stats.size() > 0) {
				entity += "|" + stats.size();
				for(Component c : stats) {
					entity += "|" + c.toString();
				}
			}
			
			if(statModifiers!=null && statModifiers.size() > 0) {
				entity += "|" + statModifiers.size();
				for(Component c : statModifiers) {
					entity += "|" + c.toString();
				}
			}
			
			if(texture!=null) entity += "|" + texture.toString();
			if(card!=null) { 
				entity += "|" + card.toString();
			} else entity+="|";
			if(owner!=null) entity += "|" + owner.toString();
			System.out.println(entity);
			entities.add(entity);
		}
		return entities;
	}
}

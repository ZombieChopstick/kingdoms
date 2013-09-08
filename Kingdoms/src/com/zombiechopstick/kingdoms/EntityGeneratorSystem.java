package com.zombiechopstick.kingdoms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.badlogic.gdx.Gdx;
import com.zombiechopstick.kingdoms.components.*;

public class EntityGeneratorSystem {
	
	private EntityManager manager;
	//private Socket server;
	
	public EntityGeneratorSystem(EntityManager manager) {
		this.manager = manager;
	}
	
	public void generateLocalEntities() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(Gdx.files.getLocalStoragePath() + "entities.txt"));
			List<UUID> entities = new ArrayList<UUID>();
			//System.out.println(br.readLine());
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
						manager.addComponents(newEntity, new Dragable());
						manager.addComponents(newEntity, new Position(0,0));
					}
					System.out.println("Entity Owner: " + components[5+count]);
					manager.addComponents(newEntity, new Owner(components[5+count]));
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generateRemoteEntities(RemoteConnectionSystem remote) {
		if(remote.getRemoteEntities().size() > 0) {
			System.out.println("Generating Remote Entities");
		}
	}
}

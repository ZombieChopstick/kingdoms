package com.zombiechopstick.kingdoms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.zombiechopstick.kingdoms.components.Component;

public class EntityManager implements Serializable {

	private static final long serialVersionUID = 8678062369251796238L;
	private ArrayList<UUID> allEntities;
	//private HashMap<Class<? extends Component>, HashMap<UUID, Component>> allComponents;
	private HashMap<Class<? extends Component>, HashMap<Component, UUID>> allComponents;

	public EntityManager() {
		allEntities = new ArrayList<UUID>();
		//allComponents = new HashMap<Class<? extends Component>, HashMap<UUID, Component>>();
		allComponents = new HashMap<Class<? extends Component>, HashMap<Component, UUID>>();
	}

	public UUID createEntity() {
		UUID uid = UUID.randomUUID();
		allEntities.add(uid);
		return uid;
	}
	
	public void addEntity(UUID uid) {
		allEntities.add(uid);
	}

	public void addComponents(UUID uid, Component... components) {
		for (Component c : components) {
			if (c != null) {
				//HashMap<UUID, Component> _components;
				HashMap<Component, UUID> _components;

				if (allComponents.containsKey(c.getClass())) {
					_components = allComponents.get(c.getClass());
				} else {
					//_components = new HashMap<UUID, Component>();
					_components = new HashMap<Component, UUID>();
					allComponents.put(c.getClass(), _components);
				}

				//_components.put(uid, c);
				_components.put(c, uid);
			}
		}
	}

	public List<UUID> getAllEntitiesWithComponent(
			Class<? extends Component> type) {
		List<UUID> entities = new ArrayList<UUID>();
		
		if (type != null && allComponents.containsKey(type)) {
			//entities.addAll(allComponents.get(type).keySet());
			entities.addAll(allComponents.get(type).values());
		}

		return entities;
	}

	public List<UUID> getAllEntitiesWithComponents(
			List<Class<? extends Component>> types) {
		final List<UUID> entities = new ArrayList<UUID>();

		for (Class<? extends Component> t : types) {
			if (t != null) {
				List<UUID> tmp = getAllEntitiesWithComponent(t);

				if (tmp.isEmpty()) {
					entities.clear();
					break;
				} else if (entities.isEmpty()) {
					entities.addAll(tmp);
				} else {
					entities.retainAll(tmp);
				}
			}
		}

		return entities;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(UUID id, Class<T> type) {
			T result = null;

			if (type != null && allComponents.containsKey(type)) {
				HashMap<Component, UUID> components = allComponents.get(type);

				/*if (components.containsKey(id)) {
					result = (T) components.get(id);
				}*/
				
				for (Entry<Component, UUID> entry : components.entrySet()) {
			        if (id.equals(entry.getValue())) {
			        	result = (T) entry.getKey();
			        }
			    }
			}

			return result;
	}

	
	//public <T extends Component> T getComponent(UUID id, Class<T> type) {
	public <T extends Component> Set<Component> getComponents(UUID id, Class<T> type) {
		//T result = null;
		Set<Component> result = null;

		if (type != null && allComponents.containsKey(type)) {
			//HashMap<UUID, Component> components = allComponents.get(type);
			HashMap<Component, UUID> components = allComponents.get(type);

			//if (components.containsKey(id)) {
			//	result = (T) components.get(id);
			//}
			
			result = new HashSet<Component>();
			for (Entry<Component, UUID> entry : components.entrySet()) {
		         if (id.equals(entry.getValue())) {
		             result.add(entry.getKey());
		         }
		     }
		     return result;
		}

		return result;
	}
	
	/*public List<Component> getEntityComponents(UUID id) {
		List<Component> components = new ArrayList<Component>();
		//for(HashMap<UUID, Component> type : allComponents.values()) {
		for(HashMap<Component, UUID> type : allComponents.values()) {
			Set<UUID> uids = type.keySet();
			if(uids.contains(id)) {
				System.out.println(type.get(id).toString());
				components.add(type.get(id));
			}
		}
		return components;
	}*/
	
	public List<UUID> getAllEntities() {
		return allEntities;
	}
	
	public Collection<HashMap<Component, UUID>> getAllComponents() {
		return allComponents.values();
	}
}

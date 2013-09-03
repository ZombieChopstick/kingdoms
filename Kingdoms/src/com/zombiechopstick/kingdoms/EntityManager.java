package com.zombiechopstick.kingdoms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.zombiechopstick.kingdoms.components.Component;

public class EntityManager implements Serializable {

	private static final long serialVersionUID = 8678062369251796238L;
	private ArrayList<UUID> allEntities;
	private HashMap<Class<? extends Component>, HashMap<UUID, Component>> allComponents;

	public EntityManager() {
		allEntities = new ArrayList<UUID>();
		allComponents = new HashMap<Class<? extends Component>, HashMap<UUID, Component>>();
	}

	public UUID createEntity() {
		UUID uid = UUID.randomUUID();
		allEntities.add(uid);
		return uid;
	}

	public void addComponents(UUID uid, Component... components) {
		for (Component c : components) {
			if (c != null) {
				HashMap<UUID, Component> _components;

				if (allComponents.containsKey(c.getClass())) {
					_components = allComponents.get(c.getClass());
				} else {
					_components = new HashMap<UUID, Component>();
					allComponents.put(c.getClass(), _components);
				}

				_components.put(uid, c);
			}
		}
	}

	public List<UUID> getAllEntitiesWithComponent(
			Class<? extends Component> type) {
		List<UUID> entities = new ArrayList<UUID>();

		if (type != null && allComponents.containsKey(type)) {
			entities.addAll(allComponents.get(type).keySet());
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
			HashMap<UUID, Component> components = allComponents.get(type);

			if (components.containsKey(id)) {
				result = (T) components.get(id);
			}
		}

		return result;
	}
}

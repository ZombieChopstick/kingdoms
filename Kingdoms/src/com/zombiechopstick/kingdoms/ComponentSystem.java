package com.zombiechopstick.kingdoms;

import java.io.Serializable;

public interface ComponentSystem extends Serializable {

	static final long serialVersionUID = -6129045885429178366L;
	
	void update(int delta);
	void addManager(EntityManager manager);
}

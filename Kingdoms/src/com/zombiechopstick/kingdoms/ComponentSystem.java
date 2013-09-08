package com.zombiechopstick.kingdoms;

import java.io.Serializable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ComponentSystem extends Serializable {

	static final long serialVersionUID = -6129045885429178366L;

	void addManager(EntityManager manager);
	void update(float delta, SpriteBatch batch);
}

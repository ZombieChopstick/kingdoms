package com.zombiechopstick.kingdoms;

import java.util.List;
import java.util.UUID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zombiechopstick.kingdoms.components.Position;
import com.zombiechopstick.kingdoms.components.Renderable;
import com.zombiechopstick.kingdoms.components.Size;

public class RenderSystem implements ComponentSystem {

	private static final long serialVersionUID = -126108005435048472L;
	private EntityManager manager;
	private AssetManager assetManager;
	
	//Temporary Debugging
	private BitmapFont font = new BitmapFont();
	
	public RenderSystem(EntityManager manager) {
		this.manager = manager;
		this.assetManager = new AssetManager();
		Texture.setEnforcePotImages(false);
		assetManager.load("data/potioncard.png", Texture.class);
		assetManager.load("data/attackboostcard.png", Texture.class);
		assetManager.load("data/dwarfleader.png", Texture.class);
		assetManager.load("data/centaurleader.png", Texture.class);
		assetManager.load("data/deck.png", Texture.class);
		assetManager.load("data/hex.png",Texture.class);
		assetManager.load("data/hexhover.png",Texture.class);
		assetManager.finishLoading();
	}
	
	@Override
	public void update(float delta, SpriteBatch batch) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		List<UUID> entities = manager.getAllEntitiesWithComponent(Renderable.class);

		for(UUID uid : entities) {
			Renderable texture = manager.getComponent(uid, Renderable.class);
			Position position = manager.getComponent(uid, Position.class);
			Size size = manager.getComponent(uid, Size.class);
			if(texture != null && position != null) {
				batch.draw(assetManager.get("data/" + texture.getSprite(),Texture.class), position.getX(), position.getY(), size.getWidth(), size.getHeight());
			}
		}
		//Temporary Debugging
		font.setColor(Color.WHITE);
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 20,Gdx.graphics.getHeight() - 10);
		batch.end();
	}

	@Override
	public void addManager(EntityManager manager) {
		this.manager = manager;
	}	
}

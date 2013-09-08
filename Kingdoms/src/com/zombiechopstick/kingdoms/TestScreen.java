package com.zombiechopstick.kingdoms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestScreen implements Screen {
	private SpriteBatch batch;
	private EntityManager manager = new EntityManager();
	private EntityGeneratorSystem entities = new EntityGeneratorSystem(manager);
	private RenderSystem render = new RenderSystem(manager);
	private InputSystem input = new InputSystem(manager);
	private DeckSystem deck;
	private HandSystem hand;
	private RemoteConnectionSystem remote;
	//Replace with ProfileSystem
	String playerName;
	
	public TestScreen(String playerName) {
		this.playerName = playerName;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		render.update(delta, batch);
		input.update(delta, batch);
		deck.update(delta, batch);		
		hand.update(delta, batch);
		
		if(Gdx.input.justTouched()) {
			remote.sendObject("C|CLICK");
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		this.deck = new DeckSystem(manager, playerName);
		this.hand = new HandSystem(manager, playerName);
		entities.generateLocalEntities();
		remote = new RemoteConnectionSystem(manager, playerName);
		remote.start();
		entities.generateRemoteEntities(remote);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
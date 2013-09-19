package com.zombiechopstick.kingdoms;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zombiechopstick.kingdoms.components.ChatMessage;

public class ChatSystem implements ComponentSystem {

	private static final long serialVersionUID = -3810729548526428625L;
	private EntityManager manager;
	private BitmapFont font;
	private List<ChatMessage> chatMessages;
	private String message;
	private String playerName;
	private boolean chatOpen = false;
	int shift = 0;

	public ChatSystem(EntityManager manager, String playerName) {
		addManager(manager);
		font = new BitmapFont();
		chatMessages = new ArrayList<ChatMessage>();
		this.playerName = playerName;
	}
	
	@Override
	public void addManager(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void update(float delta, SpriteBatch batch) {
		List<UUID> messages = manager.getAllEntitiesWithComponent(ChatMessage.class);
		for(UUID uid : messages) {
			ChatMessage message = manager.getComponent(uid, ChatMessage.class);
			if(!chatMessages.contains(message))	chatMessages.add(message);
		}
		
		batch.begin();
		
		int line = 0;
		for(int i = shift; i<chatMessages.size(); i++) {
			String received = null;
			received = chatMessages.get(i).getMessage();
			
			if(received.indexOf(":")!=-1) {
				if(received.substring(0, received.indexOf(":")).equals(playerName)) {
					received = "You" + received.substring(received.indexOf(":"));
				}
			}
			font.drawWrapped(batch, received, Gdx.graphics.getWidth()-260, Gdx.graphics.getHeight()-20 - line,250);	
			int multiplier = Math.round(font.getMultiLineBounds(received).width/250);
			if(multiplier > 0) line+=font.getLineHeight() * multiplier;
			else line+=font.getLineHeight();
			//System.out.println(font.getMultiLineBounds(received).width);
		}
		
		if(chatMessages.size() > 5 && shift<chatMessages.size() - 6) shift++;
		batch.end();
		
		//if(chatMessages.size() > 6) chatMessages.remove(0);
		
		if(Gdx.input.isKeyPressed(Input.Keys.C)) {
			chatOpen  = true;
		}
		if(chatOpen && !Gdx.input.isKeyPressed(Input.Keys.C)) {
			chatOpen = false;
			Gdx.input.getTextInput(new TextInputListener() {
				@Override
			    public void input (String text) {
					chatOpen = false;
					
					message = text;
					RemoteConnectionSystem.sendObject("CHAT|" + playerName + ": " + message);
					UUID mess = manager.createEntity();
					manager.addComponents(mess, new ChatMessage(playerName + ": " + message));
				}
		
				@Override
				public void canceled () {
					chatOpen = false;
				}
			}, "Enter a message", "");
		}
	}

}

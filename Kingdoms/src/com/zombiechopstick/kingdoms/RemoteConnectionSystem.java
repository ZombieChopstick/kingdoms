package com.zombiechopstick.kingdoms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.UUID;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RemoteConnectionSystem extends Thread implements ComponentSystem  {

	private static final long serialVersionUID = -668180559250610382L;
	private EntityManager manager;
	private Socket server;
	private ObjectOutputStream objectOut;
	private ObjectInputStream objectIn;
	private String playerName;
	private int connectionAttempts = 0;
	private ArrayList<String> remoteEntities = new ArrayList<String>();
	
	public RemoteConnectionSystem(EntityManager manager, String playerName) {
		this.manager = manager;
		this.playerName = playerName;
	}
	
	@Override
	public void run() {
		while(true) {
			if(server==null && connectionAttempts < 5) {
				try {
					server = new Socket("192.168.0.15",8080);
					objectOut = new ObjectOutputStream(server.getOutputStream());
					objectIn = new ObjectInputStream(server.getInputStream());
					sendObject("A|" + playerName);
				} catch (UnknownHostException e) {
					e.printStackTrace();
					connectionAttempts++;
				} catch (IOException e) {
					connectionAttempts++;
					System.out.println("Attempt #" + connectionAttempts + " of connecting to Kingdoms server...");
				}
			} else if(connectionAttempts == 5) {
				System.out.println("Unable to establish a connection to the server, please try again later.");
				break;
			} else if(server!=null) {
				try {
					String remoteCommand = (String) objectIn.readObject();
					if(!remoteCommand.equals("") && !remoteCommand.equals(null)) {
						System.out.println(remoteCommand);
						String[] actions = remoteCommand.split("[|]");
						if(actions[0].equals("ACKA")) {
							System.out.println("Sending Entities to Server");
							sendObject("E|");
							sendObject(manager.getAllEntities());
							sendObject("J|");
						}
						if(actions[0].equals("WAIT")) {
							System.out.println("Waiting for another player to join game.");
						}
						if(actions[0].equals("ACKJ")) {
							System.out.println(actions[1]);
							sendObject("CHAT|" + playerName + ": Hi Opponent");
							
						}
						if(actions[0].equals("CHAT")) {
							System.out.println(actions[1]);
						}
						if(actions[0].equals("C")) {
							if(actions[1].equals("CLICK")) {
								System.out.println("Opponent clicked on their screen");
							}
						}
						if(actions[0].equals("SYN")) {
							remoteEntities.add(remoteCommand);
						}
					}
				} catch (IOException e) {
					//e.printStackTrace();
					try {
						objectIn.close();
						objectOut.close();
						server.close();
						System.out.println("Lost connection to the server.");
						server=null;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void sendObject(Object object) {
		try {
			objectOut.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getRemoteEntities() {
		return remoteEntities;
	}
	
	@Override
	public void addManager(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void update(float delta, SpriteBatch batch) {	
	}
}

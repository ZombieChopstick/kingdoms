package com.zombiechopstick.kingdoms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class GameServer {
	ServerSocket server = null;
	ArrayList<String> players = new ArrayList<String>();
	private volatile ArrayList<Client> clients = new ArrayList<Client>();
	volatile ArrayList<Client> game = new ArrayList<Client>(2);
	
	public GameServer() {
		try {
			server = new ServerSocket(8080);
			System.out.println("Server Started on port 8080");
			Socket client = null;
			while(true) {
				client = server.accept();
				Client c = new Client(client);
				clients.add(c);
				c.start();
				new Thread(new Runnable() {
					public void run() {
						Scanner scan = new Scanner(System.in);
						while(scan.hasNextLine()) {
							for(Client player : clients) {
								sendObject(player,"Server: " + scan.nextLine());
								Thread.yield();
							}
						}
					}
				}).start();
			}
		} catch (SocketException e){
			try {
				server.close();
				System.out.println("Server Terminated");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e2) {
			//e2.printStackTrace();
			System.out.println("Server Terminated");
		}
	}
	
	public synchronized void sendObject(Client c, Object object) {
		try {
			c.getObjectOutputStream().writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized boolean joinGame(Client player) {

		for(Client c : clients) {
			if(c!=null) {
				if(!c.getInGame() && game.size() < 2) {
					game.add(c);
				}
			}
		}
		
		if(game.size() == 2) {
			if(game.get(0) == null) {System.out.println("error");}
			if(game.get(1) == null) {System.out.println("error");}
			sendObject(game.get(0),"ACKJ|" + game.get(1).getPlayerName() + " has joined your game.");
			game.get(0).setOpponent(game.get(1));
			game.get(0).setInGame(true);
			System.out.println(game.get(1).getPlayerName() + " has joined a game.");
			sendObject(game.get(1),"ACKJ|" + game.get(0).getPlayerName() + " has joined your game.");
			game.get(1).setOpponent(game.get(0));
			game.get(1).setInGame(true);
			System.out.println(game.get(0).getPlayerName() + " has joined a game.");
			game.clear();
			return true;
		}
		else {
			sendObject(player,"WAIT|");
			player.setInGame(true);
		}
		return false;
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		GameServer server = new GameServer();					
	}
	
	public class Client extends Thread {
		Socket client = null;
		String playerName = null;
		ObjectInputStream ins = null;
		ObjectOutputStream outs = null;
		List<UUID> entities;
		boolean inGame = false;
		Client opponent = null;
		
		public Client(Socket client) {
			this.client = client;
		}
		
		public void setInGame(boolean inGame) {
			this.inGame = inGame;
		}
		
		public void setOpponent(Client c) {
			opponent = c;
		}
		
		public boolean getInGame() {
			return inGame;
		}
		
		public String getPlayerName() {
			return playerName;
		}
		
		public ObjectInputStream getObjectInputStream() {
			return ins;
		}
		
		public ObjectOutputStream getObjectOutputStream() {
			return outs;
		}
		
		@SuppressWarnings("unchecked")
		public void run() {
			System.out.println("Client Connected");
			try {
				ins = new ObjectInputStream(client.getInputStream());
				System.out.println("Input Stream Established");
				outs = new ObjectOutputStream(client.getOutputStream());
				System.out.println("Output Stream Established");
			} catch (IOException e3) {
				e3.printStackTrace();
			}
			
			while(client!=null) {
				try {
					String command;
					command = (String) ins.readObject();
					System.out.println("Receiving Commands");
					if(!command.equals("") && !command.equals(null)) {
						System.out.println(command);
						String[] actions = command.split("[|]");
						if(actions[0].equals("A")) {
							players.add(actions[1]);
							playerName = actions[1];
							System.out.println(actions[1] + " joined the server. Waiting for another player...");
							System.out.println("Sending Commands");
							outs.writeObject("ACKA|" + playerName);
						}
						else if (actions[0].equals("Q")) {
							System.out.println(actions[1] + " left the server.");
						}
						else if (actions[0].equals("E")) {
							entities = (List<UUID>) ins.readObject();
							System.out.println(entities.size() + " entities received from " + playerName);
						}
						else if (actions[0].equals("J")) {
							joinGame(this);
						}
						else if (actions[0].equals("CHAT")) {
							sendObject(opponent,"CHAT|" + actions[1]);
						}
						else if (actions[0].equals("C")) {
							if(actions[1].equals("CLICK") && opponent!=null) {
								sendObject(opponent,"C|CLICK");
							}
						}
						else if (actions[0].equals("SYN")) {
							if(opponent!=null) {
								sendObject(opponent,entities);
							}
						}
						else if (actions[0].equals("SYNACK")) {
							
						}
					}
				} catch (SocketException e){
						try {
							ins.close();
							outs.close();
							client.close();
							client = null;
							clients.remove(this);
							System.out.println(playerName + " left the server.");
							this.interrupt();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
package com.zombiechopstick.kingdoms.components;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class BoardHex implements Component {

	private static final long serialVersionUID = 2835824646766694882L;
	private int col, row;
	private ArrayList<Vector2> vertices;
	
	public BoardHex(int col, int row) {
		this.col = col;
		this.row = row;
	}

	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
}

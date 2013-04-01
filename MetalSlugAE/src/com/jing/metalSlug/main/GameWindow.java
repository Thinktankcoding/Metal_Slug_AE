package com.jing.metalSlug.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.jing.metalSlug.world.World;

public class GameWindow extends BasicGame {

	public static final int WIDTH = 1024;
	public static final int HEIGHT = 720;

	public static final String TITLE = "Metal Slug v1.1";
	
	World world = null;
	
	public GameWindow(String title) {
		super(title);
		
		
	}

	/**
	 * @param args
	 * @throws SlickException
	 */

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new GameWindow(TITLE));
		app.setShowFPS(true);
		app.setDisplayMode(WIDTH, HEIGHT, false);
		app.start();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		world.render(gc, g);

	}

	@Override
	public void init(GameContainer g) throws SlickException {
		StaticValues.init();
		//StaticValues.print();
		
		world = new World();
		world.init();
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		System.out.println(delta);
		world.update(gc,delta);

	}

}

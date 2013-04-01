package com.jing.metalSlug.world;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.jing.metalSlug.main.GameConstants;
import com.jing.metalSlug.main.GameWindow;
import com.jing.metalSlug.main.StaticValues;
import com.jing.metalSlug.util.ActorFactory;
import com.jing.metalSlug.util.EntityComparator;

public class World {

	ArrayList<Entity> entities = new ArrayList<Entity>();
	
	Image bg = null;
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	EntityComparator comparator = new EntityComparator();
	Color color = new Color(255, 255, 255);
	public float camOffsetX = 0;
	public float camOffsetY = 0;
	Actor player1 = null;
	Actor player2 = null;

	public void init() {
		bg = StaticValues.getBGImage(GameConstants.MOUTAINS);
		
		player1 = ActorFactory.createActor(
				GameConstants.MARCRO, true, 50, 150, this);
		player2 = ActorFactory.createActor(
				GameConstants.FIO, false, 50, 290, this);
	
		Weapon weapon = new Weapon(Weapon.NORMAL, 3);
		Weapon weapon2 = new Weapon(Weapon.NORMAL,12);
		
		player1.addWeapon(weapon);
		player2.addWeapon(weapon2);
		
		entities.add(player1);
		entities.add(player2);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setColor(color);
		g.drawImage(bg, 0, 0);
		g.drawImage(bg, 0, 0, 800, 300, 0, 0, 442, 235, color);
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(gc, g);
		}

	}

	public void update(GameContainer gc, int delta) throws SlickException {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update(gc, delta);
		}
		Collections.sort(entities, comparator);
	}

}

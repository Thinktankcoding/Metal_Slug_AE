package jing.game.metalslugae;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.jing.metalSlug.main.GameConstants;
import com.jing.metalSlug.main.StaticValues;
import com.jing.metalSlug.world.AnimationWrapper;

public class MetalSlug extends BasicGame {

	public static final String Name = "Metal Slug AE 1.0";

	public static int WIDTH;
	public static int HEIGHT;
	
	AnimationWrapper wrapper;
	AnimationWrapper wrapper1;
	
	public MetalSlug(int width, int height) {
		super(Name);
		WIDTH = width;
		HEIGHT = height;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		Image image = wrapper1.getCurrentFrame();
		
		g.drawImage(image, 50, 165);
		
		image = wrapper.getCurrentFrame();
		g.drawImage(image, 50, 100);
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		StaticValues.init();
		wrapper = StaticValues.getRoleAnimations(GameConstants.MARCRO).get(GameConstants.IDLE_UPPER+"_right");
		wrapper1 = StaticValues.getRoleAnimations(GameConstants.MARCRO).get(GameConstants.IDLE_LOWER+"_right");
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		System.out.println(wrapper.getFrame());
		wrapper.update(delta);
		wrapper1.update(delta);
	}
}

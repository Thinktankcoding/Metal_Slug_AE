package com.jing.metalSlug.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.xml.XMLElement;
import org.newdawn.slick.util.xml.XMLElementList;
import org.newdawn.slick.util.xml.XMLParser;

import com.jing.metalSlug.world.Actor;
import com.jing.metalSlug.world.AnimationWrapper;

public class StaticValues {

	public static final String roleConfigFilePath = "res/EntityResourceDefinition.xml";

	private static HashMap<String, HashMap<String, AnimationWrapper>> roleAnimations = null;

	private static HashMap<String, Vector2f> roleDefaultSize = null;
	
	private static HashMap<String,Image> farBG = null;
	
	public static org.newdawn.slick.geom.Vector2f getWH(String name) {
		return roleDefaultSize.get(name);
	}

	public static void init() throws SlickException {
		roleAnimations = new HashMap<String, HashMap<String, AnimationWrapper>>();
		roleDefaultSize = new HashMap<String, Vector2f>();
		initRole();
		initBG();
	}

	private static void initRole() throws SlickException {
		XMLParser parser = new XMLParser();
		XMLElement root = parser.parse(roleConfigFilePath);

		XMLElementList rolesEntities = root.getChildren();

		for (int i = 0; i < rolesEntities.size(); i++) {

			XMLElement entity = rolesEntities.get(i);
			String roleName = entity.getAttribute("name");
			int defaultWidth = Integer.parseInt(entity
					.getAttribute("baseWidth"));
			int defualtHeight = Integer.parseInt(entity
					.getAttribute("baseHeight"));
			Vector2f WH = new Vector2f();
			WH.x = defaultWidth;
			WH.y = defualtHeight;

			roleDefaultSize.put(roleName, WH);

			XMLElementList animations = entity.getChildrenByName("animation");

			HashMap<String, AnimationWrapper> animationMap = new HashMap<String, AnimationWrapper>();

			for (int j = 0; j < animations.size(); j++) {

				XMLElement animation = animations.get(j);
				String animationName = animation.getAttribute("name");

				String otherDirectionName = "";
				if (animationName.endsWith("right")) {
					otherDirectionName = animationName.replace("right", "left");
				} else {
					otherDirectionName = animationName.replace("left", "right");
				}
				int interval = Integer.parseInt(animation
						.getAttribute("interval"));

				String path = animation.getAttribute("path");
				Image image = new Image(path);
				int height = image.getHeight();
				int width = image.getWidth();
				XMLElement widths = animation.getChildrenByName("width").get(0);
				
				boolean loop = true;
				if (animation.getAttribute("loop") != null
						&& animation.getAttribute("loop") != "") {
					loop = Boolean.parseBoolean(animation.getAttribute("loop"));
				}
				
				int adjustY = 0;
				if (widths.getAttribute("adjustY") != null
						&& widths.getAttribute("adjustY") != "") {
					adjustY = Integer.parseInt(widths.getAttribute("adjustY"));
				}
				
				int adjustX = 0;
				if (widths.getAttribute("adjustX") != null
						&& widths.getAttribute("adjustX") != "") {
					adjustX = Integer.parseInt(widths.getAttribute("adjustX"));
				}
				String[] cutXs = widths.getContent().split(",");

				Image[] animationImages = new Image[cutXs.length];

				AnimationWrapper wrapper = null;
				AnimationWrapper anotherWrapper = null;
				wrapper = new AnimationWrapper(null, adjustX,adjustY);
				wrapper.setLooping(loop);
				anotherWrapper = new AnimationWrapper(null,-adjustX, adjustY);
				anotherWrapper.setLooping(loop);
				for (int z = 0; z < cutXs.length; z++) {
					int pos = 0;
					int nextPos = 0;
					String xStr = cutXs[z];
					int cutX = Integer.parseInt(xStr);
					pos = cutX;
					if (z + 1 >= cutXs.length) {
						nextPos = width - cutX;
					} else {
						nextPos = Integer.parseInt(cutXs[z + 1]) - cutX;
					}

					animationImages[z] = image.getSubImage(pos, 0, nextPos,
							height);
					wrapper.addFrame(animationImages[z], interval);
					anotherWrapper.addFrame(
							animationImages[z].getFlippedCopy(true, false),
							interval);
				}
				animationMap.put(animationName, wrapper);
				animationMap.put(otherDirectionName, anotherWrapper);
			}
			roleAnimations.put(roleName, animationMap);
		}

	}
	
	
	private static void initBG(){/*
		farBG = new HashMap<String,Image>();
		try {
			Image image = new Image("C:/work space/Metal Slug 1.1/res/bg/mountains.gif");
			farBG.put("moutains", image);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public static HashMap<String, AnimationWrapper> getRoleAnimations(
			String roleName) {
		if (roleAnimations.get(roleName) == null) {
			System.err.println("no such resource");
		}
		return (HashMap<String, AnimationWrapper>) roleAnimations.get(roleName)
				.clone();
	}
	
	public static Image getBGImage(String name){
		if (farBG.get(name) == null) {
			System.err.println("no such resource");
		}
		return farBG.get(name);
		
	}
}

package com.jing.metalSlug.util;

import java.util.Comparator;

import com.jing.metalSlug.world.Entity;

public class EntityComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		if (o1 instanceof Entity && o2 instanceof Entity) {
			Entity e1 = (Entity)o1;
			Entity e2 = (Entity)o2;
			return (int) (e1.getY() - e2.getY());
		}
		return 0;
	}

}

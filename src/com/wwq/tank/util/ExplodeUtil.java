package com.wwq.tank.util;

import java.util.List;

import com.wwq.tank.entity.Actor;
import com.wwq.tank.entity.Explode;

public class ExplodeUtil {

	public static void showExplode(List<Actor> actors, int x, int y) {
		actors.add(new Explode(x, y));
	}
}

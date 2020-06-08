package com.wwq.tank.util;

import java.util.List;

import com.wwq.tank.entity.Actor;
import com.wwq.tank.entity.Explode;

public class ExplodeUtil {
	
	static Audio explodeWav = new Audio("audio/explode.wav");

	public static void showExplode(List<Actor> actors, int x, int y) {
		actors.add(new Explode(x, y));
	}
	
	public static void showExplodeWav() {
		new Thread(() -> new Audio("audio/explode.wav").play()).start();
	}
}

package com.wwq.tank.fire.impl;

import java.util.List;

import com.wwq.tank.constant.TankContant;
import com.wwq.tank.entity.Actor;
import com.wwq.tank.entity.Bullet;
import com.wwq.tank.fire.FireGenerator;

public class SingleFireGenerator implements FireGenerator<Actor> {

	@Override
	public void fire(List<Actor> actors, Actor t) {
		int bx = t.getRect().x + t.getRect().width / 2 - TankContant.BULLET_W / 2;
		int by = t.getRect().y + t.getRect().height / 2 - TankContant.BULLET_H / 2;
		Bullet bullet = new Bullet(bx, by, t.getDir(), t.getGroup());
		bullet.setGroup(t.getGroup());
		actors.add(bullet);
	}

}
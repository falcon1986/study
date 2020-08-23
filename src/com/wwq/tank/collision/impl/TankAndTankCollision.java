package com.wwq.tank.collision.impl;

import com.wwq.tank.collision.IActorCollision;
import com.wwq.tank.constant.Role;
import com.wwq.tank.entity.Actor;
import com.wwq.tank.entity.GameModel;

/**
 * 坦克和坦克碰撞
 * 
 * @功能描述
 *       
 * @作者 
 *       wwq
 * @创建时间 
 *       2020年6月10日 下午4:51:14
 */
public class TankAndTankCollision implements IActorCollision {

	@Override
	public boolean collideWith(GameModel gm, Actor o1, Actor o2) {
		if(Role.TANK == o1.getRole() && Role.TANK == o2.getRole()) {
			if(o1.getRect().intersects(o2.getRect())) { //碰撞
				o1.getRect().x = o1.getOldX();
				o1.getRect().y = o1.getOldY();
				
				o2.getRect().x = o2.getOldX();
				o2.getRect().y = o2.getOldY();
			}
		}

		return true;
	}

}

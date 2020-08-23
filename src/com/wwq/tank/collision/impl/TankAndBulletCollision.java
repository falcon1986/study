package com.wwq.tank.collision.impl;

import com.wwq.tank.collision.IActorCollision;
import com.wwq.tank.constant.Role;
import com.wwq.tank.entity.Actor;
import com.wwq.tank.entity.GameModel;
import com.wwq.tank.util.ExplodeUtil;

/**
 * 坦克和子弹碰撞
 * 
 * @功能描述
 *       
 * @作者 
 *       wwq
 * @创建时间 
 *       2020年6月10日 下午4:51:14
 */
public class TankAndBulletCollision implements IActorCollision {

	@Override
	public void collideWith(GameModel gm, Actor o1, Actor o2) {
		if(Role.TANK == o1.getRole() && Role.BULLET == o2.getRole()) {
			o1.setLive(false);
			o2.setLive(false);
			
			//爆炸效果
			int x = o1.getRect().x + o1.getRect().width / 2;
			int y = o1.getRect().y + o1.getRect().height / 2;
			ExplodeUtil.showExplode(gm.getActors(), x, y);
		}

	}

}

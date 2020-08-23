package com.wwq.tank.collision;

import com.wwq.tank.entity.Actor;
import com.wwq.tank.entity.GameModel;

/**
 * 碰撞处理接口
 * 
 * @功能描述
 *       
 * @作者 
 *       wwq
 * @创建时间 
 *       2020年6月10日 下午4:48:47
 */
public interface IActorCollision {

	/**
	 * 碰撞处理
	 * @param gm
	 * @param o1
	 * @param o2
	 */
	boolean collideWith(GameModel gm, Actor o1, Actor o2);
}

package com.wwq.tank.collision;

import java.util.LinkedList;
import java.util.List;

import com.wwq.tank.collision.impl.TankAndBulletCollision;
import com.wwq.tank.collision.impl.TankAndTankCollision;
import com.wwq.tank.entity.Actor;
import com.wwq.tank.entity.GameModel;

public class CollisionChain implements IActorCollision {
	
	public CollisionChain() {
		add(new TankAndTankCollision());
		add(new TankAndBulletCollision());
	}

	private List<IActorCollision> colliders = new LinkedList<>();
	
	public boolean collideWith(GameModel gm, Actor o1, Actor o2) {
		for(int i=0; i<colliders.size(); i++) {
			if(!colliders.get(i).collideWith(gm, o1, o2)) {
				return false;
			}
		}
		return true;
	}
	
	public void add(IActorCollision c) {
		colliders.add(c);
	}
}

package com.wwq.tank.fire;

import java.util.List;

import com.wwq.tank.entity.Actor;

public interface FireGenerator<T> {

	void fire(List<Actor> actors, T t);
}

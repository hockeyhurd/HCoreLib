package com.hockeyhurd.api.math.pathfinding;

import com.hockeyhurd.api.math.Vector3;

/**
 * @author hockeyhurd
 * @version 1/25/2016.
 */
public interface IPathTile {

	double distanceTo(Vector3<Integer> vec);

	Vector3<Integer> worldVec();

	Vector3<Integer> getOffsetVec(int x, int y, int z);

	boolean isSolid();

}

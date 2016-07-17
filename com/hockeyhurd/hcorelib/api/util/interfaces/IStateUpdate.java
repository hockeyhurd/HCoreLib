package com.hockeyhurd.hcorelib.api.util.interfaces;

import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.api.tileentity.AbstractTile;
import net.minecraft.world.World;

/**
 * Interfacing on block side for blocks that need state
 * based updating.
 *
 * @author hockeyhurd
 * @version 7/17/2016.
 */
public interface IStateUpdate {

	/**
	 * Attempts to update the block's state.
	 *
	 * @param tileEntity TileEntity reference.
	 * @param world World reference.
	 * @param vec Vector3i position.
	 * @param data data to apply.
	 */
	void updateState(AbstractTile tileEntity, World world, Vector3<Integer> vec, Object... data);

}

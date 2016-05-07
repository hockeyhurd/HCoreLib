package com.hockeyhurd.hcorelib.api.util.interfaces;

import com.hockeyhurd.hcorelib.api.math.Vector3;
import net.minecraft.world.World;

/**
 * Simple interface to allow updating of block metadata through method call.
 * <br><bold>NOTE: </bold>This interface should be implemented in block's class.
 * 
 * @author hockeyhurd
 * @version Jun 4, 2015
 */
public interface IMetaUpdate {

	/**
	 * Method used to update texture through metadata.
	 * 
	 * @param isActive set whether tileentity is active or not.
	 * @param world world object as reference.
	 * @param vec world vector coordinate.
	 */
	void updateMeta(boolean isActive, World world, Vector3<Integer> vec);
	
	/**
	 * Method used to update texture through metadata.
	 * 
	 * @param meta metadata to set.
	 * @param world world object as reference.
	 * @param vec world vector coordinate.
	 */
	void updateMeta(int meta, World world, Vector3<Integer> vec);
	
}

package com.hockeyhurd.hcorelib.api.item;

import net.minecraft.item.Item;

/**
 * Generic class for creating but not limited to metallic items i.e. ingots, dusts, etc.
 *
 * @deprecated Deprecated on 4/25/16.  Usage of this class
 * should move to using AbstractHCoreItem as this will be removed in
 * the future.
 *
 * @author hockeyhurd
 * @version Jun 4, 2015
 */
@Deprecated
public abstract class AbstractItemMetallic extends Item {
	
	protected String fileName;
	
	public AbstractItemMetallic(String name, String assetDir) {
		super();
		this.setUnlocalizedName(name);
		this.fileName = assetDir + name;
	}

}

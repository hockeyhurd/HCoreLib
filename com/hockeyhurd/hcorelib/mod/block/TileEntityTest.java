package com.hockeyhurd.hcorelib.mod.block;

import com.hockeyhurd.hcorelib.api.tileentity.AbstractTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

/**
 * @author hockeyhurd
 * @version 5/23/2016.
 */
public class TileEntityTest extends AbstractTile {

	private EnumFacing frontFacing = EnumFacing.NORTH;
	private boolean active;

	public TileEntityTest() {
		super("container.tileTest");
		active = true;
	}

	public EnumFacing getFrontFacing() {
		return frontFacing;
	}

	public void setFrontFacing(EnumFacing facing) {
		this.frontFacing = facing;
	}

	public boolean isActive() {
		return active;
	}

	@Override
	public void readNBT(NBTTagCompound comp) {
	}

	@Override
	public void saveNBT(NBTTagCompound comp) {
	}

}

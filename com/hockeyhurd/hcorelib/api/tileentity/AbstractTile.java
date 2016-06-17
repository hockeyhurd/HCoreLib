package com.hockeyhurd.hcorelib.api.tileentity;

import com.hockeyhurd.hcorelib.api.block.AbstractHCoreBlock;
import com.hockeyhurd.hcorelib.api.math.Vector3;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Generalized abstract tile entity class.
 *
 * @author hockeyhurd
 * @version 4/18/16
 */
public abstract class AbstractTile extends TileEntity {

    protected String customName;

    /**
     * Initializes custom name to provided String.
     *
     * @param customName String custom name.
     */
    public AbstractTile(String customName) {
        this.customName = customName;
    }

    /**
     * Initializes with custom name set to "container.generic".
     */
    public AbstractTile() {
        this.customName = "container.generic";
    }

    /**
     * Gets the block associated with this tile entity.
     *
     * @return AbstractHCoreBlock contained in tile entity.
     */
    public AbstractHCoreBlock getBlock() {
        return (AbstractHCoreBlock) blockType;
    }

    /**
     * Gets the world vector3 coordinates.
     *
     * @return Vector3i coordinates.
     */
    public Vector3<Integer> worldVec() {
        return new Vector3<Integer>(pos.getX(), pos.getY(), pos.getZ());
    }

    /**
     * Handles loading and reading data from memory. <br>
     * NOTE: This method should be overridden as necessary.
     */
    @Override
    public void readFromNBT(NBTTagCompound comp) {
        super.readFromNBT(comp);
        readNBT(comp);
    }

    /**
     * Reads from NBT data.
     *
     * @param comp NBTTagCompound.
     */
    public abstract void readNBT(NBTTagCompound comp);

    /**
     * Handles saving and writing to memory. <br>
     * NOTE: This method should be overridden as necessary.
     */
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound comp) {
        super.writeToNBT(comp);
        saveNBT(comp);
        return comp;
    }

    /**
     * Writes to NBT data.
     *
     * @param comp NBTTagCompound.
     */
    public abstract void saveNBT(NBTTagCompound comp);

    /**
     * Getter function for getting the custom name if the te has one.
     */
    public String getInventoryName() {
        return this.customName;
    }

    /**
     * Getter function for getting whether the te has a custom name or not.
     */
    public boolean hasCustomInventoryName() {
        return this.customName != null && this.customName.length() > 0;
    }

    /**
     * Method used to set the custom name of the tile entity. <br>
     * NOTE: By default, the custom name is set to container.generic.
     * @param name new customized name.
     */
    public void setCustomName(String name) {
        this.customName = name;
    }

}

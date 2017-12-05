package com.hockeyhurd.hcorelib.api.item;

import com.hockeyhurd.hcorelib.api.math.Vector3;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * @author hockeyhurd
 * @version 12/5/17
 */
public interface IWrenchable {

    /**
     * Helper function to get how the tileentity as a block should be rotated.
     * <br><bold>NOTE:</bold> This function can be nullified if 'canRotateTE' is set to false.
     *
     * @return EnumFacing containing new meta data after being rotated.
     */
    EnumFacing getRotatedState(EnumFacing facingDir, IBlockState currentState);

    /**
     * Gets the current facing.
     *
     * @return EnumFacing.
     */
    EnumFacing getCurrentFacing();

    /**
     * Sets the front facing.
     *
     * @param face EnumFacing side.
     */
    void setFrontFacing(EnumFacing face);

    /**
     * Function to get whether this te can be rotated or not.
     *
     * @return true if can be rotated else returns false.
     */
    boolean canRotateTE();

    /**
     * Method called when wrench was used on tileentity.
     *
     * @param stack itemstack, (reference to wrench itself).
     * @param player player interacted with.
     * @param world world used.
     * @param vec vector of tileentity.
     */
    void onInteract(ItemStack stack, EntityPlayer player, World world, Vector3<Integer> vec);

    /**
     * Function to get whether picking up tileentity should save its data and contents.
     *
     * @return true if can be saved else returns false (voids stored data).
     */
    boolean canSaveDataOnPickup();

    /**
     * Method used to read nbt data.
     *
     * @param comp compound to reference.
     */
    void readNBT(NBTTagCompound comp);

    /**
     * Method to write to nbt data.
     *
     * @param comp compound to reference.
     */
    void saveNBT(NBTTagCompound comp);

    /**
     * Function to get world coordinates of wrenchable tileentity.
     *
     * @return vector3 component.
     */
    Vector3<Integer> worldVec();

}

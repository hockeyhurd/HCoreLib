package com.hockeyhurd.hcorelib.api.tileentity.multiblock;

import com.hockeyhurd.hcorelib.api.tileentity.AbstractTile;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.Set;

/**
 * Interfacing for IMultiblock master.
 *
 * @see AbstractTile
 * @see IMultiblockable
 *
 * @author hockeyhurd
 * @version 12/28/2017.
 */
public interface IMasterBlock<T extends AbstractTile> extends IMultiblockable<T> {

    /**
     * Gets the set of classes that the controller can work with.
     *
     * @return Set Class<? extends IMultiblockable<?>>.
     */
    Set<Class<? extends IMultiblockable<?>>> getValidComponents();

    /**
     * Checks if the multiblock is complete.
     *
     * @return boolean.
     */
    boolean checkIsCompleteMultiblock();

    /**
     * Gets the current state of the multiblock (cached).
     *
     * @see EnumMultiblockState
     * @return EnumMultiblockState.
     */
    EnumMultiblockState getMultiblockState();

    /**
     * Sets the current multiblock state by the provided value.
     *
     * @see EnumMultiblockState
     * @param state EnumMultiblockState.
     */
    void setMultiblockState(EnumMultiblockState state);

    /**
     * Iterates over internal mapping and notifies all children
     * components of the current multiblock state.
     */
    void notifyChildren();

    /**
     * Adds a child component to the internal mapping.
     *
     * @see IMultiblockable
     * @param child IMultiblockable
     */
    void addChild(IMultiblockable<?> child);

    /**
     * Removes a child component to the internal mapping.
     *
     * @see IMultiblockable
     * @param child IMultiblockable
     */
    void removeChild(IMultiblockable<?> child);

    /**
     * Gets the internal mapping of children in the multiblock.
     *
     * @see BlockPos
     * @see IMultiblockable
     * @return Map<BlockPos, IMultiblockable<?>>
     */
    Map<BlockPos, IMultiblockable<?>> getChildren();

}

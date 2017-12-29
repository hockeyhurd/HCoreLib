package com.hockeyhurd.hcorelib.api.tileentity.multiblock;

import com.hockeyhurd.hcorelib.api.tileentity.AbstractTile;
import net.minecraft.util.math.BlockPos;

import java.util.Map;

/**
 * Interfacing for IMultiblock master.
 *
 * @author hockeyhurd
 * @version 12/28/2017.
 */
public interface IMasterBlock<T extends AbstractTile> extends IMultiblockable<T> {

    boolean checkIsCompleteMultiblock();

    EnumMultiblockState getMultiblockState();
    void setMultiblockState(EnumMultiblockState state);

    void notifyChildren();

    void addChild(IMultiblockable<?> child);

    void removeChild(IMultiblockable<?> child);

    Map<BlockPos, IMultiblockable<?>> getChildren();

}

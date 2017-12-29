package com.hockeyhurd.hcorelib.api.tileentity.multiblock;

/**
 * @author hockeyhurd
 * @version 12/28/2017.
 */
public enum EnumMultiblockState {

    COMPLETE, IN_COMPLETE;

    public static EnumMultiblockState getStateFromBool(final boolean complete) {
        return complete ? COMPLETE : IN_COMPLETE;
    }

}

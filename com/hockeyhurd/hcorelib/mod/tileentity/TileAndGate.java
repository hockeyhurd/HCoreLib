package com.hockeyhurd.hcorelib.mod.tileentity;

import com.hockeyhurd.hcorelib.api.tileentity.AbstractTileContainer;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author hockeyhurd
 * @version 4/1/18
 */
public class TileAndGate extends AbstractTileContainer {

    private enum SignalStrength {
        ZERO, LOW, HIGH
    }

    private EnumFacing frontFacing = EnumFacing.NORTH;
    private SidedSignal signals;

    public TileAndGate() {
        signals = new SidedSignal();
    }

    public boolean setSignalFromSide(int signal, EnumFacing side) {
        if (side != frontFacing)
            signals.setSignalStrengthFromSide(side, signal == 0 ? SignalStrength.ZERO : signal < 8 ? SignalStrength.LOW : SignalStrength.HIGH);

        int count = 0;
        for (EnumFacing face : EnumFacing.HORIZONTALS) {
            if (face == frontFacing)
                continue;
            else if (signals.getSignalStrengthFromSide(face) == SignalStrength.HIGH)
                count++;
        }

        final boolean powered = count == 3;
        signals.setSignalStrengthFromSide(frontFacing, powered ? SignalStrength.HIGH : SignalStrength.ZERO);

        return powered;
    }

    public int getSignalFromSide(final EnumFacing side) {
        final SignalStrength signalStrength = signals.getSignalStrengthFromSide(side);

        switch (signalStrength) {
            case ZERO:
                return 0;
            case LOW:
                return 1;
            case HIGH:
                return 0xF;
        }

        return 0;
    }

    @Override
    protected void initSlotsArray() {

    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, EnumFacing side) {
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, EnumFacing side) {
        return false;
    }

    @Override
    public void readNBT(NBTTagCompound comp) {

    }

    @Override
    public void saveNBT(NBTTagCompound comp) {

    }

    @Override
    public boolean shouldRefresh(World world, BlockPos blockPos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    public EnumFacing getFrontFacing() {
        return frontFacing;
    }

    public void setFrontFacing(EnumFacing frontFacing) {
        this.frontFacing = frontFacing;
    }

    private static class SidedSignal {

        private byte value;

        public SidedSignal(byte value) {
            this.value = value;
        }

        public SidedSignal() {
            this((byte) 0);
        }

        public SignalStrength getSignalStrengthFromSide(final EnumFacing side) {
            final int ordinal = side.getHorizontalIndex();

            byte mask = (byte) (value >> (ordinal << 1));
            mask &= 3;

            switch (mask) {
                case 0:
                    return SignalStrength.ZERO;
                case 1:
                    return SignalStrength.LOW;
                case 2:
                    return SignalStrength.HIGH;
                default:
                    HCoreLibMain.logHelper.severe("Error decoding signal value!", mask, value);
                    return SignalStrength.ZERO;
            }
        }

        public void setSignalStrengthFromSide(final EnumFacing side, final SignalStrength signal) {
            final int ordinal = side.getHorizontalIndex();
            final int powerLevel = signal.ordinal();
            int encode = powerLevel << (ordinal << 1);

            // byte: 10 10 10 10

            int mask = 3 << (ordinal << 1);
            mask = ~mask;
            value &= (byte) mask;
            value |= (byte) encode;
        }

    }

}

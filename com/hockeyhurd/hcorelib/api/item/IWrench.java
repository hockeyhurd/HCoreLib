package com.hockeyhurd.hcorelib.api.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author hockeyhurd
 * @version 12/5/17
 */
public interface IWrench {

    void sneakWrench(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, IBlockState blockState,
                     IWrenchable wrenchable, EnumFacing sideHit);

    void normalWrench(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, IBlockState blockState,
                      IWrenchable wrenchable, EnumFacing sideHit);

}

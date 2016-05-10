package com.hockeyhurd.hcorelib.api.item;

import com.hockeyhurd.hcorelib.api.util.ItemUtils;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author hockeyhurd
 * @version 7/22/2015.
 */
public abstract class AbstractItemBucket extends ItemBucket {

	protected final String name;
	protected final String assetDir;
	protected final Block block;
	protected final Item itemToReturn;

	/**
	 * @param name name of item bucket.
	 * @param assetDir location of asset directory.
	 * @param itemToReturn Item to return.
	 * @param block reference to block.
	 */
	public AbstractItemBucket(String name, String assetDir, Item itemToReturn, Block block) {
		super(block);

		this.setUnlocalizedName(name);
		this.name = name;
		this.assetDir = assetDir;
		this.itemToReturn = itemToReturn;
		this.block = block;
	}

	/**
	 * @param name name of item bucket.
	 * @param assetDir location of asset directory.
	 * @param block reference to block.
	 */
	public AbstractItemBucket(String name, String assetDir, Block block) {
		this(name, assetDir, Items.bucket, block);
	}

	/*@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		this.itemIcon = reg.registerIcon(assetDir + name);
	}*/

	@Override
	public boolean tryPlaceContainedLiquid(EntityPlayer player, World world, BlockPos pos) {
		if (block == null) return false;
		else if (!world.isAirBlock(pos) && world.getBlockState(pos).getMaterial().isSolid()) return false;
		else {
			world.setBlockState(pos, block.getDefaultState(), 3);
			return true;
		}
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List subItems) {
		subItems.add(new ItemStack(item, 1, 0));
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	/*@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
		return false;
	}*/

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		if (stack.attemptDamageItem(1, itemRand)) return ItemUtils.createStack(itemToReturn);
		return stack;
	}

}
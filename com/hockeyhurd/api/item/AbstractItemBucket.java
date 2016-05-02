package com.hockeyhurd.api.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
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

	/**
	 * @param name name of item bucket.
	 * @param assetDir location of asset directory.
	 * @param block reference to block.
	 */
	public AbstractItemBucket(String name, String assetDir, Block block) {
		super(block);

		this.setUnlocalizedName(name);
		this.name = name;
		this.assetDir = assetDir;
		this.block = block;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		this.itemIcon = reg.registerIcon(assetDir + name);
	}

	@Override
	public boolean tryPlaceContainedLiquid(World world, int x, int y, int z) {
		if (block == null) return false;
		else if (!world.isAirBlock(x, y, z) && world.getBlock(x, y, z).getMaterial().isSolid()) return false;
		else {
			world.setBlock(x, y, z, block, 0, 3);
			return true;
		}
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List subItems) {
		subItems.add(new ItemStack(item, 1, 0));
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
		return true;
	}
}
